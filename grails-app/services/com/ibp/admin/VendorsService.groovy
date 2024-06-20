package com.ibp.admin

import com.ibp.admin.commands.VendorFileUpload
import com.ibp.admin.commands.VendorUploadDataCommand
import com.ibp.admin.csv.CsvReader
import com.ibp.admin.dto.WatiCustomParamsDto
import com.ibp.admin.dto.WatiVendorBusinessDetailsDto
import com.ibp.admin.enums.VendorBusinessStatus
import com.ibp.admin.enums.VendorDataStatus
import com.ibp.admin.enums.VendorUploadStatus
import com.ibp.admin.utils.IbpHubUtils
import com.ibp.admin.utils.VendorBuilder
import grails.converters.JSON
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.validation.ValidationException
import org.hibernate.SessionFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.multipart.MultipartFile

class VendorsService implements UserDetailsService{

    def messageSource
    FileUploadService fileUploadService
    SpringSecurityService springSecurityService
    UserService userService
    SessionFactory sessionFactory
    List<Category> categoryList
    List<SubCategory> subCategoryList
    List<Product> productList
    List<Association> associationList
    GrailsApplication grailsApplication
    AwsService awsService
    AuditLogsService auditLogsService
    WatiIntegrationService watiIntegrationService

    @Transactional(readOnly = true)
    def list(Map params) {
        List<VendorBusinessDetails> vendorBusinessList = VendorBusinessDetails.createCriteria().list(params) {
            if (params?.name) {
                or {
                    ilike('businessName', "%${params?.name}%")
                    ilike('username', "%${params?.name}%")
                    ilike('primaryPhoneNumber', "%${params?.name}%")
                    ilike('businessEmail', "%${params?.name}%")
                }
            }
            if (params?.verified) {
                boolean isVerified = params?.verified?.equals('true')
                eq('isVerified', isVerified)
            }
            if (params?.businessType) {
                ilike('businessType', "%${params?.businessType}%")
            }
            if (params?.category) {
                category {
                    ilike('name', "%${params.category}%")
                }
            }
            if (params?.vendorStatus) {
                if (params?.vendorStatus?.equals(VendorBusinessStatus.SUBMITTED.toString())) {
                    eq('status', VendorBusinessStatus.SUBMITTED.value)
                } else if (params?.vendorStatus?.equals(VendorBusinessStatus.VERIFIED.toString())) {
                    eq('status', VendorBusinessStatus.VERIFIED.value)
                } else if (params?.vendorStatus?.equals(VendorBusinessStatus.ON_HOLD.toString())) {
                    eq('status', VendorBusinessStatus.ON_HOLD.value)
                } else {
                    eq('status', VendorBusinessStatus.IN_REVIEW.value)
                }
            }
            order("dateCreated", "desc")
        } as List<VendorBusinessDetails>
//        List<VendorBusinessDetailsDto> list = []
//        vendorBusinessList?.each { vendorBusinessDetails ->
//            VendorBusinessDetailsDto vendorBusinessDetailsDto = new VendorBusinessDetailsDto()
//            vendorBusinessDetailsDto.with {
//                id = vendorBusinessDetails.id
//                vendorId = vendorBusinessDetails.vendor.id
//                businessName = vendorBusinessDetails.businessName
//                businessEmail = vendorBusinessDetails?.businessEmail
//                primaryPhoneNumber = vendorBusinessDetails?.primaryPhoneNumber
//                businessType = vendorBusinessDetails?.businessType
//                city = vendorBusinessDetails?.city
//                district = vendorBusinessDetails?.district
//                state = vendorBusinessDetails?.state
//                paid = PaymentStatus?.parsePaymentStatus(vendorBusinessDetails?.paid)?.name
//                status = UserStatus?.parseUserStatus(vendorBusinessDetails?.status)?.name
//                lastUpdated = vendorBusinessDetails?.lastUpdatedText
//                isVerified = vendorBusinessDetails?.isVerified
//                category = VendorProductList?.findByVendorBusiness(vendorBusinessDetails)?.category?.name
//            }
//            list.add(vendorBusinessDetailsDto)
//        }
//        if (params?.category) {
//            list = list?.findAll {
//                it?.category?.toLowerCase()?.contains(params.category.toString().toLowerCase())
//            }
//        }
        vendorBusinessList
    }

