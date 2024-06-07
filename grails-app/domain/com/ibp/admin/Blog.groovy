package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Blog extends BaseDomain implements Cloneable{

    String blogId = UUID.randomUUID()
    String title
    String sortDescription
    String description
    String category
    BlogCategory blogCategory
    String thumbUrl
    String tags
    Boolean active = Boolean.FALSE
    String updatedBy
    String createdBy
    String writtenBy
    String friendlyUrl

    static constraints = {
        friendlyUrl unique: true
    }
    static mapping = {
        version false
        description  sqlType: 'longText'
        sortDescription    sqlType: 'longText'
        thumbUrl sqlType: 'TEXT'
        tags sqlType: 'TEXT'
    }
}
