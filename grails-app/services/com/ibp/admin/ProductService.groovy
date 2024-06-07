package com.ibp.admin

import com.ibp.admin.commands.ProductFileUpload
import com.ibp.admin.commands.ProductUploadDataCommand
import com.ibp.admin.commands.VendorUploadDataCommand
import com.ibp.admin.csv.CsvReader
import com.ibp.admin.enums.VendorDataStatus
import com.ibp.admin.utils.ProductBuilder
import com.ibp.admin.utils.VendorBuilder
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import org.hibernate.SessionFactory

@Transactional
class ProductService {

    GrailsApplication grailsApplication
    FileUploadService fileUploadService
    SessionFactory sessionFactory
    List<Product> productList
    def userService

    def list(Map params) {
        Product.createCriteria().list(params) {
            if(params?.categoryId) {
                Category category = Category.findById(params?.categoryId)
                eq('category' , category)
            }
            if(params?.subCategoryId) {
                SubCategory subCategory = SubCategory.findById(params?.subCategoryId)
                eq('subCategory' , subCategory)
            }
            if (params?.name) {
                ilike('name', "%${params?.name}%")
            }
            if (params?.searchProductStatus) {
                if(params?.searchProductStatus?.equals("Activated")) {
                    eq('active', true)
                } else {
                    eq('active', false)
                }
            }
            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }

    Product save(Product product) {
        product.save(flush: true, failOnError: true)
    }

    Product get(Long id) {
        Product.read(id)
    }

    Product delete(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }

    @Transactional
    def saveProductUpload(ProductFileUpload productFileUpload) {
        if (!productFileUpload.validate()) {
            throw new ValidationException('Validation error in product upload', productFileUpload.errors)
        }
        String path = grailsApplication.config.getProperty('user.product_upload.path')
        String filePath = fileUploadService.upload(productFileUpload.uploadFile , path)
        List<ProductUploadDataCommand> productRecord = CsvReader.readProductCsv(filePath)
        log.info "[processProductUpload] productRecords size: ${productRecord?.size()}"
        if (!productRecord?.size()) {
            throw new RuntimeException("No data found in csv")
        }
        productList = Product.findAll()
        Config config = Config?.findByName(Constants.IbpConfigMap.PRODUCT_PROCESS_BATCH_SIZE)
        Integer batchSize = config?.value ? Integer.parseInt(config.value) : Constants.PRODUCT_PROCESS_BATCH_SIZE
        productRecord.collate(batchSize).each { List<ProductUploadDataCommand> records ->
            records.each { ProductUploadDataCommand record ->
                processProductRecord(record)
            }
            sessionFactory?.getCurrentSession()?.clear()
        }
        productList = []
    }

    void processProductRecord(ProductUploadDataCommand record) {
        Product.withTransaction {
            try {
                if (record?.validate()) {
                    Product product = Product?.findByName(record?.product)
                    Category category = Category.findByName(record?.category)
                    SubCategory subCategory = SubCategory.findByName(record?.subCategory)
                    if (category && subCategory) {
                        if (product) {
                            record?.updatedBy = userService?.getLoggedInUser()?.username
                            save(ProductBuilder.buildProductList(record, category, subCategory, 'update'))
                        } else {
                            record?.createdBy = userService?.getLoggedInUser()?.username
                            record?.updatedBy = userService?.getLoggedInUser()?.username
                            save(ProductBuilder.buildProductList(record, category, subCategory, 'create'))
                        }
                    } else {
                        String errorMessage = "[processFileUpload]::[category]::ERROR::${category?.name}=>${subCategory?.name}=>${product?.name}"
                        log.error errorMessage
                    }
                }
            } catch (Exception ex) {
                log.error "[processFileUpload] ERROR: ${ex.message}", ex

            }
        }
    }

}