    @Transactional(readOnly = true)
    def listOfVerifiedByVendors(Map params) {
        List<VendorBusinessDetails> vendorBusinessList = VendorBusinessDetails.createCriteria().list(params) {
            if (params?.name) {
                or {
                    ilike('businessName', "%${params?.name}%")
                    ilike('username', "%${params?.name}%")
                    ilike('primaryPhoneNumber', "%${params?.name}%")
                    ilike('businessEmail', "%${params?.name}%")
                }
            }
            if (params?.verified) {
                boolean isVerified = params?.verified?.equals('true')
                eq('isVerified', isVerified)
            }
            if (params?.verifiedByVendors) {
                boolean isVerifiedByVendors = params?.verifiedByVendors?.equals('true')
                eq('modifiedByVendor', isVerifiedByVendors)
            }
            if (params?.businessType) {
                ilike('businessType', "%${params?.businessType}%")
            }
            if (params?.category) {
                category {
                    ilike('name', "%${params.category}%")
                }
            }
            if (params?.vendorStatus) {
                if (params?.vendorStatus?.equals(VendorBusinessStatus.SUBMITTED.toString())) {
                    eq('status', VendorBusinessStatus.SUBMITTED.value)
                } else if (params?.vendorStatus?.equals(VendorBusinessStatus.VERIFIED.toString())) {
                    eq('status', VendorBusinessStatus.VERIFIED.value)
                } else if (params?.vendorStatus?.equals(VendorBusinessStatus.ON_HOLD.toString())) {
                    eq('status', VendorBusinessStatus.ON_HOLD.value)
                } else {
                    eq('status', VendorBusinessStatus.IN_REVIEW.value)
                }
            }
            order("dateCreated", "desc")
        } as List<VendorBusinessDetails>
//        List<VendorBusinessDetailsDto> list = []
//        vendorBusinessList?.each { vendorBusinessDetails ->
//            VendorBusinessDetailsDto vendorBusinessDetailsDto = new VendorBusinessDetailsDto()
//            vendorBusinessDetailsDto.with {
//                id = vendorBusinessDetails.id
//                vendorId = vendorBusinessDetails.vendor.id
//                businessName = vendorBusinessDetails.businessName
//                businessEmail = vendorBusinessDetails?.businessEmail
//                primaryPhoneNumber = vendorBusinessDetails?.primaryPhoneNumber
//                businessType = vendorBusinessDetails?.businessType
//                city = vendorBusinessDetails?.city
//                district = vendorBusinessDetails?.district
//                state = vendorBusinessDetails?.state
//                paid = PaymentStatus?.parsePaymentStatus(vendorBusinessDetails?.paid)?.name
//                status = UserStatus?.parseUserStatus(vendorBusinessDetails?.status)?.name
//                lastUpdated = vendorBusinessDetails?.lastUpdatedText
//                isVerified = vendorBusinessDetails?.isVerified
//                category = VendorProductList?.findByVendorBusiness(vendorBusinessDetails)?.category?.name
//            }
//            list.add(vendorBusinessDetailsDto)
//        }
//        if (params?.category) {
//            list = list?.findAll {
//                it?.category?.toLowerCase()?.contains(params.category.toString().toLowerCase())
//            }
//        }
        vendorBusinessList
    }

    @Transactional(readOnly = true)
    def uploadList(Map params) {
        VendorUpload.createCriteria().list(params) {
            if (params?.fileName) {
                ilike('fileName', "%${params?.fileName}%")
            }
            if (params?.status) {
                eq('status', Byte.parseByte(params?.status as String))
            }
        }
    }

    @Transactional
    Vendors save(Vendors vendors) {
        vendors.save(flush: true, failOnError: true)


    }

    @Transactional
    VendorBusinessDetails saveVendorBusinessDetails(VendorBusinessDetails vendorBusinessDetails) {
        vendorBusinessDetails.save(flush: true, failOnError: true)
    }

    @Transactional
    UserClient saveUserClient(UserClient userClient) {
        userClient.save(flush: true, failOnError: true)
    }

    @Transactional
    VendorProductList saveVendorProductList(VendorProductList vendorProductList) {
        vendorProductList.save(flush: true, failOnError: true)
    }

