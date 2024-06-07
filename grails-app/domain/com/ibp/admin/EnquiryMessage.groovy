package com.ibp.admin

import com.ibp.admin.common.BaseDomain

import javax.persistence.Column
import java.time.LocalDateTime

class EnquiryMessage extends BaseDomain {
    String enquiryMessageId
    Enquiry enquiry
    UserClient userClient
    @Column(columnDefinition = 'TEXT')
    String message
    Integer accountType
    LocalDateTime messageDate

    static constraints = {
    }

    static mapping = {
        version false
        message  sqlType: 'text'
    }
}
