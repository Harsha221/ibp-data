package com.ibp.admin.commands

import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class AdvertisementRateCardCommand implements Validateable{

    String title
    String size
    String description
    Boolean isMultiplePages = Boolean.FALSE
    Integer amount
    Integer amountPage1
    Integer amountPage2
    Integer amountPage3
    Integer amountPage4
    Integer amountPage5
    String imageUrl
    MultipartFile imageFile
    Boolean active = Boolean.FALSE

    static constraints ={
        title nullable: false,blank: false
        active nullable: false, blank:false
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