    @Transactional
    VendorAssociationList saveVendorAssociationList(VendorAssociationList vendorAssociationList) {
        vendorAssociationList.save(flush: true, failOnError: true)
    }

    @Transactional
    def clearVendorProductList(VendorBusinessDetails vendorBusinessDetails) {
        def vendorProductList = VendorProductList.findAllByVendorBusiness(vendorBusinessDetails)
        vendorProductList?.each {
            def oldVendorProductList = it.clone()

            it.delete(flush: true)
            auditLogsService.logAction('Delete',it.class.simpleName,it?.id,userService?.getLoggedInUser()?.username,(oldVendorProductList as JSON).toString(false),({} as JSON)?.toString(false))

        }
    }

    @Transactional
    def clearVendorAssociationList(VendorBusinessDetails vendorBusinessDetails) {
        def vendorAssociationList = VendorAssociationList.findAllByVendorBusiness(vendorBusinessDetails)
        vendorAssociationList?.each {
            def oldVendorAssList = it.clone()
            it.delete(flush: true)
            auditLogsService.logAction('Delete',it.class.simpleName,it?.id,userService?.getLoggedInUser()?.username,(oldVendorAssList as JSON).toString(false),({} as JSON)?.toString(false))
        }
    }

    VendorUploadData correctVendorUploadData(VendorUploadData vendorUploadData) {
        categoryList = Category.findAll()
        subCategoryList = SubCategory.findAll()
        productList = Product.findAll()
        processVendorRecord(vendorUploadData, vendorUploadData?.vendorUpload, true)
        VendorUploadData.get(vendorUploadData?.id)
    }

    @Transactional
    VendorUpload saveVendorUpload(VendorUpload vendorUpload) {
        vendorUpload.save(flush: true, failOnError: true)
    }

    @Transactional
    VendorUpload saveVendorUpload(VendorFileUpload vendorFileUpload) {
        if (!vendorFileUpload.validate()) {
            throw new ValidationException('Validation error in vendor upload', vendorFileUpload.errors)
        }
        String path = grailsApplication.config.getProperty('user.file_upload.path')
        String filePath = fileUploadService.upload(vendorFileUpload.uploadFile, path)
        VendorUpload vendorUpload = new VendorUpload(
                description: vendorFileUpload.description,
                fileName: vendorFileUpload.uploadFile.originalFilename,
                uploadAction: vendorFileUpload?.uploadAction,
                contentType: vendorFileUpload.uploadFile.contentType,
                size: vendorFileUpload.uploadFile.size,
                filePath: filePath,
                uploadedBy: userService.loggedInUser
        )
        vendorUpload.save(flush: true, failOnError: true)
    }

    void processVendorUpload(VendorUpload vendorUpload) {
        try {
            vendorUpload.status = VendorUploadStatus.PROCESSING.status
            List<VendorUploadData> vendorRecords = CsvReader.readVendorCsv(vendorUpload.filePath)
            log.info "[processVendorUpload] vendorRecords size: ${vendorRecords?.size()}"
            vendorUpload.totalCount = vendorRecords?.size()
            vendorUpload.successCount = 0
            vendorUpload.failedCount = 0
            saveVendorUpload(vendorUpload)
            if (!vendorRecords?.size()) {
                throw new RuntimeException("No data found in csv")
            }
            categoryList = Category.findAll()
            subCategoryList = SubCategory.findAll()
            productList = Product.findAll()
            associationList = Association.findAll()
            Config config = Config?.findByName(Constants.IbpConfigMap.VENDOR_PROCESS_BATCH_SIZE)
            Integer batchSize = config?.value ? Integer.parseInt(config.value) : Constants.VENDOR_PROCESS_BATCH_SIZE
            vendorRecords.collate(batchSize).each { List<VendorUploadData> records ->
                records.each { VendorUploadData record ->
                    record.vendorUpload = vendorUpload
                    processVendorRecord(record, vendorUpload, false)
                }
                sessionFactory?.getCurrentSession()?.clear()
                saveVendorUpload(vendorUpload)
            }
            vendorUpload.status = VendorUploadStatus.PROCESSED.status
        } catch (Exception ex) {
            log.error "[processVendorUpload] ERROR: ${ex.message}", ex
            vendorUpload.status = VendorUploadStatus.FAILED.status
            vendorUpload.error = ex.message
        }
        try {
            saveVendorUpload(vendorUpload)
        } catch (Exception ex) {
            log.error "[processVendorUpload] ERROR: ${ex.message}", ex
            throw ex
        }
        categoryList = []
        subCategoryList = []
        productList = []
    }

