package com.ibp.admin

import com.ibp.admin.commands.CategoryUpload
import com.ibp.admin.commands.CategoryUploadDataCommand
import com.ibp.admin.commands.ProductFileUpload
import com.ibp.admin.commands.ProductUploadDataCommand
import com.ibp.admin.commands.SubCategoryUploadDataCommand
import com.ibp.admin.csv.CsvReader
import com.ibp.admin.utils.CategoryBuilder
import com.ibp.admin.utils.ProductBuilder
import com.ibp.admin.utils.SubCategoryBuilder
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import org.hibernate.SessionFactory


@Transactional
class CategoryService {
    SessionFactory sessionFactory
    GrailsApplication grailsApplication
    List<Category> categoryList
    List<SubCategory> subCategoryList
    FileUploadService fileUploadService
    def userService

    def list(Map params) {
//        log.info("inside category list....")
        Category.createCriteria().list(params) {
            if (params?.name) {
                ilike('name', "%${params?.name}%")
            }
            if (params?.searchCategoryStatus) {
                if(params?.searchCategoryStatus?.equals("Activated")) {
                    eq('active', true)
                } else {
                    eq('active', false)
                }
            }
            if (params?.searchSequenceStatus) {

                eq('sequence', Integer.parseInt(params?.searchSequenceStatus))
            }

            order(params?.sort ?: 'active', params?.order ?: 'desc')

        }
    }

    Category save(Category category) {
        category.save(flush: true, failOnError: true)
    }

    Category get(Long id) {
        Category.read(id)
    }

    Category delete(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }

    def listSubCategory(Map params) {
        SubCategory.createCriteria().list(params) {
            if(params?.categoryId) {
                Category category = Category.findById(params?.categoryId)
                eq('category' , category)
            }
            if (params?.subCategoryName) {
                ilike('name', "%${params?.subCategoryName}%")
            }
            if (params?.searchSUbCategoryStatus) {
                if(params?.searchSUbCategoryStatus?.equals("Activated")) {
                    eq('active', true)
                } else {
                    eq('active', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }

    SubCategory saveSubCategory(SubCategory category) {
        category.save(flush: true, failOnError: true)
    }

    SubCategory getSubCategory(Long id) {
        SubCategory.read(id)
    }

    SubCategory deleteSubCategory(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }

    @Transactional
    def saveCategoryUpload(CategoryUpload categoryUpload) {
        if (!categoryUpload.validate()) {
            throw new ValidationException('Validation error in category upload', categoryUpload.errors)
        }
        String path = grailsApplication.config.getProperty('user.category_upload.path')
        String filePath = fileUploadService.upload(categoryUpload.uploadFile , path)
        List<CategoryUploadDataCommand> categoryRecord = CsvReader.readCategoryCsv(filePath)
        log.info "[processCategoryUpload] categoryRecord size: ${categoryRecord?.size()}"
        if (!categoryRecord?.size()) {
            throw new RuntimeException("No data found in csv")
        }
        categoryList = Category.findAll()
        Config config = Config?.findByName(Constants.IbpConfigMap?.CATEGORY_PROCESS_BATCH_SIZE)
        Integer batchSize = Integer.parseInt(config?.value)
        categoryRecord.collate(batchSize).each { List<CategoryUploadDataCommand> records ->
            records.each { CategoryUploadDataCommand record ->
                processCategoryRecord(record)

            }
            sessionFactory?.getCurrentSession()?.clear()
        }
        categoryList = []
    }

    void processCategoryRecord(CategoryUploadDataCommand record) {
        Category.withTransaction {
            try {
                if (record?.validate()) {
                    Category category = Category?.findByName(record?.category)
                    if (category) {
                        record?.updatedBy = userService?.getLoggedInUser()?.username
                        save(CategoryBuilder.buildCategoryList(record, 'update'))
                    } else {
                        record?.createdBy = userService?.getLoggedInUser()?.username
                        record?.updatedBy = userService?.getLoggedInUser()?.username
                        save(CategoryBuilder.buildCategoryList(record, 'create'))
                    }
                }
            } catch (Exception ex) {
                log.error "[processCategoryFileUpload] ERROR: ${ex.message}", ex

            }
        }
    }

    def saveSubCategoryUpload(CategoryUpload categoryUpload) {
        if (!categoryUpload.validate()) {
            throw new ValidationException('Validation error in Subcategory upload', categoryUpload.errors)
        }
        String path = grailsApplication.config.getProperty('user.subCategory_upload.path')
        String filePath = fileUploadService.upload(categoryUpload.uploadFile , path)
        List<SubCategoryUploadDataCommand> categoryRecord = CsvReader.readSubCategoryCsv(filePath)
        log.info "[processSubCategoryUpload] SubcategoryRecord size: ${categoryRecord?.size()}"
        if (!categoryRecord?.size()) {
            throw new RuntimeException("No data found in csv")
        }
        subCategoryList = SubCategory.findAll()
        Config config = Config?.findByName(Constants.IbpConfigMap?.SUBCATEGORY_PROCESS_BATCH_SIZE)
        Integer batchSize = Integer.parseInt(config?.value)
        categoryRecord.collate(batchSize).each { List<SubCategoryUploadDataCommand> records ->
            records.each { SubCategoryUploadDataCommand record ->
                processSubCategoryRecord(record)
            }
            sessionFactory?.getCurrentSession()?.clear()
        }
        subCategoryList = []
    }

    void processSubCategoryRecord(SubCategoryUploadDataCommand record) {
        Category.withTransaction {
            try {
                if (record?.validate()) {
                    SubCategory subCategory = SubCategory?.findByName(record?.subCategory)
                    Category category = Category?.findByName(record?.category)
                    if (subCategory) {
                        record?.updatedBy = userService?.getLoggedInUser()?.username
                        saveSubCategory(SubCategoryBuilder.buildSubCategoryList(record, category, 'update'))
                    } else {
                        record?.createdBy = userService?.getLoggedInUser()?.username
                        record?.updatedBy = userService?.getLoggedInUser()?.username
                        saveSubCategory(SubCategoryBuilder.buildSubCategoryList(record, category, 'create'))
                    }
                }
            } catch (Exception ex) {
                log.error "[processCategoryFileUpload] ERROR: ${ex.message}", ex

            }
        }
    }
}
