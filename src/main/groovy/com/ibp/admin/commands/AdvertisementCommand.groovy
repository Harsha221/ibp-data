package com.ibp.admin.commands

import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class AdvertisementCommand implements Validateable {

    String id
    String name
    Boolean active = Boolean.FALSE
    String videoUrl
    String youtubeUrl
    String iconUrl
    Long associationId
    MultipartFile imageFile
    MultipartFile videoFile
    String description
    String tags
    String mediaSelection
    String mySelections
    String adsPosition

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
        videoFile nullable: false, validator: { val, obj ->
                if (val == null) {
                    return true
                }
                if (val.empty) {
                    return true
                }
                List fileFormats = Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.videoFormat', List)
                fileFormats.any { extension ->
                    val.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
    }
}
