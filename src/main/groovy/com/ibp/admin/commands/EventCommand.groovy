package com.ibp.admin.commands

import com.ibp.admin.Association
import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

import java.time.LocalDate

class EventCommand implements Validateable{
    String title
    String thumbUrl
    String bannerUrl
    LocalDate eventStartDate
    LocalDate eventEndDate
    String time
    MultipartFile thumbImageFile
    MultipartFile bannerImageFiles
    Association association
    String tags
    String venue
    String associationWith
    String description
    List<MultipartFile> imageFiles
    Boolean active = Boolean.FALSE
    static constraints = {
        title nullable: false, blank: false
//        description nullable: false, blank: false
        List fileFormats = Holders.grailsApplication.getConfig().getProperty('ibp.vendors.upload.imageFormat', List)
        thumbImageFile nullable: false, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }
            fileFormats.any { extension ->
                val?.originalFilename?.toLowerCase()?.endsWith(extension as String)
            }
        }
        bannerImageFiles nullable: false, validator: { val, obj ->
            if (val == null) {
                return true
            }
            if (val.empty) {
                return true
            }
            fileFormats.any { extension ->
                val?.originalFilename?.toLowerCase()?.endsWith(extension as String)
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
