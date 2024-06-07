package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Customer extends BaseDomain {
    String customerId
    Long userClientId
    String email
    String mobileNo

    static constraints = {
    }
    static mapping = {
        version false
    }
}
