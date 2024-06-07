package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class ContactUs extends BaseDomain {
    String contactUsId
    String email
    String phoneNumber
    String name
    String subject
    String message

    static mapping = {
        message sqlType: 'TEXT'
        version false
    }

    static constraints = {
    }
}
