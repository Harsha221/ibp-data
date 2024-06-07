package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class News extends BaseDomain implements Cloneable{

    String newsId = UUID.randomUUID()
    String title
    String gujaratiTitle
    String sortDescription
    String description
    String category
    NewsCategory newsCategory
    String thumbUrl
    String imageUrl
    Association association
    String tags
    Boolean active = Boolean.FALSE
    String updatedBy
    Boolean isTopNews = Boolean.FALSE
    String createdBy
    String friendlyUrl

    static constraints = {
        friendlyUrl unique: true
    }
    static mapping = {
        version false
        description  sqlType: 'longText'
        sortDescription    sqlType: 'longText'
        thumbUrl sqlType: 'TEXT'
        imageUrl sqlType: 'TEXT'
        tags sqlType: 'TEXT'
    }
}