    static VendorUploadDataCommand setVendorUploadDataCommand (VendorUploadData vendorUploadData) {
        VendorUploadDataCommand vendorUploadDataCommand = new VendorUploadDataCommand()
        vendorUploadDataCommand.with {
            category = vendorUploadData?.category
            subCategory = vendorUploadData?.subCategory
            product = vendorUploadData?.product
            businessName = vendorUploadData?.businessName
            address = vendorUploadData?.address
            city = vendorUploadData?.city
            state = vendorUploadData?.state
            district = vendorUploadData?.district
            primaryPhoneNumber = vendorUploadData?.primaryPhoneNumber
            businessEmail = vendorUploadData?.businessEmail
        }
        vendorUploadDataCommand
    }

    void processVendorRecord(VendorUploadData record, VendorUpload vendorUpload, boolean isManual) {
        Vendors.withNewTransaction {
            try {
                VendorUploadDataCommand vendorUploadDataCommand = setVendorUploadDataCommand(record)
                if (vendorUploadDataCommand?.validate()) {
                    Category category = categoryList?.find {it?.name?.equalsIgnoreCase(record?.category)}

                    SubCategory subCategory = subCategoryList?.find {it.name?.equalsIgnoreCase(record?.subCategory?.trim())
                            && it?.category?.id == category?.id}


                    String[] productArray = record?.product?.trim()?.split(",")
                    List<Product> productObjList = new ArrayList<Product>()
                    productArray?.each {productObj ->
                        Product product = productList?.find {   it?.name?.equalsIgnoreCase(productObj?.trim()) &&
                                it?.category?.id == category?.id && it?.subCategory?.id == subCategory?.id}

                        if (product) {
                            productObjList.add(product)
                        } else {
                            if(category && subCategory) {
                                product = new Product()
                                product.name = productObj?.trim()
                                product.metaKeyword = productObj?.trim()
                                product.active = true
                                product.productId = UUID.randomUUID().toString()
                                product.metaDescription = productObj?.trim()
                                product.metaTitle = productObj?.trim()
                                product.category = category
                                product.subCategory = subCategory
                                product.description = productObj?.trim()
                                String title = product?.name
                                String url = IbpHubUtils.convertToSlug(title)
                                product.displayName = url
                                product.createdBy = userService?.getLoggedInUser()?.username
                                product.updatedBy = userService?.getLoggedInUser()?.username
                                product.save(flush: true, failOnError: true)
                                productObjList.add(product)
                                productList.add(product)
                            }
                        }
                    }

                    String[] associationArray
                    String directory = record?.directory?.trim()?.replaceAll("\\s+", "")
                    if (null == directory || directory?.empty || directory?.equalsIgnoreCase('-')) {
                        associationArray = new String[] {"IBP"}
                    } else {
                        associationArray = directory?.split(",")
                    }
                    List<Association> associationObjList = new ArrayList<Association>()
                    associationArray?.each {associationObj ->
                        Association association = associationList?.find {   it?.name?.equalsIgnoreCase(associationObj)}
                        if (association) {
                            associationObjList.add(association)
                        }
                    }

                    if (productObjList && productObjList?.size() > 0) {
                        Vendors vendors = Vendors.findByEmailAndMobileNo(record?.businessEmail, record?.primaryPhoneNumber)
                        if (vendorUpload?.uploadAction?.equalsIgnoreCase('CREATE')) {
                            if (vendors) {
                                record.status = VendorDataStatus.FAILED.status
                                record.error = 'Vendor Already Available'
                                if(!isManual) {
                                    vendorUpload.failedCount++
                                }
                            } else {
                                UserClient userClient = saveUserClient(VendorBuilder.buildUserClientFromCsvRecord(record))
                                record?.createdBy = userService?.getLoggedInUser()?.username
                                record?.updatedBy = userService?.getLoggedInUser()?.username
                                vendors = save(VendorBuilder.buildFromCsvRecord(record, userClient))
                                VendorBusinessDetails vendorBusinessDetails = saveVendorBusinessDetails(
                                        VendorBuilder.buildVBDFromCsvRecord(record, vendors, category, subCategory, productArray, false))
                                productObjList.each {product ->
                                    saveVendorProductList(VendorBuilder.buildVendorProductList(category, product,
                                            vendorBusinessDetails))
                                }

                                associationObjList.each {association ->
                                    saveVendorAssociationList(VendorBuilder.buildVendorAssociationList(association, vendorBusinessDetails))
                                }
                                record.vendors = vendors
                                record.status = VendorDataStatus.SUCCESS.status
                                vendorUpload.successCount++
                                if (isManual) {
                                    vendorUpload.failedCount--
                                }

                                log.info "[processVendorRecord] fetching data to create contacts on wati.io"
                                // wati contact details
                                def watiContactDetails = new WatiVendorBusinessDetailsDto()
                                watiContactDetails.setName(vendorBusinessDetails?.username)

                                log.debug "[processVendorRecord] wati contact name = " + vendorBusinessDetails?.username

                                List<WatiCustomParamsDto> customParamsList = new ArrayList<>()

                                // email
                                if (vendorBusinessDetails?.businessEmail != null) {
                                    setWatiContactAttribute("businessEmail", vendorBusinessDetails?.businessEmail, customParamsList)
                                    log.debug "[processVendorRecord] wati business email = " + vendorBusinessDetails?.businessEmail
                                }
                                // businessName
                                if (vendorBusinessDetails?.businessName != null) {
                                    setWatiContactAttribute("businessName", vendorBusinessDetails?.businessName, customParamsList)
                                    log.debug "[processVendorRecord] wati business name = " + vendorBusinessDetails?.businessName
                                }
                                // websiteAddress
                                if (vendorBusinessDetails?.websiteAddress != null) {
                                    setWatiContactAttribute("websiteAddress", vendorBusinessDetails?.websiteAddress, customParamsList)
                                    log.debug "[processVendorRecord] wati website address = " + vendorBusinessDetails?.websiteAddress
                                }
                                // businessType
                                if (vendorBusinessDetails?.businessType != null) {
                                    setWatiContactAttribute("businessType", vendorBusinessDetails?.businessType, customParamsList)
                                    log.debug "[processVendorRecord] wati business type = " + vendorBusinessDetails?.businessType
                                }
                                // categoryName
                                if (vendorBusinessDetails?.categoryName != null) {
                                    setWatiContactAttribute("categoryName", vendorBusinessDetails?.categoryName, customParamsList)
                                    log.debug "[processVendorRecord] wati category = " + vendorBusinessDetails?.categoryName
                                }
                                // subCategoryName
                                if (vendorBusinessDetails?.subCategoryName != null) {
                                    setWatiContactAttribute("subCategoryName", vendorBusinessDetails?.subCategoryName, customParamsList)
                                    log.debug "[processVendorRecord] wati sub-category = " + vendorBusinessDetails?.subCategoryName
                                }
                                // productName
                                if (vendorBusinessDetails?.productName != null) {
                                    setWatiContactAttribute("productName", vendorBusinessDetails?.productName, customParamsList)
                                    log.debug "[processVendorRecord] wati product = " + vendorBusinessDetails?.productName
                                }
                                // address, area, city, district, state, pinCode
                                if (vendorBusinessDetails?.address != null || vendorBusinessDetails?.area != null ||
                                        vendorBusinessDetails?.city != null || vendorBusinessDetails?.district != null
                                        || vendorBusinessDetails?.state != null || vendorBusinessDetails?.pinCode != null) {
                                    def watiAddress = new WatiCustomParamsDto()
                                    watiAddress.setName("address")
                                    String address = vendorBusinessDetails?.address + ", " + vendorBusinessDetails?.area + ", " +
                                            vendorBusinessDetails?.city + ", " + vendorBusinessDetails?.district + ", " +
                                            vendorBusinessDetails?.state + ", " + vendorBusinessDetails?.pinCode
                                    watiAddress.setValue(address)
                                    log.debug "[processVendorRecord] wati address = " + address
                                    customParamsList.add(watiAddress)
                                }

                                watiContactDetails.setCustomParams(customParamsList)

                                String phoneNumWithCountryCode = "91" + vendorBusinessDetails.primaryPhoneNumber  // 91 is a country code, Hard Coded
                                log.debug "[processVendorRecord] wati phone number = " + phoneNumWithCountryCode

                                watiIntegrationService.addContact(watiContactDetails, phoneNumWithCountryCode)
                            }
                        } else {
                            if (vendors) {
                                VendorBusinessDetails vendorBusinessDetails = saveVendorBusinessDetails(
                                        VendorBuilder.buildVBDFromCsvRecord(record, vendors, category, subCategory, productArray, true))

                                if (productObjList?.size() > 0) {
                                    clearVendorProductList(vendorBusinessDetails)
                                }
                                productObjList.each { product ->
                                    saveVendorProductList(VendorBuilder.buildVendorProductList(category, product,
                                            vendorBusinessDetails))
                                }

                                if (associationObjList?.size() > 0) {
                                    clearVendorAssociationList(vendorBusinessDetails)
                                }
                                associationObjList.each {association ->
                                    saveVendorAssociationList(VendorBuilder.buildVendorAssociationList(association, vendorBusinessDetails))
                                }
                                record.vendors = vendors
                                record.status = VendorDataStatus.SUCCESS.status
                                vendorUpload.successCount++
                                if (isManual) {
                                    vendorUpload.failedCount--
                                }
                            } else {
                                record.status = VendorDataStatus.FAILED.status
                                record.error = 'Vendor Primary Phone Number and Business Email not found'
                                if(!isManual) {
                                    vendorUpload.failedCount++
                                }
                            }
                        }
                    } else {
                        String errorMessage = "[processVendorUpload]::ERROR::${category?.name ? '' : 'Category not found, '}" +
                                "${subCategory?.name ? '' : 'Sub Category not found, '}${productArray? '' : 'Product not found.'}"
                        log.error errorMessage
                        record.status = VendorDataStatus.FAILED.status
                        record.error = errorMessage
                        if(!isManual) {
                            vendorUpload.failedCount++
                        }
                    }
                } else {
                    record.status = VendorDataStatus.FAILED.status
                    record.error = vendorUploadDataCommand?.errors?.allErrors?.collect {
                        messageSource.getMessage(it, Locale.ENGLISH)
                    }?.join(", ")
                    if(!isManual) {
                        vendorUpload.failedCount++
                    }
                }
                vendorUploadDataCommand = null
            } catch (Exception ex) {
                log.error "[processVendorUpload] ERROR: ${ex.message}", ex
                record.status = VendorDataStatus.FAILED.status
                record.error = ex.message
                if(!isManual) {
                    vendorUpload.failedCount++
                }
            }
        }
        VendorUploadData.withNewTransaction {
            record.save(flush: true, failOnError: true)
        }
    }

