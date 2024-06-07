package com.ibp.admin.commands

import com.ibp.admin.Association
import grails.util.Holders
import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class AboutCommitteeCommand implements Validateable {
    String name
    Integer designation1
    String level1
    String companyName
    String address
    String year
    String mobileNo
    String email
    String tags
    Integer type1
    String typeId1
    Long associationId
    Boolean active = Boolean.FALSE
    MultipartFile imageFile

    static constraints = {
        name nullable: false, blank: false
        active nullable: false, blank: false
        imageFile nullable: true, validator: { val, obj ->
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
