package com.ibp.admin.commands

import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class SubCategoryCommand implements Validateable {

    String name
    Boolean active = Boolean.FALSE
    String description
    MultipartFile imageFile
    Long categoryId
    String bannerUrl


    static constraints = {
        name nullable: false, blank: false
        active nullable: false, blank: false
        imageFile nullable: false, blank: false, validator: { val, obj ->
            List fileFormats = Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.imageFormat', List)
            fileFormats.any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension)
            }
        }
    }
}
