package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Product extends BaseDomain implements Cloneable{
    String productId
    String name
    String description
    Boolean active = Boolean.FALSE
    SubCategory subCategory
    Category category
    String metaTitle
    String metaDescription
    String metaKeyword
    String bannerUrl
    String displayName
    String updatedBy
    String createdBy

    static constraints = {

    }

    static mapping = {
        version false
        description  sqlType: 'longText'
        bannerUrl    sqlType: 'text'
        metaDescription sqlType: 'TEXT'
    }
}
