package com.ibp.admin

import com.ibp.admin.common.BaseDomain

import javax.persistence.Column

class Enquiry extends BaseDomain {
    String enquiryId
    Long createdFromId
    Long createdToId
    VendorBusinessDetails vendorBusinessDetails
    String subject
    String location
    String name
    String email
    Boolean isRead = Boolean.FALSE
    String mobileNo
    String status
    Integer accountType

    static constraints = {
    }

    static mapping = {
        version false
        subject  sqlType: 'text'
    }
}
