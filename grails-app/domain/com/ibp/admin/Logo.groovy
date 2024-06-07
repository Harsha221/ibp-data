package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Logo extends BaseDomain implements Cloneable{

    String logoId = UUID.randomUUID()
    String name
    String logoUrl
    String websiteLink
    String updatedBy
    String createdBy
    Boolean status = Boolean.FALSE

    static mapping = {
        version false
        logoUrl sqlType: 'TEXT'
        websiteLink sqlType: 'TEXT'
    }

    static  constraints = {
    }

}
