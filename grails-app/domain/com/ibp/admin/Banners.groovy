package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Banners extends BaseDomain {

    String bannersId
    Association association
    String title
    String mobileBannersPath
    String webBannersPath
    Boolean status = Boolean.FALSE
    String updatedBy
    String createdBy

    static constraints = {
    }

    static mapping = {
        version false
        title  sqlType: 'text'
        mobileBannersPath  sqlType: 'text'
        webBannersPath  sqlType: 'text'
    }
}
