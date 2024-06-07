package com.ibp.admin.commands

import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class PublicationCommand implements Validateable{
    String title
    String thumbUrl
    String amazonLink
    String flipkartLink
    MultipartFile imageFile
    List<MultipartFile> imageFiles
    Boolean active = Boolean.FALSE
    static constraints ={
        title nullable: false, blank: false

        active nullable: false, blank:false
        List fileFormats = Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.imageFormat', List)
        imageFile nullable: false, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }
            //List fileFormats = Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.imageFormat', List)
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

    }


}
