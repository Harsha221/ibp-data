package com.ibp.admin.commands

import grails.validation.Validateable

class VendorUploadDataCommand implements Validateable {

    String category
    String subCategory
    String product
    String businessName
//    String businessCategory
    String address
    String city
    String district
    String state
    String primaryPhoneNumber
    String businessEmail

    static constraints = {
        businessName nullable: false, blank: false
//        businessCategory nullable: false, blank: false
        address nullable: false, blank: false
        city nullable: false, blank: false
        district nullable: false, blank: false
        state nullable: false, blank: false
        businessEmail nullable: false, blank: false
        primaryPhoneNumber nullable: false, blank: false

        category nullable: false, blank: false
        subCategory nullable: false, blank: false
        product nullable: false, blank: false
    }
}
