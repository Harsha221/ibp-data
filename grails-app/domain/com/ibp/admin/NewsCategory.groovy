package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class NewsCategory extends BaseDomain implements Cloneable{
    String newsCategoryId = UUID.randomUUID()
    String name
    String displayName
    Long associationId
    Boolean status = Boolean.FALSE
    String updatedBy
    String createdBy
    String thumbUrl

    static mapping = {
        version false
        thumbUrl sqlType: 'TEXT'
    }

    static constraints = {

    }
}
