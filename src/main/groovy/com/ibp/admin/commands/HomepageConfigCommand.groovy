package com.ibp.admin.commands

import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class HomepageConfigCommand implements Validateable {

    String id
    String hashTags
    List<MultipartFile> imageFiles

    static constraints = {
        hashTags nullable: false, blank: false
        imageFiles nullable: true, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val?.size() == 0) {
                return true
            }
            List fileFormats = Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.imageFormat', List)
            fileFormats.any { extension ->
                val.each {
                    it?.originalFilename?.toLowerCase()?.endsWith(extension as String)
                }
            }
        }
    }
}