    private void setWatiContactAttribute(String fieldName, String field, List<WatiCustomParamsDto> customParamsList) {
        if (field != null) {
            def contactAttribute = new WatiCustomParamsDto()
            contactAttribute.setName(fieldName)
            contactAttribute.setValue(field)
            customParamsList.add(contactAttribute)
        }
    }

    Vendors get(Long id) {
        Vendors.read(id)
    }

    VendorMedia getMedia(Long id) {
        VendorMedia.read(id)
    }

    @Transactional
    Vendors delete(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }

    def exportFailedCSVList(List exportFailedCSVList) {
        def exportList = []
        exportFailedCSVList.eachWithIndex { data, int i ->
            def exportObject = new VendorUploadData()
            exportObject.with {
                srNo = (i+1).toString()
                category = data?.category
                subCategory = data?.subCategory
                product = data?.product
                businessName = data?.businessName
                address = data?.address
                area = data?.area
                city = data?.city
                district = data?.district
                state = data?.state
                pinCode = data?.pinCode
                primaryLandNumber = data?.primaryLandNumber
                secondaryLandNumber = data?.secondaryLandNumber
                tertiaryLandNumber = data?.tertiaryLandNumber
                directory = data?.directory
                userID = data?.userID
                logoImageUrl = data?.logoImageUrl
                photoGallery = data?.photoGallery
                primaryPhoneNumber = data?.primaryPhoneNumber
                secondaryPhoneNumber = data?.secondaryPhoneNumber
                thirdPhoneNumber = data?.thirdPhoneNumber
                businessEmail = data?.businessEmail
                secondaryEmail = data?.secondaryEmail
                websiteAddress = data?.websiteAddress
                username = data?.username
                secondaryUsername = data?.secondaryUsername
                businessType = data?.businessType
                aboutCompany = data?.aboutCompany
                aboutCustomers = data?.aboutCustomers
                aboutSuppliers = data?.aboutSuppliers
                yearOfEstablishment = data?.yearOfEstablishment
                gst = data?.gst
                daysOpen = data?.daysOpen
                metaTitle = data?.metaTitle
                metaDescription = data?.metaDescription
                metaKeywords = data?.metaKeywords
                latitude = data?.latitude
                longitude = data?.longitude
                error = data?.error
            }
            exportList.add(exportObject)
        }
        exportList
    }

