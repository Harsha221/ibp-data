package com.ibp.admin

import com.ibp.admin.common.BaseDomain

import java.time.LocalDateTime

class NewsSubscriber extends BaseDomain{
    String subscriberId = UUID.randomUUID()
    String email
    LocalDateTime subscriptionDate
    Boolean status = Boolean.FALSE

    static constraints = {
        email unique: true
    }

    static mapping = {
        version false
        email nullable: false, blank: false
    }

}
