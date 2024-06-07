package com.ibp.admin.commands

import com.ibp.admin.Association
import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class NewsCommand implements Validateable {

    String id
    String title
    String gujaratiTitle
    String sortDescription
    String description
    String category
    String thumbUrl
    String imageUrl
    MultipartFile imageFile
    MultipartFile imageFiles
    Association association
    String tags
    Boolean isTopNews = Boolean.FALSE
    Boolean active = Boolean.FALSE

    static constraints = {
        title nullable: false, blank: false
        sortDescription nullable: false, blank: false
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
        imageFiles nullable: false, validator: { val, obj ->
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
