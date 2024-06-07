package com.ibp.admin.commands

import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class ProductCommand implements Validateable {

    String name
    String oldName
    String description
    Boolean active = Boolean.FALSE
    Long subCategoryId
    Long categoryId
    MultipartFile imageFile
    String metaTitle
    String metaDescription
    String metaKeyword
    String bannerUrl

    static constraints = {
        name nullable: false, blank: false
        active nullable: false, blank: false
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
    }
}
