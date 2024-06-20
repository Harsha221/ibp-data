package com.ibp.admin

import com.ibp.admin.commands.VendorCreateCommand
import com.ibp.admin.commands.VendorFileUpload
import com.ibp.admin.dto.SendMessageDTO
import com.ibp.admin.enums.*
import com.ibp.admin.utils.IbpHubUtils
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class VendorsController {

    VendorsService vendorsService
    UserService userService
    def exportService
    AwsService awsService
    AuditLogsService auditLogsService
    OtpService otpService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static defaultAction = "index"

    def login() {
        render(view: "vendorsLogin")
    }

    def sendOtp() {
        try {
            def mobileNoWithoutCountryCode = params.mobileNo.substring(2)
            def vendor = otpService.generateOtp(mobileNoWithoutCountryCode)
            sendOTPToMobile(vendor.mobileNo, vendor.otp)
            if (vendor)
                render(view: "verifyOtp", model: [mobileNo: params.mobileNo])
        } catch (Exception e) {
            request.withFormat {
                form multipartForm {
                    flash.message = 'Vendor record not found.'
                    redirect action: 'login'
                }
                '*' { respond vendors, [status: NO_CONTENT] }
            }
        }

    }

    def authenticate() {
        def mobileNoWithoutCountryCode = params.mobileNo.substring(2)
        if (otpService.validateOtp(mobileNoWithoutCountryCode, params.otp)) {
            Map<String, String> vendorMap = new HashMap<>()
            vendorMap.put("name", mobileNoWithoutCountryCode)
            VendorBusinessDetails vendor = vendorsService.list(vendorMap).get(0)
            request.withFormat {
                form multipartForm {
                    flash.message = 'Vendor login successfully'
                    redirect action: 'editByVendor', method: 'GET', id: vendor.id, params: [name: 'unknown']
                }
                '*' { respond vendors, [status: CREATED] }
            }
        } else {
            request.withFormat {
                form multipartForm {
                    flash.message = 'wrong OTP'
                    redirect action: 'sendOtp', params: [mobileNo: params.mobileNo]
                }
                '*' { respond vendors, [status: NO_CONTENT] }
            }
        }
    }

    private void sendOTPToMobile(String mobileNo, String otp) {

        String OTP = otp
        String EXPIRE_MINUTES = "300"
        String expireMinutes = (5 * 60).toString()
        String generatedMessage = "Dear user, ${OTP.replace(OTP, otp)} is OTP for your login at IBPHub, This code expires in ${EXPIRE_MINUTES.replace(EXPIRE_MINUTES, expireMinutes)} seconds. Never share OTP with anyone. Ameya Information Ltd."

        SendMessageDTO messageDTO = SendMessageDTO.builder()
                .withSenderMobileNo("9825217616")
                .withPassword("579608280c4b4f6092107b252d3d03f8")
                .withToNumber(mobileNo)
                .withSenderId("IBPHUB")
//                .withTemplateId("IBPHUB")
                .withTemplateId("1207170505685026437")
                .withMessage(generatedMessage)
                .withPeId("1201159886408760288")
                .build()

        try {
            // Url that will be called to submit the message
            URL sendUrl = new URL("https://smsidea.co.in/smsstatuswithid.aspx")
            HttpURLConnection httpConnection = (HttpURLConnection) sendUrl.openConnection()
            httpConnection.setRequestMethod("POST")
            httpConnection.setDoInput(true)
            httpConnection.setDoOutput(true)
            httpConnection.setUseCaches(false)
            DataOutputStream dataStreamToServer = new DataOutputStream(httpConnection.getOutputStream())
            dataStreamToServer.writeBytes("mobile="
                    + URLEncoder.encode(messageDTO.senderMobileNo, "UTF-8") + "&pass="
                    + URLEncoder.encode(messageDTO.password, "UTF-8") + "&senderid="
                    + URLEncoder.encode(messageDTO.senderId, "UTF-8") + "&to="
                    + URLEncoder.encode(messageDTO.toNumber, "UTF-8") + "&msg="
                    + URLEncoder.encode(messageDTO.message, "UTF-8") + "&templateid="
                    + URLEncoder.encode(messageDTO.templateId, "UTF-8") + "&restype=json")
            dataStreamToServer.flush()
            dataStreamToServer.close()

            BufferedReader dataStreamFromUrl = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()))
            String dataFromUrl = "", dataBuffer = ""

            // Writing information from the stream to the buffer
            while ((dataBuffer = dataStreamFromUrl.readLine()) != null) {
                dataFromUrl += dataBuffer
            }
            /**
             * Now dataFromUrl variable contains the Response received from the
             * server so we can parse the response and process it accordingly.
             */
            dataStreamFromUrl.close()
            log.info('OTP send response for Mobile {}', dataFromUrl)
        } catch (Exception ex) {
            log.error("@@@sendOTPToMobile:: Error while sending otp to:${mobileNo}", ex)
        }
    }

    def home() {
        // Vendor home page
        log.info '[home] welcome home'
    }

    def logout() {
        // Handle logout
        session.invalidate()
        redirect(action: 'login')
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        params.verified = 'true'
        def vendorsList = vendorsService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    vendorsList : vendorsList,
                    vendorsCount: vendorsList?.totalCount
            ]
            return
        }
        [vendorsList: vendorsList, vendorsCount: vendorsList?.totalCount, params: params]
    }

    def businessList(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        params.verified = 'false'
        def vendorsList = vendorsService.list(params)
        if (request.xhr) {
            render template: 'businessList', model: [
                    vendorsList : vendorsList,
                    vendorsCount: vendorsList?.totalCount
            ]
            return
        }
        [vendorsList: vendorsList, vendorsCount: vendorsList?.totalCount, params: params]
    }

    def verifiedByVendors(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        params.verified = 'false'
        params.verifiedByVendors = 'true'
        def vendorsList = vendorsService.listOfVerifiedByVendors(params)
        if (request.xhr) {
            render template: 'businessList', model: [
                    vendorsList : vendorsList,
                    vendorsCount: vendorsList?.totalCount
            ]
            return
        }
        [vendorsList: vendorsList, vendorsCount: vendorsList?.totalCount, params: params]
    }

    def create() {
//        respond new VendorBusinessDetails(params)
    }

    def getSubCategoriesByCategory () {
        println "@@@getSubCategoriesByCategory::Params > " + params
        def category = Category.get(params?.id)
        def categoriseSubCategories = SubCategory.findAllByActiveAndCategory(true, category)
        render(template: 'subCategoriesSelect', model: [categoriseSubCategories: categoriseSubCategories, category: category,
                                                        params: params])
    }

    def getProductsBySubCategories () {
        println "@@@getProductsBySubCategories::Params productIds:: " + params.productIds
        def subCategories = SubCategory.findAllByIdInList(params?.id?.split(",")?.toList())
        def productsList = Product.findAllByActiveAndSubCategoryInList(true, subCategories)
        render(template: 'productSelect', model: [subCategoriesProductsList: productsList?.groupBy {it?.subCategory?.name},
                                                  params: params])
    }

    def save(VendorCreateCommand vendorCreateCommand) {
        if (vendorCreateCommand == null) {
            notFound()
            return
        }

        if(Vendors.findByEmail(vendorCreateCommand?.businessEmail)) {
            vendorCreateCommand.errors.rejectValue('businessEmail', 'com.ibp.admin.vendors.duplicate.email.exists')
        }

        if(Vendors.findByMobileNo(vendorCreateCommand?.primaryPhoneNumber)) {
            vendorCreateCommand.errors.rejectValue('primaryPhoneNumber', 'com.ibp.admin.vendors.duplicate.mobile.number')
        }

        if (vendorCreateCommand?.hasErrors()) {
            render(view: 'create', model: [vendorUploadDataInstance: vendorCreateCommand])
            return
        }

        /*if (!vendorCreateCommand?.validate()) {
            render(view: 'create', model: [vendorUploadDataInstance: vendorCreateCommand])
            return
        }*/

        try {

            UserClient userClient = vendorsService.saveUserClient(new UserClient(userClientId: UUID.randomUUID(),
                    email: vendorCreateCommand?.businessEmail, phoneNumber: vendorCreateCommand?.primaryPhoneNumber,
                    username: vendorCreateCommand?.businessEmail, businessName: vendorCreateCommand?.businessName,
                    type: UserType.VENDOR.value, status: UserStatus.ACTIVE.value))

            Vendors vendors = vendorsService.save(new Vendors(vendorId: UUID.randomUUID(), userClient: userClient,
                    email: vendorCreateCommand?.businessEmail, mobileNo: vendorCreateCommand?.primaryPhoneNumber,
                    createdBy: userService?.getLoggedInUser()?.username,updatedBy: userService?.getLoggedInUser()?.username))
            auditLogsService.logAction('Save',vendors.class.simpleName,vendors?.id,userService?.getLoggedInUser()?.username,(vendors as JSON).toString(false),(vendors as JSON).toString(false))
            VendorBusinessDetails vendorBusinessDetails = new VendorBusinessDetails()
            bindData(vendorBusinessDetails, vendorCreateCommand)
            vendorBusinessDetails.status = VendorBusinessStatus.SUBMITTED.value
            vendorBusinessDetails.paid = PaymentStatus.UN_PAID.value
            vendorBusinessDetails.vendor = vendors
            vendorBusinessDetails.category = Category.findById(Long.valueOf(vendorCreateCommand.category))
            vendorBusinessDetails.vendorDetailUUID = UUID.randomUUID()
            vendorBusinessDetails.categoryName = Category.findById(Long.valueOf(vendorCreateCommand.category)).name
            vendorBusinessDetails.location = vendorCreateCommand?.latitude + "," + vendorCreateCommand?.longitude


            Set<String> subcategoryname = new HashSet<String>()
            Set<String> productname = new HashSet<String>()

            def products = Product.findAllByActiveAndIdInList(true, vendorCreateCommand?.product?.split(',')?.toList())
            products?.eachWithIndex { it, index ->
                def vendorProductList = new VendorProductList(vendorProductListId: UUID.randomUUID(),
                        vendorBusiness: vendorBusinessDetails,
                        category: it?.category, categoryName: it?.category?.name,
                        subCategory: it?.subCategory, subCategoryName: it?.subCategory?.name,
                        product: it, productName: it?.name)
                vendorsService.saveVendorProductList(vendorProductList)
                auditLogsService.logAction('Save',VendorProductList.class.simpleName,vendorProductList?.id,userService?.getLoggedInUser()?.username,(vendorProductList as JSON).toString(false),(vendorProductList as JSON).toString(false))


                subcategoryname.add(it?.subCategory?.name)
                productname.add(it?.name)


            }



            String commaSeparatedSubcategoryname = String.join(",", subcategoryname)
            String commaSeparatedProductname = String.join(",", productname)
            vendorBusinessDetails.subCategoryName = commaSeparatedSubcategoryname
            vendorBusinessDetails.productName = commaSeparatedProductname


            String vendorBusinessName = vendorBusinessDetails?.businessName
            String url = IbpHubUtils.convertToSlug(vendorBusinessName)
            vendorBusinessDetails?.friendlyUrl = url.concat("-"+vendorBusinessDetails?.vendorDetailUUID)

            vendorBusinessDetails = vendorsService.saveVendorBusinessDetails(vendorBusinessDetails)
            auditLogsService.logAction('Save','VendorBusinessDetails',vendorBusinessDetails?.id,userService?.getLoggedInUser()?.username,(vendorBusinessDetails as JSON).toString(false),(vendorBusinessDetails as JSON).toString(false))



            def associations = Association.findAllByActiveAndIdInList(true, vendorCreateCommand?.association?.split(',')?.toList())
            associations?.each {
                VendorAssociationList vendorAssociationList = new VendorAssociationList()
                vendorAssociationList.association = it
                vendorAssociationList.vendorBusiness = vendorBusinessDetails
                vendorAssociationList.associationName = it?.name
                vendorsService.saveVendorAssociationList(vendorAssociationList)
                auditLogsService.logAction('Save',vendorAssociationList.class.simpleName,vendorAssociationList?.id,userService?.getLoggedInUser()?.username,(vendorAssociationList as JSON).toString(false),(vendorAssociationList as JSON).toString(false))
            }

            if(vendorCreateCommand?.panCardFile?.size>0 ) {
                vendorsService.saveVendorMedia(vendorBusinessDetails, MediaType.PAN_CARD.value, vendorCreateCommand?.panCardFile, vendors?.vendorId)
            }
            if(vendorCreateCommand?.aadhaarCardFile?.size>0 ) {
                vendorsService.saveVendorMedia(vendorBusinessDetails,MediaType.AADHAAR_CARD.value,vendorCreateCommand?.aadhaarCardFile,vendors?.vendorId)
            }
            if(vendorCreateCommand?.certificationFile?.size>0 ) {
                vendorsService.saveVendorMedia(vendorBusinessDetails,MediaType.CERTIFICATE.value,vendorCreateCommand?.certificationFile,vendors?.vendorId)
            }

            if(vendorCreateCommand?.logoFile?.size>0 ) {
                vendorsService.saveVendorMedia(vendorBusinessDetails,MediaType.LOGO.value,vendorCreateCommand?.logoFile,vendors?.vendorId)
            }
            if(vendorCreateCommand?.businessLicenceFile?.size>0 ) {
                vendorsService.saveVendorMedia(vendorBusinessDetails,MediaType.BUSINESS_LICENCE.value,vendorCreateCommand?.businessLicenceFile,vendors?.vendorId)
            }
            if(vendorCreateCommand?.imageFiles?.size() > 0) {
                vendorCreateCommand?.imageFiles?.each {
                    if(it?.size > 0) {
                        vendorsService.saveVendorMedia(vendorBusinessDetails,MediaType.IMAGE_VIDEO.value,it,vendors?.vendorId)
                    }
                }
            }
            if(vendorCreateCommand?.videoFiles?.size() > 0) {
                vendorCreateCommand?.videoFiles?.each {
                    if(it?.size > 0) {
                        vendorsService.saveVendorMedia(vendorBusinessDetails,MediaType.IMAGE_VIDEO.value,it,vendors?.vendorId)
                    }
                }
            }

        }
        catch (ValidationException ignored) {
            respond vendorCreateCommand.errors, view: 'create'
            return
        }

        if (params.name.equals("businessList")) {
            request.withFormat {
                form multipartForm {
                    flash.message = 'Vendor created successfully'
                    //message(code: 'default.updated.message', args: [message(code: 'vendors.label', default: 'Vendors'), vendors.id])
                    redirect action: 'businessList' // vendors
                }
                '*' { respond vendors, [status: CREATED] }
            }
        } else {
            request.withFormat {
                form multipartForm {
                    flash.message = 'Vendor created successfully'
                    //message(code: 'default.created.message', args: [message(code: 'vendors.label', default: 'Vendors'), vendors.id])
                    redirect action: 'index' // vendors
                }
                '*' { respond vendors, [status: CREATED] }
            }
        }
    }

    def edit(Long id) {
        def vendors = vendorsService.get(id)
        def vendorBusinessDetails = VendorBusinessDetails?.findByVendor(vendors)
        def vendorProductList = VendorProductList?.findAllByVendorBusiness(vendorBusinessDetails)
        def vendorAssociationList = VendorAssociationList?.findAllByVendorBusiness(vendorBusinessDetails)
        def vendorCreateCommand = new VendorCreateCommand()
        bindData(vendorCreateCommand, vendorBusinessDetails)
        vendorCreateCommand.id = vendors?.id
        //vendorCreateCommand.category = vendorProductList[0]?.category?.id?.toString()
        vendorCreateCommand.category = vendorBusinessDetails?.category?.id?.toString()
        vendorCreateCommand.subCategory = vendorProductList?.subCategory?.unique()?.id?.join(',')
        vendorCreateCommand.product = vendorProductList?.product?.id?.join(',')
        vendorCreateCommand.association = vendorAssociationList?.association?.id?.join(',')
        def categoriseSubCategories = SubCategory.findAllByActiveAndCategory(true, vendorBusinessDetails?.category)
        def subCategoriesProductsList = Product?.findAllByActiveAndSubCategoryInList(true, categoriseSubCategories)
        def vendorMediaList = VendorMedia.findAllByVendorBusiness(vendorBusinessDetails)
        def multipleMediaList = VendorMedia.findAllByVendorBusinessAndType(vendorBusinessDetails, MediaType.IMAGE_VIDEO.value)
        def multipleVideoMediaList = VendorMedia.findAllByVendorBusinessAndTypeAndDocTypeLike(vendorBusinessDetails, MediaType.IMAGE_VIDEO.value, "%video%")
        log.info("multivideo media list size : "+multipleVideoMediaList?.size())
        [multipleVideoMediaList: multipleVideoMediaList, multipleMediaList: multipleMediaList, vendorMediaList: vendorMediaList, vendorCreateCommand: vendorCreateCommand, vendor: vendors,
         category: vendorBusinessDetails?.category, categoriseSubCategories:categoriseSubCategories,
         subCategoriesProductsList: subCategoriesProductsList?.groupBy {it?.subCategory?.name}]
    }

    def editByVendor(Long id) {
        def vendors = vendorsService.get(id)
        def vendorBusinessDetails = VendorBusinessDetails?.findByVendor(vendors)
        def vendorProductList = VendorProductList?.findAllByVendorBusiness(vendorBusinessDetails)
        def vendorAssociationList = VendorAssociationList?.findAllByVendorBusiness(vendorBusinessDetails)
        def vendorCreateCommand = new VendorCreateCommand()
        bindData(vendorCreateCommand, vendorBusinessDetails)
        vendorCreateCommand.id = vendors?.id
        //vendorCreateCommand.category = vendorProductList[0]?.category?.id?.toString()
        vendorCreateCommand.category = vendorBusinessDetails?.category?.id?.toString()
        vendorCreateCommand.subCategory = vendorProductList?.subCategory?.unique()?.id?.join(',')
        vendorCreateCommand.product = vendorProductList?.product?.id?.join(',')
        vendorCreateCommand.association = vendorAssociationList?.association?.id?.join(',')
        def categoriseSubCategories = SubCategory.findAllByActiveAndCategory(true, vendorBusinessDetails?.category)
        def subCategoriesProductsList = Product?.findAllByActiveAndSubCategoryInList(true, categoriseSubCategories)
        def vendorMediaList = VendorMedia.findAllByVendorBusiness(vendorBusinessDetails)
        def multipleMediaList = VendorMedia.findAllByVendorBusinessAndType(vendorBusinessDetails, MediaType.IMAGE_VIDEO.value)
        def multipleVideoMediaList = VendorMedia.findAllByVendorBusinessAndTypeAndDocTypeLike(vendorBusinessDetails, MediaType.IMAGE_VIDEO.value, "%video%")
        log.info("multivideo media list size : "+multipleVideoMediaList?.size())
        [multipleVideoMediaList: multipleVideoMediaList, multipleMediaList: multipleMediaList, vendorMediaList: vendorMediaList, vendorCreateCommand: vendorCreateCommand, vendor: vendors,
         category: vendorBusinessDetails?.category, categoriseSubCategories:categoriseSubCategories,
         subCategoriesProductsList: subCategoriesProductsList?.groupBy {it?.subCategory?.name}]
    }

    @Transactional
    def update(VendorCreateCommand vendorCreateCommand) {
        log.info("[update] Entered")
        log.info("remove vendor video media : "+params?.removeVideoMedia)
        if (vendorCreateCommand == null) {
            notFound()
            return
        }

        if (!vendorCreateCommand?.businessEmail?.equalsIgnoreCase(vendorCreateCommand?.hiddenBusinessEmail)) {
            log.info("email not same")
            if(Vendors.findByEmail(vendorCreateCommand?.businessEmail)) {
                log.info("Update validation on email fired")
                vendorCreateCommand.errors.rejectValue('businessEmail', 'com.ibp.admin.vendors.duplicate.email.exists')
            }
        }

        if (!vendorCreateCommand?.primaryPhoneNumber?.equalsIgnoreCase(vendorCreateCommand?.hiddenPrimaryPhoneNumber)) {
            log.info("primary phone not same")
            if(Vendors.findByMobileNo(vendorCreateCommand?.primaryPhoneNumber)) {
                log.info("update validation on phone fired")
                vendorCreateCommand.errors.rejectValue('primaryPhoneNumber', 'com.ibp.admin.vendors.duplicate.mobile.number')
            }
        }

        if (vendorCreateCommand?.hasErrors()) {
            render(view: 'edit', model: [vendorCreateCommand: vendorCreateCommand])
            return
        }

        Vendors vendors = Vendors.get(vendorCreateCommand?.id)
        def oldVendor = vendors.clone()
        vendors.mobileNo = vendorCreateCommand?.primaryPhoneNumber
        vendors.email = vendorCreateCommand?.businessEmail

        // if data is verified by Vendor
        if (params?.name?.equals("unknown")) {
            log.info('record updated by Vendor')
            vendors.setModifiedByVendor(true)
        }

        vendorsService.save(vendors)
        auditLogsService.logAction('Update',vendors.class.simpleName,vendors?.id,userService?.getLoggedInUser()?.username,(oldVendor as JSON).toString(false),(vendors as JSON).toString(false))

        try {

            VendorBusinessDetails vendorBusinessDetails = VendorBusinessDetails.findByVendor(vendors)
            def oldVendorBusinessDetails = vendorBusinessDetails.clone()
            bindData(vendorBusinessDetails, vendorCreateCommand)
            vendorBusinessDetails.category = Category.findById(Long.valueOf(vendorCreateCommand.category))
            vendorBusinessDetails.categoryName = Category.findById(Long.valueOf(vendorCreateCommand.category)).name
            vendorBusinessDetails.location = vendorCreateCommand?.latitude + "," + vendorCreateCommand?.longitude

            if (params?.name?.equals("unknown")) {
                log.info('record updated by Vendor')
                vendorBusinessDetails.setModifiedByVendor(true)
            }

            Set<String> subcategoryname = new HashSet<String>()
            Set<String> productname = new HashSet<String>()

            vendorBusinessDetails = vendorsService.saveVendorBusinessDetails(vendorBusinessDetails)
            def vendorMediaList = VendorMedia.findAllByVendorBusiness(vendorBusinessDetails)
            def multipleImageMediaList = VendorMedia.findAllByVendorBusinessAndTypeAndDocTypeLike(vendorBusinessDetails, MediaType.IMAGE_VIDEO.value, "%image%")
            def multipleVideoMediaList = VendorMedia.findAllByVendorBusinessAndTypeAndDocTypeLike(vendorBusinessDetails, MediaType.IMAGE_VIDEO.value, "%video%")

            def products = Product.findAllByActiveAndIdInList(true, vendorCreateCommand?.product?.split(',')?.toList())
            def associations = Association.findAllByActiveAndIdInList(true, vendorCreateCommand?.association?.split(',')?.toList())

            if (associations?.size() > 0) {
                vendorsService.clearVendorAssociationList(vendorBusinessDetails)
            }
            associations?.each {
                VendorAssociationList vendorAssociationList = new VendorAssociationList()

                vendorAssociationList.association = it
                vendorAssociationList.vendorBusiness = vendorBusinessDetails
                vendorAssociationList.associationName = it?.name
                vendorsService.saveVendorAssociationList(vendorAssociationList)
                auditLogsService.logAction('Save',vendorAssociationList.class.simpleName,vendorAssociationList?.id,userService?.getLoggedInUser()?.username,(vendorAssociationList as JSON).toString(false),(vendorAssociationList as JSON).toString(false))
            }

            if (products?.size() > 0) {
                vendorsService.clearVendorProductList(vendorBusinessDetails)
            }


            products?.eachWithIndex { it, index ->
                def vendorProductList = new VendorProductList(vendorProductListId: UUID.randomUUID(),
                        vendorBusiness: vendorBusinessDetails,
                        category: it?.category, categoryName: it?.category?.name,
                        subCategory: it?.subCategory, subCategoryName: it?.subCategory?.name,
                        product: it, productName: it?.name)
                vendorsService.saveVendorProductList(vendorProductList)
                auditLogsService.logAction('Save',VendorProductList.class.simpleName,vendorProductList?.id,userService?.getLoggedInUser()?.username,(vendorProductList as JSON).toString(false),(vendorProductList as JSON).toString(false))


                //  subcategoryname += it?.subCategory?.name
                subcategoryname.add(it?.subCategory?.name)

                //  productname += it?.name
                productname.add(it?.name)

            }
            String commaSeparatedSubcategoryname = String.join(",", subcategoryname)
            String commaSeparatedProductname = String.join(",", productname)
            vendorBusinessDetails.subCategoryName = commaSeparatedSubcategoryname
            vendorBusinessDetails.productName = commaSeparatedProductname

            String vendorBusinessName = vendorBusinessDetails?.businessName
            String url = IbpHubUtils.convertToSlug(vendorBusinessName)
            vendorBusinessDetails?.friendlyUrl = url.concat("-"+vendorBusinessDetails?.vendorDetailUUID)

            vendorBusinessDetails = vendorsService.saveVendorBusinessDetails(vendorBusinessDetails)
            auditLogsService.logAction('Update','VendorBusinessDetails',vendorBusinessDetails?.id,userService?.getLoggedInUser()?.username,(oldVendorBusinessDetails as JSON).toString(false),(vendorBusinessDetails as JSON).toString(false))






            boolean isPan = false
            boolean isAadhar = false
            boolean isCertificate = false
            boolean isLogo = false
            boolean isLicensce = false
            boolean isVideoImage = false


            vendorMediaList?.each {

                if(it.type == MediaType.PAN_CARD.value) {
                    isPan = true
                    if(vendorCreateCommand?.panCardFile?.size>0 ) {
                        awsService.removeImageFromS3(it?.path , 'prod/vendor' , vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        String imagePath = awsService.uploadImageToS3(vendorCreateCommand?.panCardFile, Constants.S3Folders.VENDOR, vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        vendorsService.updateVendorMedia(imagePath, vendorCreateCommand?.panCardFile?.size, vendorCreateCommand?.panCardFile?.originalFilename, vendorCreateCommand?.panCardFile?.contentType, it)


                    }
                    else if(vendorCreateCommand?.panCardFile?.size == 0 && it?.path) {
                        vendorsService.updateVendorMedia(it?.path, it?.size, it?.name, it?.docType, it)
                    }
                    else{
                        awsService.removeImageFromS3(it?.path , 'prod/vendor' , vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        vendorsService.removeVendorMedia(it)
                    }
                }
                if (it.type == MediaType.AADHAAR_CARD.value){
                    isAadhar = true
                    if(vendorCreateCommand?.aadhaarCardFile?.size>0){
                        awsService.removeImageFromS3(it?.path,'prod/vendor',vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        String imagePath = awsService.uploadImageToS3(vendorCreateCommand?.aadhaarCardFile,Constants.S3Folders.VENDOR,vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        vendorsService.updateVendorMedia(imagePath,vendorCreateCommand?.aadhaarCardFile?.size,vendorCreateCommand?.aadhaarCardFile?.originalFilename,vendorCreateCommand?.aadhaarCardFile?.contentType,it)
                    }
                    else if(vendorCreateCommand?.aadhaarCardFile?.size == 0 && it?.path) {
                        vendorsService.updateVendorMedia(it?.path, it?.size, it?.name, it?.docType, it)
                    }
                    else{
                        awsService.removeImageFromS3(it?.path , 'prod/vendor' , vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        vendorsService.removeVendorMedia(it)
                    }
                }
                if(it.type == MediaType.CERTIFICATE.value) {
                    isCertificate = true
                    if(vendorCreateCommand?.certificationFile?.size>0 ) {
                        awsService.removeImageFromS3(it?.path , 'prod/vendor' , vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type+'_'+it?.type)
                        String imagePath = awsService.uploadImageToS3(vendorCreateCommand?.certificationFile, Constants.S3Folders.VENDOR, vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        vendorsService.updateVendorMedia(imagePath, vendorCreateCommand?.certificationFile?.size, vendorCreateCommand?.certificationFile?.originalFilename, vendorCreateCommand?.certificationFile?.contentType, it)
                    }
                    else if(vendorCreateCommand?.certificationFile?.size == 0 && it?.path) {
                        vendorsService.updateVendorMedia(it?.path, it?.size, it?.name, it?.docType, it)
                    }
                    else{
                        awsService.removeImageFromS3(it?.path , 'prod/vendor' , vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        vendorsService.removeVendorMedia(it)
                    }
                }
                if(it.type == MediaType.LOGO.value) {
                    isLogo = true
                    if(vendorCreateCommand?.logoFile?.size>0 ) {
                        awsService.removeImageFromS3(it?.path , 'prod/vendor' , vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        String imagePath = awsService.uploadImageToS3(vendorCreateCommand?.logoFile, Constants.S3Folders.VENDOR, vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        vendorsService.updateVendorMedia(imagePath, vendorCreateCommand?.logoFile?.size, vendorCreateCommand?.logoFile?.originalFilename, vendorCreateCommand?.logoFile?.contentType, it)
                    }
                    else if(vendorCreateCommand?.logoFile?.size == 0 && it?.path) {
                        vendorsService.updateVendorMedia(it?.path, it?.size, it?.name, it?.docType, it)
                    }
                    else{
                        awsService.removeImageFromS3(it?.path , 'prod/vendor' , vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        vendorsService.removeVendorMedia(it)
                    }
                }
                if(it.type == MediaType.BUSINESS_LICENCE.value) {
                    isLicensce = true
                    if(vendorCreateCommand?.businessLicenceFile?.size>0 ) {
                        awsService.removeImageFromS3(it?.path , 'prod/vendor' , vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        String imagePath = awsService.uploadImageToS3(vendorCreateCommand?.businessLicenceFile, Constants.S3Folders.VENDOR, vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        vendorsService.updateVendorMedia(imagePath, vendorCreateCommand?.businessLicenceFile?.size, vendorCreateCommand?.businessLicenceFile?.originalFilename, vendorCreateCommand?.businessLicenceFile?.contentType, it)
                    }
                    else if(vendorCreateCommand?.businessLicenceFile?.size == 0 && it?.path) {
                        vendorsService.updateVendorMedia(it?.path, it?.size, it?.name, it?.docType, it)
                    }
                    else{
                        awsService.removeImageFromS3(it?.path , 'prod/vendor' , vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        vendorsService.removeVendorMedia(it)
                    }
                }
            }

            //To do create logic
            if(!isPan) {
                if(vendorCreateCommand?.panCardFile?.size>0 ) {
                    vendorsService.saveVendorMedia(vendorBusinessDetails, MediaType.PAN_CARD.value, vendorCreateCommand?.panCardFile, vendors?.vendorId)
                }
            }
            if(!isAadhar) {
                if(vendorCreateCommand?.aadhaarCardFile?.size>0 ) {
                    vendorsService.saveVendorMedia(vendorBusinessDetails,MediaType.AADHAAR_CARD.value,vendorCreateCommand?.aadhaarCardFile,vendors?.vendorId)
                }
            }
            if(!isCertificate) {
                if(vendorCreateCommand?.certificationFile?.size>0 ) {
                    vendorsService.saveVendorMedia(vendorBusinessDetails,MediaType.CERTIFICATE.value,vendorCreateCommand?.certificationFile,vendors?.vendorId)
                }
            }
            if(!isLogo) {
                if(vendorCreateCommand?.logoFile?.size>0 ) {
                    vendorsService.saveVendorMedia(vendorBusinessDetails,MediaType.LOGO.value,vendorCreateCommand?.logoFile,vendors?.vendorId)
                }
            }
            if(!isLicensce) {
                if(vendorCreateCommand?.businessLicenceFile?.size>0 ) {
                    vendorsService.saveVendorMedia(vendorBusinessDetails,MediaType.BUSINESS_LICENCE.value,vendorCreateCommand?.businessLicenceFile,vendors?.vendorId)
                }
            }

            //multiple images update logic
            List<VendorMedia> configAds = []
            String oldAds = ''
            log.info("Old value : "+params?.old)
            if (params?.old)
            {
                if (params?.old instanceof List) {
                    oldAds = params?.old ? (params?.old as List).join(',') : ''
                }
                else
                {
                    oldAds = params?.old
                }

            }
            multipleImageMediaList?.each {
                if(oldAds?.indexOf(it?.id?.toString()) < 0) {
                    configAds.add(it)
                }
            }

            configAds?.each {
                awsService.removeImageFromS3(it.path, 'prod/vendor', vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                //homepageConfig.removeFromHomePageAds(it)
                vendorsService.removeVendorMedia(it)
            }

            vendorCreateCommand?.imageFiles?.each {
                if (!it.empty) {
                    // String imageId = UUID.randomUUID()
                    vendorsService.saveVendorMedia(vendorBusinessDetails,MediaType.IMAGE_VIDEO.value,it,vendors?.vendorId)


                }
            }

            if (params?.removeVideoMedia) {
                if(params?.removeVideoMedia instanceof String[]) {
                    List<VendorMedia> vendorMedias = VendorMedia.findAllByIdInList(params?.removeVideoMedia as List<Long>)
                    vendorMedias?.each {
                        awsService.removeImageFromS3(it.path, 'prod/vendor', vendors?.vendorId+'_'+it?.vendorMediaUUID+'_'+it?.type)
                        vendorsService.removeVendorMedia(it)
                    }
                } else {
                    VendorMedia vendorMedia = VendorMedia.findById(params?.removeVideoMedia as Long)
                    awsService.removeImageFromS3(vendorMedia.path, 'prod/vendor', vendors?.vendorId+'_'+vendorMedia?.vendorMediaUUID+'_'+vendorMedia?.type)
                    vendorsService.removeVendorMedia(vendorMedia)
                }
            }

            vendorCreateCommand?.videoFiles?.each {
                if (!it.empty) {
                    vendorsService.saveVendorMedia(vendorBusinessDetails,MediaType.IMAGE_VIDEO.value,it,vendors?.vendorId)
                }
            }
            vendors?.updatedBy = userService?.getLoggedInUser()?.username
            vendorsService.save(vendors)
        } catch (ValidationException ignored) {
            respond vendors.errors, view: 'edit'
            return
        }
        if (params.name.equals("businessList")) {
            log.info "[update] params.name = businessList"
            request.withFormat {
                form multipartForm {
                    flash.message = 'Vendor updated successfully'
                    //message(code: 'default.updated.message', args: [message(code: 'vendors.label', default: 'Vendors'), vendors.id])
                    redirect action: 'businessList' // vendors
                }
                '*' { respond vendors, [status: OK] }
            }
        } else if (params.name.equals("unknown")) {
            log.info "[update] params.name = unknown"
            request.withFormat {
                form multipartForm {
                    flash.message = 'Vendor record updated successfully'
                    //message(code: 'default.updated.message', args: [message(code: 'vendors.label', default: 'Vendors'), vendors.id])
                    redirect action: 'edit', method: 'GET', id: params.id, params: [name: params.name] // vendors
                }
                '*' { respond vendors, [status: OK] }
            }
        }else {
            log.info "[update] params.name = anonymous"
            request.withFormat {
                form multipartForm {
                    flash.message = 'Vendor updated successfully'
                    //message(code: 'default.updated.message', args: [message(code: 'vendors.label', default: 'Vendors'), vendors.id])
                    redirect action: 'index' // vendors
                }
                '*' { respond vendors, [status: OK] }
            }
        }

    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        vendorsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = 'Vendor deleted successfully'
                //message(code: 'default.deleted.message', args: [message(code: 'vendors.label', default: 'Vendors'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    def uploads(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def vendorUploadList = vendorsService.uploadList(params)
        if (request.xhr) {
            println "XHR"
            render template: 'uploadList', model: [
                    vendorUploadList : vendorUploadList,
                    vendorUploadCount: vendorUploadList.totalCount
            ]
            return
        }
        if(params?.f && params.f == "csv") {
            response.contentType = grailsApplication.config.grails.mime.types[params.f]
            response.setHeader("Content-disposition", "attachment; filename=Vendor_Upload.csv")
            List fields = ['Sr No','business_name','address','area','city','district','state','pincode','primary_land_number','secondary_land_number','tertiary_land_number','username','primary_phone_number','secondary_username','secondary_phone_number','3rd_phone_number','business_email','secondary_email','website_address','business_type','category','sub_category','Products_and_services','about_company','about_customers','about_suppliers','year_of_establishment','gst','days_open','lattitude','longitude','meta title','meta description','meta keywords','directory','User ID','logo_image_url','photo_gallery']
//            List vendor = ['category','sub_category','product_and_services']
//            vendor.add(VendorUploadData.getTestExportData())
//            Map labels = ["id": "Sr No", "category": "Category"]
            exportService.export(params?.f as String, response.outputStream, [],fields, [:], [:], [:])
            return
        }
        respond vendorUploadList, model: [vendorUploadCount: vendorUploadList.totalCount]
    }

    def createUpload() {
        respond new VendorFileUpload()
    }

    def saveUpload(VendorFileUpload vendorFileUpload) {
        if (vendorFileUpload == null) {
            notFound()
            return
        }
        try {
            vendorsService.saveVendorUpload(vendorFileUpload)
        } catch (ValidationException e) {
            respond vendorFileUpload.errors, view: 'createUpload'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Vendor file uploaded successfully'
                redirect action: 'uploads'
            }
            '*' { respond vendorFileUpload, [status: CREATED] }
        }
    }

    def process(VendorUpload vendorUpload) {
        if (vendorUpload == null) {
            notFound()
            return
        }
        Map response = [
                success: false
        ]
        try {
            vendorsService.processVendorUpload(vendorUpload)
            response = [
                    message: 'Vendor data upload started successfully',
                    success: true
            ]
        } catch (Exception ex) {
            response = [
                    message: 'Vendor data upload failed',
                    error  : ex.message,
                    success: false
            ]
        }
        render response as JSON
    }

    def exportFailedCSV(Integer max) {

        List vendorUploadDataList = vendorsService.getFailedVendorDataById(params) as List

        def exportList = vendorsService.exportFailedCSVList(vendorUploadDataList)

        List fields = ['srNo', 'businessName', 'address', 'area', 'city', 'district', 'state', 'pinCode',
                       'primaryLandNumber', 'secondaryLandNumber', 'tertiaryLandNumber', 'username',
                       'primaryPhoneNumber', 'secondaryUsername', 'secondaryPhoneNumber', 'thirdPhoneNumber',
                       'businessEmail', 'secondaryEmail', 'websiteAddress', 'businessType', 'category', 'subCategory',
                       'product', 'aboutCompany', 'aboutCustomers', 'aboutSuppliers', 'yearOfEstablishment',
                       'gst', 'daysOpen', 'latitude', 'longitude', 'metaTitle', 'metaDescription', 'metaKeywords',
                       'directory', 'userID', 'logoImageUrl', 'photoGallery', 'error']

        Map labels = ['srNo'               : 'Sr No', 'businessName': 'business_name', 'address': 'address',
                      'area'               : 'area', 'city': 'city', 'district': 'district', 'state': 'state', 'pinCode': 'pincode',
                      'primaryLandNumber'  : 'primary_land_number', 'secondaryLandNumber': 'secondary_land_number', 'tertiaryLandNumber': 'tertiary_land_number', 'username': 'username',
                      'primaryPhoneNumber' : 'primary_phone_number', 'secondaryUsername': 'secondary_username', 'secondaryPhoneNumber': 'secondary_phone_number', 'thirdPhoneNumber': '3rd_phone_number', 'businessEmail': 'business_email',
                      'secondaryEmail'     : 'secondary_email', 'websiteAddress': 'website_address',
                      'businessType'       : 'business_type', 'category': 'category', 'subCategory': 'sub_category', 'product': 'Products_and_services', 'aboutCompany': 'about_company', 'aboutCustomers': 'about_customers', 'aboutSuppliers': 'about_suppliers',
                      'yearOfEstablishment': 'year_of_establishment', 'gst': 'gst', 'daysOpen': 'days_open', 'latitude': 'lattitude', 'longitude': 'longitude', 'metaTitle': 'meta_title', 'metaDescription': 'meta_description',
                      'metaKeywords'       : 'meta_keywords', 'directory': 'directory', 'userID': 'User ID', 'logoImageUrl': 'logo_image_url', 'photoGallery': 'photo_gallery', 'error': 'error']

        response.contentType = grailsApplication.config.grails.mime.types[params?.f]
        response.setHeader('Content-disposition', 'attachment; ' +
                "filename=failed_vendor_data_reports_${new Date()}.${params.extension}")
        exportService.export(params.f.toString(), response.outputStream, exportList, fields, labels, [:], [:])

    }
    def uploadView(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def vendorUploadDataList = vendorsService.listVendorUploadData(params)
        if (request.xhr) {
            render template: 'uploadDataList', model: [
                    vendorUploadDataList : vendorUploadDataList,
                    vendorUploadDataCount: vendorUploadDataList.totalCount
            ]
            return
        }
        respond vendorUploadDataList, model: [
                vendorUploadDataCount: vendorUploadDataList.totalCount,
                vendorUpload: VendorUpload.read(params?.id?.toString()?.toLong())
        ]
    }

    def toggleStatusToOnHold(Long toggleId) {
        if (toggleId == null) {
            notFound()
            return
        }
        String message = 'Vendor Business has been On Hold successfully'
        VendorBusinessDetails vendorBusinessDetails = VendorBusinessDetails.findById(toggleId)
        vendorBusinessDetails.isVerified = true
        vendorBusinessDetails.status = VendorBusinessStatus.ON_HOLD.value
        vendorsService.saveVendorBusinessDetails(vendorBusinessDetails)
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }
    def toggleStatusToActive(Long toggleIdActive) {
        if (toggleIdActive == null) {
            notFound()
            return
        }
        String message = 'Vendor Business has been Verified successfully'
        VendorBusinessDetails vendorBusinessDetails = VendorBusinessDetails.findById(toggleIdActive)
        vendorBusinessDetails.isVerified = true
        vendorBusinessDetails.status = VendorBusinessStatus.VERIFIED.value
        vendorsService.saveVendorBusinessDetails(vendorBusinessDetails)
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }
    def toggleStatus(Long toggleId) {
        if (toggleId == null) {
            notFound()
            return
        }
        String message
        VendorBusinessDetails vendorBusinessDetails = VendorBusinessDetails.findById(toggleId)
        vendorBusinessDetails.isVerified = true
        vendorBusinessDetails.status = VendorBusinessStatus.VERIFIED.value
        message = 'Vendor Business has been Verified successfully'
        vendorsService.saveVendorBusinessDetails(vendorBusinessDetails)
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"businessList", method:"GET"
            }
        }
    }

    def editVendorUploadData() {
        [vendorUploadDataInstance: VendorUploadData.get(params?.id)]
    }

    def correctVendorUploadData (VendorUploadData vendorUploadData) {
        if (!vendorUploadData?.validate()) {
            log.error("VendorUploadData Correct Error ? " + vendorUploadData?.errors)
            render(view: 'editVendorUploadData', model: [vendorUploadDataInstance: vendorUploadData])
            return
        }

        vendorUploadData = vendorsService.correctVendorUploadData(vendorUploadData)
        if (vendorUploadData?.status?.equals(VendorDataStatus.SUCCESS.status)) {
            flash.message = "Vendor Data Corrected Successfully."
        } else {
            flash.errorMessage = "Vendor Data Correction Failed."
        }

        redirect(action: 'uploadView', params: [id: vendorUploadData.vendorUpload.id])
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = "Vendor not found with id ${params.id}"
                // message(code: 'default.not.found.message', args: [message(code: 'vendors.label', default: 'Vendors'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
