package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Association extends BaseDomain implements Cloneable{

    String associationId = UUID.randomUUID()
    String name
    String logoUrl
    String headerUrl
    String redirectLink
    String bannerUrl
    String mobileBannerUrl
    Boolean active = Boolean.FALSE
    String updatedBy
    String createdBy

    static constraints = {
    }

    static mapping = {
        version false
        logoUrl    sqlType: 'text'
        headerUrl    sqlType: 'text'
        redirectLink    sqlType: 'text'
    }
}
