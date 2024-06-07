package com.ibp.admin

import com.ibp.admin.commands.CategoryUpload
import com.ibp.admin.commands.ProductFileUpload
import com.ibp.admin.utils.IbpHubUtils
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class AffiliationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    UserService userService
    def affiliationService
    def exportService
    AuditLogsService auditLogsService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        if(params?.affiliationProduct) {
            def affiliationList = affiliationService.listAffiliationProductCategory(params)
            if (request.xhr) {
                render template: 'productCategory/list', model: [
                        affiliationList : affiliationList,
                        affiliationCount: affiliationList.totalCount
                ]
                return
            }
            render view: 'productCategory/affiliationProduct', model: [
                    affiliationList : affiliationList,
                    affiliationCount: affiliationList.totalCount
            ]
        } else {
            def affiliationList = affiliationService.list(params)
            if (request.xhr) {
                render template: 'list', model: [
                        affiliationList : affiliationList,
                        affiliationCount: affiliationList.totalCount
                ]
                return
            }
            respond affiliationList, model: [affiliationCount: affiliationList.totalCount]
        }
    }

    def show(Long id) {
        respond affiliationService.get(id)
    }

    def create() {
        respond new Affiliation(params)
    }

    def save(Affiliation affiliation) {
        if (affiliation == null) {
            notFound()
            return
        }
        if (affiliation?.hasErrors()) {
            render view: 'create', model: [affiliation: affiliation]
            return
        }
        try {
            if (!affiliation?.affiliationId) {
                affiliation?.affiliationId = UUID.randomUUID().toString()
            }
            affiliation?.createdBy = userService?.getLoggedInUser()?.username
            affiliation?.updatedBy = userService?.getLoggedInUser()?.username
            String title = affiliation?.name
            String url = IbpHubUtils.convertToSlug(title)
            affiliation?.displayName = url
            affiliationService.save(affiliation)
            auditLogsService.logAction('Save','Affiliation',affiliation?.id,userService?.getLoggedInUser()?.username,(affiliation as JSON).toString(false),(affiliation as JSON).toString(false))
        } catch (ValidationException e) {
            respond affiliation.errors, view:'create'
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = 'Affiliation created successfully' //message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action: 'index'
            }
            '*' { respond affiliation, [status: CREATED] }
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

    def edit(Long id) {
        Affiliation affiliation = affiliationService.get(id)
        respond affiliation
    }

    def update(Affiliation affiliation) {

        if (affiliation == null) {
            notFound()
            return
        }
        Affiliation affiliationInstance= Affiliation.findById(params?.id)

        if (!affiliationInstance) {
            notFound()
            return
        }
        def oldAffiliation = affiliation.clone()
        try {
            bindData(affiliationInstance, affiliation)
            affiliation?.updatedBy = userService?.getLoggedInUser()?.username
            String title = affiliation?.name
            String url = IbpHubUtils.convertToSlug(title)
            affiliation?.displayName = url
            affiliationService.save(affiliationInstance)
            auditLogsService.logAction('Update','Affiliation',affiliationInstance?.id,userService?.getLoggedInUser()?.username,(oldAffiliation as JSON).toString(false),(affiliationInstance as JSON).toString(false))
        } catch (ValidationException ignored) {
            respond affiliationInstance.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'AffiliationInstance updated successfully'
                redirect action: 'index'
            }
            '*'{ respond affiliationInstance, [status: OK] }
        }
    }

    def affiliationProduct(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def affiliationList = affiliationService.listAffiliationProductCategory(params)
        if (request.xhr) {
            render template: 'productCategory/list', model: [
                    affiliationList : affiliationList,
                    affiliationCount: affiliationList.totalCount
            ]
            return
        }
        render view: 'productCategory/affiliationProduct', model: [
                affiliationList : affiliationList,
                affiliationCount: affiliationList.totalCount
        ]
    }

    def showProductCategory(Long id) {
        respond affiliationService.getProductCategory(id)
    }

    def createProductCategory() {
        respond new AffiliationProductCategory(params), view: 'productCategory/create'
    }

    def saveProductCategory(AffiliationProductCategory affiliationProductCategory) {
        if (affiliationProductCategory == null) {
            notFound()
            return
        }
      try {
          if (!affiliationProductCategory?.affiliationProductCategoryId) {
              affiliationProductCategory?.affiliationProductCategoryId = UUID.randomUUID().toString()
          }
          affiliationProductCategory?.createdBy = userService?.getLoggedInUser()?.username
          affiliationProductCategory?.updatedBy = userService?.getLoggedInUser()?.username

          String title = affiliationProductCategory?.name
          String url = IbpHubUtils.convertToSlug(title)
          affiliationProductCategory?.displayName = url


          affiliationService.saveProductCategory(affiliationProductCategory)
        } catch (ValidationException e) {
            respond affiliationProductCategory.errors, view:'productCategory/create'
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = 'Affiliation Product Category created successfully'
                redirect action: 'affiliationProduct'
            }
            '*' { respond affiliationProductCategory, [status: CREATED] }
        }
    }

    def editProductCategory(Long id) {
        AffiliationProductCategory affiliationProductCategory = affiliationService.getProductCategory(id)
        render view: 'productCategory/edit', model: [
                affiliationProductCategory : affiliationProductCategory
        ]
    }

    def updateProductCategory(AffiliationProductCategory affiliationProductCategory) {
        if (affiliationProductCategory == null) {
            notFound()
            return
        }
        AffiliationProductCategory affiliationInstance= AffiliationProductCategory.findById(params?.id)
        if (!affiliationInstance) {
            notFound()
            return
        }
        try {
            bindData(affiliationInstance, affiliationProductCategory)
            affiliationProductCategory?.updatedBy = userService?.getLoggedInUser()?.username
            String title = affiliationProductCategory?.name
            String url = IbpHubUtils.convertToSlug(title)
            affiliationProductCategory?.displayName = url
            affiliationService.saveProductCategory(affiliationInstance)
        } catch (ValidationException ignored) {
            respond affiliationInstance.errors, view:'productCategory/edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Affiliation  Product Category updated successfully'
                redirect action: 'affiliationProduct'
            }
            '*'{ respond affiliationInstance, [status: OK] }
        }
    }

    def deleteProductCategory(Long id) {
        if (id == null) {
            notFound()
            return
        }

        affiliationService.deleteProductCategory(id)

        request.withFormat {
            form multipartForm {
                flash.message = 'Affiliation  Product Category deleted successfully'
                redirect action:"affiliationProduct", method:"GET"
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
        Affiliation affiliation = Affiliation.findById(toggleId)
        def oldAffiliationStatus = affiliation.clone()
        if(affiliation?.active) {
            affiliation.active = false
            message = 'Affiliation has been Deactivated successfully'
        }
        else {
            affiliation.active = true
            message = 'Affiliation has been Activated successfully'
        }
        affiliationService.save(affiliation)

        if(affiliation?.active == false){
            auditLogsService.logAction('DeActivated','Affiliation',affiliation?.id,userService?.getLoggedInUser()?.username,(oldAffiliationStatus as JSON).toString(false),(affiliation as JSON).toString(false))
        }
        else {
            auditLogsService.logAction('Activated','Affiliation',affiliation?.id,userService?.getLoggedInUser()?.username,(oldAffiliationStatus as JSON).toString(false),(affiliation as JSON).toString(false))
        }
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }

    def toggleProductStatus(Long toggleId) {
        if (toggleId == null) {
            notFound()
            return
        }
        String message
        AffiliationProductCategory category = AffiliationProductCategory.findById(toggleId)
        if(category?.active) {
            category.active = false
            message = 'AffiliationProductCategory has been Deactivated successfully'
        }
        else {
            category.active = true
            message = 'AffiliationProductCategory has been Activated successfully'
        }
        affiliationService.saveProductCategory(category)
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"affiliationProduct", method:"GET"
            }
        }
    }

    def createUpload() {
        if (params?.f && params.f == "csv") {
            response.contentType = grailsApplication.config.grails.mime.types[params.f]
            response.setHeader("Content-disposition", "attachment; filename=Affiliation_upload.csv")
            List fields = ['Sr No','Affiliation', 'Website Link', 'Image Path']
            exportService.export(params?.f as String, response.outputStream, [],fields, [:], [:], [:])
            return
        }
        else {
            respond new ProductFileUpload()
        }
    }

    def saveUpload(ProductFileUpload productFileUpload) {
        if (productFileUpload == null) {
            notFound()
            return
        }
        try {
            affiliationService.saveAffiliationUpload(productFileUpload)
        } catch (ValidationException e) {
            respond productFileUpload.errors, view: 'createUpload'
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = 'Product file uploaded successfully'
                redirect action: 'index'
            }
            '*' { respond productFileUpload, [status: CREATED] }
        }
    }

    def creatAffiliationProductUpload() {
        if (params?.f && params.f == "csv") {
            response.contentType = grailsApplication.config.grails.mime.types[params.f]
            response.setHeader("Content-disposition", "attachment; filename=AffiliationProduct_upload.csv")
            List fields = ['Sr No','Affiliation','Affiliation Product Name','Website Link','Image Path']
            exportService.export(params?.f as String, response.outputStream, [],fields, [:], [:], [:])
            return
        }
        else {
            respond new ProductFileUpload(), view: 'productCategory/createUpload'
        }

    }

    def saveAffiliationProductUpload(ProductFileUpload productFileUpload) {
        if (productFileUpload == null) {
            notFound()
            return
        }
        try {
            affiliationService.saveProductUpload(productFileUpload)
        } catch (ValidationException e) {
            respond productFileUpload.errors, view: 'productCategory/createUpload'
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = 'Product file uploaded successfully'
                redirect action: 'affiliationProduct'
            }
            '*' { respond productFileUpload, [status: CREATED] }
        }
    }
}
