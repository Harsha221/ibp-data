package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class SubCategory extends BaseDomain implements Cloneable{
    String subCategoryId
    String name
    Boolean active = Boolean.FALSE
    String description
    String bannerUrl
    String updatedBy
    String createdBy
    String displayName
    static belongsTo = [category: Category]


    static constraints = {

    }

    static mapping = {
        version false
        description  sqlType: 'longText'
        bannerUrl    sqlType: 'text'
    }
}
