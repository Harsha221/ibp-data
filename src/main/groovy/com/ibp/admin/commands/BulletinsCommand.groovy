package com.ibp.admin.commands

import com.ibp.admin.Association
import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class BulletinsCommand implements Validateable{
    String title
    String youtubeVideoUrl
    String thumbUrl
    MultipartFile imageFile
    Association association
    Boolean active = Boolean.FALSE
    String tags
    static constraints = {
        title nullable: false, blank: false
        youtubeVideoUrl nullable: false, blank: false
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
