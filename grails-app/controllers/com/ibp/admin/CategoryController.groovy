package com.ibp.admin

import com.ibp.admin.commands.CategoryCommand
import com.ibp.admin.commands.CategoryUpload
import com.ibp.admin.commands.SubCategoryCommand
import com.ibp.admin.utils.IbpHubUtils
import grails.converters.JSON
import grails.validation.ValidationException


import static org.springframework.http.HttpStatus.*

class CategoryController {

    def categoryService
    def awsService
    UserService userService
//    VendorBusinessDetails vendorBusinessDetails
    VendorsService vendorsService
    AuditLogsService auditLogsService
    def exportService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        if(params?.searchPage?.equals("subcategory")) {
            params.max = Math.min(max ?: 20, 100)
            def categoryList = categoryService.listSubCategory(params)
            if (request.xhr) {
                render template: 'subCategory/subCategorylist', model: [
                        categoryList : categoryList,
                        categoryCount: categoryList.totalCount
                ]
                return
            }

            render view: 'subCategory/subCategory', model: [
                    categoryList : categoryList,
                    categoryCount: categoryList.totalCount
            ]
        } else {
            def categoryList = categoryService.list(params)
            if (request.xhr) {
                render template: 'list', model: [
                        categoryList : categoryList,
                        categoryCount: categoryList.totalCount
                ]
                return
            }
            respond categoryList, model: [categoryCount: categoryList.totalCount]
        }

    }


    def show(Long id) {
        respond categoryService.get(id)
    }

    def create() {
        respond new Category(params)
    }

    def save(CategoryCommand categoryCommand) {
        if (categoryCommand == null) {
            notFound()
            return
        }
        if (categoryCommand?.hasErrors()) {
            render view: 'create', model: [categoryCommand: categoryCommand]
            return
        }
        Category category = new Category()
        bindData(category,categoryCommand)
        try {
            if (!category?.categoryId) {
                category?.categoryId = UUID.randomUUID().toString()
            }
            if (categoryCommand?.imageFile?.size > 0) {
                String bannerUrl = awsService.uploadImageToS3(categoryCommand?.imageFile , Constants.S3Folders.CATEGORY,
                        category?.categoryId)
                category?.bannerUrl = bannerUrl
            }
            if (categoryCommand?.iconImageFile?.size > 0) {
                String iconUrl = awsService.uploadImageToS3(categoryCommand?.iconImageFile , Constants.S3Folders.CATEGORY_ICON,
                        category?.categoryId)
                category?.iconUrl = iconUrl
            }
            category?.createdBy = userService?.getLoggedInUser()?.username
            category?.updatedBy = userService?.getLoggedInUser()?.username

            String title = category?.name
            String url = IbpHubUtils.convertToSlug(title)
            category?.displayName = url
            categoryService.save(category)
            auditLogsService.logAction('Save','Category',category?.id,userService?.getLoggedInUser()?.username,(category as JSON).toString(false),(category as JSON).toString(false))
        } catch (ValidationException e) {
            respond category.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Category created successfully' //message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action: 'index'
            }
            '*' { respond category, [status: CREATED] }
        }
    }

    def edit(Long id) {
        Category category = categoryService.get(id)
        respond category
    }

    def update(CategoryCommand categoryCommand) {

        if (categoryCommand == null) {
            notFound()
            return
        }

        Category category = Category.findById(params?.id)
        if (!category) {
            notFound()
            return
        }
        def oldCategory = category.clone()
        try {
            String bannerUrl = category?.bannerUrl
            String iconUrl = category?.iconUrl
            bindData(category, categoryCommand)

            if (categoryCommand?.imageFile?.size > 0) {
                bannerUrl = awsService.uploadImageToS3(categoryCommand?.imageFile, Constants.S3Folders.CATEGORY,
                        category?.categoryId)
                category?.bannerUrl = bannerUrl
            } else if (categoryCommand?.imageFile?.size == 0 && bannerUrl) {
                category?.bannerUrl = bannerUrl
            } else {
                awsService.removeImageFromS3(bannerUrl , 'prod/category' , category?.categoryId)
            }

            if (categoryCommand?.iconImageFile?.size >0) {
                iconUrl = awsService.uploadImageToS3(categoryCommand?.iconImageFile, Constants.S3Folders.CATEGORY_ICON,
                        category?.categoryId)
                category?.iconUrl = iconUrl
            } else if (categoryCommand?.imageFile?.size == 0 && iconUrl) {
                category?.iconUrl = iconUrl
            } else {
                awsService.removeImageFromS3(iconUrl , 'prod/category/icon' , category?.categoryId)
            }
            category?.updatedBy = userService?.getLoggedInUser()?.username
            String title = category?.name
            String url = IbpHubUtils.convertToSlug(title)
            category?.displayName = url
            categoryService.save(category)
            auditLogsService.logAction('Update', 'Category', category?.id, userService.getLoggedInUser()?.username, (oldCategory as JSON).toString(false), (category as JSON).toString(false))
            List<VendorBusinessDetails> vendorBusinessDetailList = VendorBusinessDetails.findAllByCategory(category)
            for (VendorBusinessDetails vendorBusinessDetails:vendorBusinessDetailList) {
                vendorBusinessDetails?.categoryName =category?.name
                vendorsService.saveVendorBusinessDetails(vendorBusinessDetails)
            }
            List<VendorProductList> vendorProductLists = VendorProductList.findAllByCategory(category)
            for(VendorProductList vendorProductList:vendorProductLists){
                vendorProductList?.categoryName = category?.name
                vendorsService.saveVendorProductList(vendorProductList)
            }
            request.withFormat {
                form multipartForm {
                    flash.message = 'Category updated successfully and ' + vendorBusinessDetailList?.size() + ' Vendors are updated'
                    redirect action: 'index'
                }
                '*'{ respond category, [status: OK] }
            }
        } catch (ValidationException ignored) {
            respond category.errors, view:'edit'
            return
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        categoryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = 'Category deleted successfully' //message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = 'default.not.found.message'//, args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def subcategory(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def categoryList = categoryService.listSubCategory(params)
        if (request.xhr) {
            render template: 'subCategory/subCategorylist', model: [
                    categoryList : categoryList,
                    categoryCount: categoryList.totalCount
            ]
            return
        }
        render view: 'subCategory/subCategory', model: [
                categoryList : categoryList,
                categoryCount: categoryList.totalCount
        ]
    }

    def showSubCategory(Long id) {
        respond categoryService.getSubCategory(id)
    }

    def createSubCategory() {
        respond new SubCategory(params), view: 'subCategory/create'
    }

    def saveSubCategory(SubCategoryCommand categoryCommand) {
        if (categoryCommand == null) {
            notFound()
            return
        }
        SubCategory subCategory = new SubCategory()
        bindData(subCategory,categoryCommand)
        try {
            if (!subCategory?.subCategoryId) {
                subCategory?.subCategoryId = UUID.randomUUID().toString()
            }
            Category masterCategory = Category.findById(categoryCommand?.categoryId)
            subCategory?.category = masterCategory
            if (categoryCommand?.imageFile?.size > 0) {
                String bannerUrl = awsService.uploadImageToS3(categoryCommand?.imageFile , 'subCategory' , subCategory?.subCategoryId)
                subCategory?.bannerUrl = bannerUrl
            }
            subCategory?.createdBy = userService?.getLoggedInUser()?.username
            subCategory?.updatedBy = userService?.getLoggedInUser()?.username
            String title = subCategory?.name
            String url = IbpHubUtils.convertToSlug(title)
            subCategory?.displayName = url
            categoryService.saveSubCategory(subCategory)
            auditLogsService.logAction('Save','SubCategory',subCategory?.id,userService?.getLoggedInUser()?.username,(subCategory as JSON).toString(false),(subCategory as JSON).toString(false))
        } catch (ValidationException e) {
            respond subCategory.errors, view:'subCategory/create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'SubCategory created successfully' //message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action: 'subcategory'
            }
            '*' { respond subCategory, [status: CREATED] }
        }
    }

    def editSubCategory(Long id) {
        SubCategory category = categoryService.getSubCategory(id)
        render view: 'subCategory/edit', model: [
                category : category
        ]
    }

    def updateSubCategory(SubCategoryCommand subCategoryCommand) {
        if (subCategoryCommand == null) {
            notFound()
            return
        }
            SubCategory subCategory = SubCategory.findById(params?.id)
            def oldSubCategory = subCategory.clone()
        try {
            String bannerUrl = subCategory?.bannerUrl
            bindData(subCategory, subCategoryCommand)
            Category masterCategory = Category.findById(subCategoryCommand?.categoryId)
            subCategory?.category = masterCategory
            if (subCategoryCommand?.imageFile?.size >0 ) {
                bannerUrl = awsService.uploadImageToS3(subCategoryCommand?.imageFile, 'subCategory', subCategory?.subCategoryId)
                subCategory?.bannerUrl = bannerUrl
            } else if (subCategoryCommand?.imageFile?.size == 0 && bannerUrl) {
                subCategory?.bannerUrl = bannerUrl
            }
            else {
                awsService.removeImageFromS3(bannerUrl , 'prod/subCategory' , subCategory?.subCategoryId)
            }
            subCategory?.updatedBy = userService?.getLoggedInUser()?.username
            String title = subCategory?.name
            String url = IbpHubUtils.convertToSlug(title)
            subCategory?.displayName = url
            categoryService.saveSubCategory(subCategory)
            auditLogsService.logAction('Update', 'SubCategory', subCategory?.id, userService.getLoggedInUser()?.username, (oldSubCategory as JSON).toString(false), (subCategory as JSON).toString(false))
            List<VendorProductList> vendorProductLists = VendorProductList.findAllBySubCategory(subCategory)
            for(VendorProductList vendorProductList:vendorProductLists){
                vendorProductList?.subCategoryName = subCategory?.name
                vendorsService.saveVendorProductList(vendorProductList)
            }

        } catch (ValidationException e) {
            respond subCategory.errors, view:'subCategory/edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'SubCategory updated successfully' //, args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action: 'subcategory'
            }
            '*'{ respond subCategory, [status: OK] }
        }
    }

    def deleteSubCategory(Long id) {
        if (id == null) {
            notFound()
            return
        }

        categoryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = 'SubCategory deleted successfully' //message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"subcategory", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def toggleStatus(Long toggleId) {
        if (toggleId == null) {
            notFound()
            return
        }
        String message
        Category category = Category.findById(toggleId)
        def oldCatStatus = category.clone()
        if(category?.active) {
            List<VendorBusinessDetails> vendorBusinessDetailList = VendorBusinessDetails.findAllByCategory(category)
            if (vendorBusinessDetailList?.size() == 0) {
                List<VendorProductList> vendorProductLists = VendorProductList.findAllByCategory(category)
                if (vendorProductLists?.size() == 0) {
                    category.active = false
                    message = 'Category has been Deactivated successfully'
                } else {
                    message = "Category " + category?.name + " is associated with Vendor so it can not be deactivated"
                    request.withFormat {
                        form multipartForm {
                            flash.errorMessage = message
                            redirect action:"index", method:"GET"
                        }
                    }
                    return
                }
            } else {
               message = "Category " + category?.name + " is associated with Vendor so it can not be deactivated"
                request.withFormat {
                    form multipartForm {
                        flash.errorMessage = message
                        redirect action:"index", method:"GET"
                    }
                }
                return
            }
        }
        else {
            category.active = true
            message = 'Category has been Activated successfully'
        }
        categoryService.save(category)

        if(category?.active == false){
            auditLogsService.logAction('DeActivated','Category',category?.id,userService?.getLoggedInUser()?.username,(oldCatStatus as JSON).toString(false),(category as JSON).toString(false))
        }
        else {
            auditLogsService.logAction('Activated','Category',category?.id,userService?.getLoggedInUser()?.username,(oldCatStatus as JSON).toString(false),(category as JSON).toString(false))
        }

        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }

    def toggleSubCategoryStatus(Long toggleId) {
        if (toggleId == null) {
            notFound()
            return
        }
        String message
        SubCategory subcategory = SubCategory.findById(toggleId)
        def oldSubCatStatus = subcategory.clone()
        if(subcategory?.active) {
            List<VendorProductList> vendorProductLists = VendorProductList.findAllBySubCategory(subcategory)
            if(vendorProductLists?.size()==0){
                subcategory.active = false
                message = 'SubCategory has been Deactivated successfully'
            }
           else{
                message = "SubCategory " + subcategory?.name + " is associated with Vendor so it can not be deactivated"
                request.withFormat {
                    form multipartForm {
                        flash.errorMessage = message
                        redirect action:"index", method:"GET"
                    }
                }
                return

            }
        }
        else {
            subcategory.active = true
            message = 'SubCategory has been Activated successfully'
        }
        categoryService.saveSubCategory(subcategory)
        if(subcategory?.active == false){
            auditLogsService.logAction('DeActivated','SubCategory',subcategory?.id,userService?.getLoggedInUser()?.username,(oldSubCatStatus as JSON).toString(false),(subcategory as JSON).toString(false))
        }
        else {
            auditLogsService.logAction('Activated','SubCategory',subcategory?.id,userService?.getLoggedInUser()?.username,(oldSubCatStatus as JSON).toString(false),(subcategory as JSON).toString(false))
        }
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"subcategory", method:"GET"
            }
        }
    }

    def createUpload() {
        if (params?.f && params.f == "csv") {
            response.contentType = grailsApplication.config.grails.mime.types[params.f]
            response.setHeader("Content-disposition", "attachment; filename=Category_Upload.csv")
            List fields = ['Category Name','Category Description','Meta Title','Meta Description','Meta Keyword']
            exportService.export(params?.f as String, response.outputStream, [],fields, [:], [:], [:])
            return
        }
        else {
            respond new CategoryUpload()
        }
    }

    def saveUpload(CategoryUpload categoryUpload) {
        if (categoryUpload == null) {
            notFound()
            return
        }
        try {
            categoryService.saveCategoryUpload(categoryUpload)
        } catch (ValidationException e) {
            respond categoryUpload.errors, view: 'createUpload'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Category file uploaded successfully'
                redirect action: 'index'
            }
            '*' { respond categoryUpload, [status: CREATED] }
        }
    }

    def createSubCategoryUpload() {
        if (params?.f && params.f == "csv") {
            response.contentType = grailsApplication.config.grails.mime.types[params.f]
            response.setHeader("Content-disposition", "attachment; filename=subCategory_upload.csv")
            List fields = ['Category','SubCategory Name','SubCategory Description']
            exportService.export(params?.f as String, response.outputStream, [],fields, [:], [:], [:])
            return
        }

        respond new CategoryUpload(), view: 'subCategory/createUpload'

    }

    def saveSubCategoryUpload(CategoryUpload categoryUpload) {
        if (categoryUpload == null) {
            notFound()
            return
        }
        try {
            categoryService.saveSubCategoryUpload(categoryUpload)
        } catch (Exception e) {
            respond categoryUpload.errors, view: 'subCategory/createUpload'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'SubCategory file uploaded successfully'
                redirect action: 'subcategory'
            }
            '*' { respond categoryUpload, [status: CREATED] }
        }
    }
}
