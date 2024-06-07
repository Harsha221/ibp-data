package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class BlogCategory extends BaseDomain implements Cloneable{
    String blogCategoryId = UUID.randomUUID()
    String name
    String displayName
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
