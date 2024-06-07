package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Cities extends BaseDomain {
    String cityId
    String name
    States state
    Boolean isPopular = Boolean.TRUE
    Boolean isActive = Boolean.TRUE
    static constraints = {
    }
    static mapping = {
        version false
    }
}
