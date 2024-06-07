package com.ibp.admin.commands

import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class ProductFileUpload implements Validateable {

    MultipartFile uploadFile

    static constraints = {
        uploadFile nullable: false, blank: false, validator: { val, obj ->
            if ( val == null ) {
                return false
            }
            if ( val.empty ) {
                return false
            }
            List fileFormats = Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.fileFormat', List)
            fileFormats.any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension)
            }
        }
    }
}