    @Transactional(readOnly = true)
    def listVendorUploadData(Map params) {
        VendorUploadData.createCriteria().list(params) {
            vendorUpload {
                eq('id', params?.id?.toString()?.toLong())
            }
            if (params?.businessName) {
                ilike('businessName', "%${params?.businessName}%")
            }
            if (params?.status) {
                eq('status', Byte.parseByte(params?.status as String))
            }
        }
    }

    @Transactional(readOnly = true)
    def getFailedVendorDataById(Map params) {
        VendorUploadData.createCriteria().list(params) {
            vendorUpload {
                eq('id', params?.id?.toString()?.toLong())
            }
            isNotNull('error')
        }
    }

    @Transactional
    def saveVendorMedia(VendorBusinessDetails vendorBusinessDetails, int imageType,MultipartFile multipartFile, String vendorId) {

        VendorMedia vendorMedia = new VendorMedia()
        vendorMedia.type = imageType
        vendorMedia.vendorBusiness = vendorBusinessDetails
        vendorMedia.vendorMediaUUID = UUID.randomUUID().toString()
        String imagePath = awsService.uploadImageToS3(multipartFile, Constants.S3Folders.VENDOR, vendorId+'_'+vendorMedia.vendorMediaUUID+'_'+imageType)
        vendorMedia.path = imagePath
        vendorMedia.name = multipartFile.originalFilename
        vendorMedia.docType = multipartFile.contentType
        vendorMedia.size = multipartFile.size

        vendorMedia.save(flush: true, failOnError: true)
        auditLogsService.logAction('Save','VendorMedia',vendorMedia?.id,userService?.getLoggedInUser()?.username,(vendorMedia as JSON).toString(false),(vendorMedia as JSON).toString(false))





    }
    @Transactional
    def updateVendorMedia(String imagePath, long size, String fileName, String contentType, VendorMedia vendorMedia) {
        def oldVendorMedia = vendorMedia.clone()
        vendorMedia.path = imagePath
        vendorMedia.name = fileName
        vendorMedia.docType = contentType
        vendorMedia.size = size
        vendorMedia.save(flush: true, failOnError: true)
        auditLogsService.logAction('Update','VendorMedia',vendorMedia?.id,userService?.getLoggedInUser()?.username,(oldVendorMedia as JSON).toString(false),(vendorMedia as JSON).toString(false))
    }
    @Transactional
    def removeVendorMedia(VendorMedia vendorMedia ){
        def oldVendorMedia = vendorMedia.clone()
        def deletedMedia = deleteVendorMedia(vendorMedia?.id)
        auditLogsService.logAction('Delete','VendorMedia',vendorMedia?.id,userService?.getLoggedInUser()?.username,(oldVendorMedia as JSON).toString(false),"")

    }
    VendorMedia deleteVendorMedia(Long id) {

        getMedia(id).delete(flush: true, failOnError: true)
    }

    Vendors loadVendorByMobileNo(String mobileNo)  {
        Vendors vendor = Vendors.findByMobileNo(mobileNo)
        if (!vendor) {
            throw new UsernameNotFoundException("Vendor not found")
        }
        vendor
    }

    @Override
    UserDetails loadUserByUsername(String mobileNo)  {
        loadVendorByMobileNo(mobileNo)
    }

}


