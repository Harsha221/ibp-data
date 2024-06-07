package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Country extends BaseDomain {
    String countryId
    String code
    String name
    static constraints = {
    }
    static mapping = {
        version false
    }
}
