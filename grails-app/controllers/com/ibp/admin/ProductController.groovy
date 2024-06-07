package com.ibp.admin

import com.ibp.admin.commands.ProductCommand
import com.ibp.admin.commands.ProductFileUpload
import com.ibp.admin.utils.IbpHubUtils
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class ProductController {

    def productService
    def categoryService
    def exportService
    AwsService awsService
    VendorsService vendorsService
    UserService userService
    AuditLogsService auditLogsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def productList = productService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    productList : productList,
                    productCount: productList.totalCount
            ]
            return
        }
        respond productList, model: [productCount: productList.totalCount]
    }

    def show(Long id) {
        respond productService.get(id)
    }

    def create() {
        respond new Product(params)
    }

    def save(ProductCommand productCommand) {

        if (productCommand == null) {
            notFound()
            return
        }
        Product product = new Product()
        try {
            bindData(product,productCommand)
            if (!product?.productId) {
                product?.productId = UUID.randomUUID().toString()
            }
            Category category = Category.findById(productCommand?.categoryId)
            product?.category = category
            SubCategory subCategory = SubCategory.findById(productCommand?.subCategoryId)
            product?.subCategory = subCategory
            if (productCommand?.imageFile?.size > 0) {
                String bannerUrl = awsService.uploadImageToS3(productCommand?.imageFile , 'product' , product?.productId)
                product?.bannerUrl = bannerUrl
            }
            product?.createdBy = userService?.getLoggedInUser()?.username
            product?.updatedBy = userService?.getLoggedInUser()?.username

            String title = product?.name
            String url = IbpHubUtils.convertToSlug(title)
            product?.displayName = url

            productService.save(product)
            auditLogsService.logAction('Save','Product',product?.id,userService?.getLoggedInUser()?.username,(product as JSON).toString(false),(product as JSON).toString(false))
        } catch (ValidationException e) {
            respond product.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Product created successfully' //message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action: 'index'
            }
            '*' { respond category, [status: CREATED] }
        }
    }

    def edit(Long id) {
        Product product = productService.get(id)
        respond product
    }

    def update(ProductCommand productCommand) {
        if (productCommand == null) {
            notFound()
            return
        }
        Product product = Product.findById(params?.id)
        if (!product) {
            notFound()
            return
        }
        def oldProduct = product.clone()
        try {
            String bannerUrl = product?.bannerUrl
            bindData(product,productCommand)

            Category category = Category.findById(productCommand?.categoryId)
            product?.category = category
            SubCategory subCategory = SubCategory.findById(productCommand?.subCategoryId)
            product?.subCategory = subCategory
            if (productCommand?.imageFile?.size > 0) {
                bannerUrl = awsService.uploadImageToS3(productCommand?.imageFile, 'product', product?.productId)
                product?.bannerUrl = bannerUrl
            }
            else if (productCommand?.imageFile?.size == 0 && bannerUrl) {
                product?.bannerUrl = bannerUrl
            }
            else {
                awsService.removeImageFromS3(bannerUrl , 'prod/product' , product?.productId)
            }
            product?.updatedBy = userService?.getLoggedInUser()?.username
            String title = product?.name
            String url = IbpHubUtils.convertToSlug(title)
            product?.displayName = url
            productService.save(product)
            auditLogsService.logAction('Update','Product',userService?.getLoggedInUser()?.username,product?.id,(oldProduct as JSON).toString(false),(product as JSON).toString(false))

            List<VendorProductList> vendorProductLists = VendorProductList.findAllByProduct(product)
            for(VendorProductList vendorProductList:vendorProductLists){
                vendorProductList?.productName = product?.name
                vendorsService.saveVendorProductList(vendorProductList)

                VendorBusinessDetails vendorBusinessDetails = VendorBusinessDetails.findById(vendorProductList?.vendorBusinessId)
                String productNameCommaSep = vendorBusinessDetails?.productName
                String[] productNameArray = productNameCommaSep?.split(",")
                if(productNameArray && productNameArray?.length > 0) {
                    List<String> ProductList = Arrays.asList(productNameArray)
                    // Iterate over the list
                    for (int i = 0; i < ProductList.size(); i++) {
                        // Check if the current element matches the search string
                        if (ProductList.get(i).equalsIgnoreCase(productCommand?.oldName)) {
                            // Replace the element with the new string
                            ProductList.set(i, product?.name)
                        }
                    }
                    String commaSeparatedProductNames = String.join(",", ProductList)
                    log.info("commaSeparatedProductNames : "+commaSeparatedProductNames)
                    vendorBusinessDetails.productName = commaSeparatedProductNames
                    vendorsService.saveVendorBusinessDetails(vendorBusinessDetails)
                }
            }
        } catch (ValidationException e) {
            respond product.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Product updated successfully' //, args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action: 'index'
            }
            '*'{ respond product, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        productService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = 'Product deleted successfully' //message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
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

    def onSelectCategory() {
        Category category = Category?.findById(params?.category)
        SubCategory subCategory = SubCategory?.findById(params?.subCategory)
        render template: 'searchTemplates/subCategory',
                model: [subCategory: SubCategory?.findAllByCategoryAndActive(category, true), subCategoryInstance: subCategory]
    }

    def createUpload() {
        if (params?.f && params.f == "csv") {
            response.contentType = grailsApplication.config.grails.mime.types[params.f]
            response.setHeader("Content-disposition", "attachment; filename=Product_Upload.csv")
            List fields = ['Category','Details Category','Product Name','Product Description','Meta Title','Meta Description','Meta Keyword']
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
            productService.saveProductUpload(productFileUpload)
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

    def toggleStatus(Long toggleId) {
        if (toggleId == null) {
            notFound()
            return
        }
        String message
        Product product = Product.findById(toggleId)
        def oldProductStatus = product.clone()
        if(product?.active) {
            List<VendorProductList> vendorProductLists = VendorProductList.findAllByProduct(product)
            if(vendorProductLists?.size()==0){
                product.active = false
                message = 'Product has been Deactivated successfully'
            }
            else{
                message = "Product " + product?.name + " is associated with Vendor so it can not be deactivated"
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
            product.active = true
            message = 'Product has been Activated successfully'
        }

        productService.save(product)

        if(product?.active == false){
            auditLogsService.logAction('DeActivated','Product',product?.id,userService?.getLoggedInUser()?.username,(oldProductStatus as JSON).toString(false),(product as JSON).toString(false))
        }
        else {
            auditLogsService.logAction('Activated','Product',product?.id,userService?.getLoggedInUser()?.username,(oldProductStatus as JSON).toString(false),(product as JSON).toString(false))
        }

        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }

}
