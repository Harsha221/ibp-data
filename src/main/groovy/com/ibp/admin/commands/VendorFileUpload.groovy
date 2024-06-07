package com.ibp.admin.commands

import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class VendorFileUpload implements Validateable {

    MultipartFile uploadFile
    String description
    String uploadAction

    static constraints = {
        description nullable: false, blank: false
        uploadAction nullable: false, blank: false
        uploadFile nullable: false, blank: false, validator: { val, obj ->
            if ( val == null ) {
                return false
            }
            if ( val.empty ) {
                return false
            }
            List fileFormats = Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.fileFormat', List)
            println "fileFormats>>>>>>> ${fileFormats}"
            //['jpeg', 'jpg', 'png']
            fileFormats.any { extension ->
                val.originalFilename?.toLowerCase()?.endsWith(extension)
            }
        }
    }
}
