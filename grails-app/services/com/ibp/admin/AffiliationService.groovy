package com.ibp.admin

import com.ibp.admin.commands.AffiliationUploadDataCommand
import com.ibp.admin.commands.ProductFileUpload
import com.ibp.admin.commands.AffiliationProductUploadCommand
import com.ibp.admin.csv.CsvReader
import com.ibp.admin.utils.AffiliationBuilder
import com.ibp.admin.utils.AffiliationProductBuilder
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import org.hibernate.SessionFactory

@Transactional
class AffiliationService {

    SessionFactory sessionFactory
    UserService userService
    GrailsApplication grailsApplication
    FileUploadService fileUploadService
    List<AffiliationProductCategory> productList
    List<Affiliation> affiliatonList

    def list(Map params) {
        Affiliation.createCriteria().list(params) {
            if (params?.name) {
                ilike('name', "%${params?.name}%")
            }
            if (params?.searchAffiliationStatus) {
                if(params?.searchAffiliationStatus?.equals("Activated")) {
                    eq('active', true)
                } else {
                    eq('active', false)
                }
            }
            order(params?.sort ?: 'dateCreated' as String, params?.order ?: 'desc' as String)
        }
    }

    Affiliation save(Affiliation affiliation) {
        affiliation.save(flush: true, failOnError: true)
    }

    Affiliation get(Long id) {
        Affiliation.read(id)
    }

    Affiliation delete(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }

    def listAffiliationProductCategory(Map params) {
        AffiliationProductCategory.createCriteria().list(params) {
            if(params?.affiliation) {
                Affiliation affiliation = Affiliation.findById(params?.affiliation?.toString()?.toLong())
                eq('affiliation' , affiliation)
            }
            if (params?.name) {
                ilike('name', "%${params?.name}%")
            }
            if (params?.searchAffiliationProductCategoryStatus) {
                if(params?.searchAffiliationProductCategoryStatus?.equals("Activated")) {
                    eq('active', true)
                } else {
                    eq('active', false)
                }
            }
            order(params?.sort ?: 'dateCreated' as String, params?.order ?: 'desc' as String)
        }
    }

    AffiliationProductCategory saveProductCategory(AffiliationProductCategory affiliation) {
        affiliation.save(flush: true, failOnError: true)
    }

    AffiliationProductCategory getProductCategory(Long id) {
        AffiliationProductCategory.read(id)
    }

    AffiliationProductCategory deleteProductCategory(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }

    @Transactional
    def saveProductUpload(ProductFileUpload productFileUpload) {
        if (!productFileUpload.validate()) {
            throw new ValidationException('Validation error in product upload', productFileUpload.errors)
        }
        String path = grailsApplication.config.getProperty('user.affiliation_upload.path')
        String filePath = fileUploadService.upload(productFileUpload.uploadFile , path)
        List<AffiliationProductUploadCommand> productRecord = CsvReader.readAffiliationProductCsv(filePath)
        log.info "[processProductUpload] productRecords size: ${productRecord?.size()}"
        if (!productRecord?.size()) {
            throw new RuntimeException("No data found in csv")
        }
        productList = AffiliationProductCategory.findAll()
        productRecord.collate(Constants.PRODUCT_PROCESS_BATCH_SIZE).each { List<AffiliationProductUploadCommand> records ->
            records.each { AffiliationProductUploadCommand record ->
                processProductRecord(record)
            }
            sessionFactory?.getCurrentSession()?.clear()
        }
        productList = []
    }

    void processProductRecord(AffiliationProductUploadCommand record) {
        Product.withTransaction {
            try {
                if (record?.validate()) {
                    Affiliation affiliation = Affiliation.findByName(record?.affiliation)
                    AffiliationProductCategory affiliationProductCategory = AffiliationProductCategory.findByName(record?.affiliationProduct)
                    if (affiliation) {
                        if (affiliationProductCategory) {
                            record?.updatedBy = userService?.getLoggedInUser()?.username
                            saveProductCategory(AffiliationProductBuilder.buildAffiliationProductList(record, affiliation, 'update'))
                        } else {
                            record?.createdBy = userService?.getLoggedInUser()?.username
                            record?.updatedBy = userService?.getLoggedInUser()?.username
                            saveProductCategory(AffiliationProductBuilder.buildAffiliationProductList(record, affiliation, 'create'))
                        }
                    } else {
                        String errorMessage = "[processFileUpload]"
                        log.error errorMessage
                    }
                }
            } catch (Exception ex) {
                log.error "[processFileUpload] ERROR: ${ex.message}", ex

            }
        }
    }

    @Transactional
    def saveAffiliationUpload(ProductFileUpload productFileUpload) {
        if (!productFileUpload.validate()) {
            throw new ValidationException('Validation error in product upload', productFileUpload.errors)
        }
        String path = grailsApplication.config.getProperty('user.affiliationProduct_upload.path')
        String filePath = fileUploadService.upload(productFileUpload.uploadFile , path)
        List<AffiliationUploadDataCommand> productRecord = CsvReader.readAffiliationCsv(filePath)
        log.info "[processProductUpload] productRecords size: ${productRecord?.size()}"
        if (!productRecord?.size()) {
            throw new RuntimeException("No data found in csv")
        }
        affiliatonList = Affiliation.findAll()
        productRecord.collate(Constants.PRODUCT_PROCESS_BATCH_SIZE).each { List<AffiliationUploadDataCommand> records ->
            records.each { AffiliationUploadDataCommand record ->
                processAffiliationRecord(record)
            }
            sessionFactory?.getCurrentSession()?.clear()
        }
        affiliatonList = []
    }

    void processAffiliationRecord(AffiliationUploadDataCommand record) {
        Product.withTransaction {
            try {
                if (record?.validate()) {
                    Affiliation affiliation = Affiliation.findByName(record?.affiliation)
                    if (affiliation) {
                            record?.updatedBy = userService?.getLoggedInUser()?.username
                            save(AffiliationBuilder.buildAffiliationProductList(record,  'update'))
                        } else {
                            record?.createdBy = userService?.getLoggedInUser()?.username
                            record?.updatedBy = userService?.getLoggedInUser()?.username

                        save(AffiliationBuilder.buildAffiliationProductList(record, 'create'))
                        }
                    } else {
                        String errorMessage = "[processFileUpload]"
                        log.error errorMessage
                    }
            } catch (Exception ex) {
                log.error "[processFileUpload] ERROR: ${ex.message}", ex

            }
        }
    }

}
