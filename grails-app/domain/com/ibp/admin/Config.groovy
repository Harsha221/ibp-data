package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Config extends BaseDomain {

    String name
    String value
    boolean isActive = true
    String updatedBy
    String createdBy

    static constraints = {
        name nullable: false, blank: false
        value nullable: false, blank: false
    }

    static mapping = {
        version false
        value sqlType: 'longText'
    }
}
