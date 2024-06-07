package com.ibp.admin.commands

import com.ibp.admin.Association
import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

import java.time.LocalDate

class VendorCreateCommand implements Validateable {

    String id
    String category
    String subCategory
    String product
    String businessName
    String address
    String area
    String city
    String district
    String state
    String pinCode
    String primaryLandNumber
    String secondaryLandNumber
    String tertiaryLandNumber
    String hiddenPrimaryPhoneNumber
    String primaryPhoneNumber
    String secondaryPhoneNumber
    String thirdPhoneNumber
    String hiddenBusinessEmail
    String businessEmail
    String secondaryEmail
    String websiteAddress
    String username
    String secondaryUsername
    String businessType
    String aboutCompany
    String aboutCustomers
    String aboutSuppliers
    LocalDate yearOfEstablishment
    String gstNo
    String daysOpen
    String metaTitle
    String metaDescription
    String metaKeywords
    String latitude
    String longitude
    String tags
    String imageUrl
    MultipartFile panCardFile
    MultipartFile aadhaarCardFile
    MultipartFile certificationFile
    MultipartFile logoFile
    MultipartFile businessLicenceFile
    List<MultipartFile> imageFiles
    List<MultipartFile> videoFiles
    String association

    static constraints = {
        businessName nullable: false, blank: false
        address nullable: false, blank: false
        city nullable: false, blank: false
        district nullable: false, blank: false
        state nullable: false, blank: false
        businessEmail nullable: false, blank: false
        primaryPhoneNumber nullable: false, blank: false
        category nullable: false, blank: false
        subCategory nullable: false, blank: false
        product nullable: false, blank: false
        association nullable: false
        daysOpen nullable: false, blank: false
        List fileFormats = Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.imageFormat', List)
        panCardFile nullable: true, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }
            fileFormats.any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
        aadhaarCardFile nullable: true, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }
            fileFormats.any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
        certificationFile nullable: true, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }
            fileFormats.any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
        logoFile nullable: true, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }
            fileFormats.any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
        businessLicenceFile nullable: true, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }
            fileFormats.any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
        imageFiles nullable: true, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }

            fileFormats.any { extension ->
                val.each {
                    it?.originalFilename?.toLowerCase()?.endsWith(extension as String)
                }
            }
        }
        videoFiles nullable: true, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }

            fileFormats.any { extension ->
                val.each {
                    it?.originalFilename?.toLowerCase()?.endsWith(extension as String)
                }
            }
        }
    }
}
