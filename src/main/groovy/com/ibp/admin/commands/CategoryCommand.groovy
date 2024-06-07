package com.ibp.admin.commands

import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class CategoryCommand implements Validateable {

    String id
    String name
    Boolean active = Boolean.FALSE
    String bannerUrl
    String iconUrl
    Integer sequence
    MultipartFile imageFile
    MultipartFile iconImageFile
    String description
    String metaTitle
    String metaDescription
    String metaKeyword

    static constraints = {
        name nullable: false, blank: false
        description nullable: false, blank: false
        imageFile nullable: false, validator: { val, obj ->
                if (val == null) {
                    return true
                }
                if (val.empty) {
                    return true
                }
                List fileFormats = Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.imageFormat', List)
                fileFormats.any { extension ->
                    val.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
        iconImageFile nullable: false, validator: { val, obj ->
                if (val == null) {
                    return true
                }
                if (val.empty) {
                    return true
                }
                List fileFormats = Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.imageFormat', List)
                fileFormats.any { extension ->
                    val.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
    }
}
