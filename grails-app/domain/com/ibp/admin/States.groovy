package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class States extends BaseDomain {
    String statesId
    String name
    Country country
    Boolean isPopular = Boolean.TRUE
    static constraints = {
    }
    static mapping = {
        version false
    }
}
