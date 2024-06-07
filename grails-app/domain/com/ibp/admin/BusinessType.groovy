package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class BusinessType extends BaseDomain {
    String businessTypeId
    String name

    static mapping = {
        version false
    }

    static constraints = {

    }
}
