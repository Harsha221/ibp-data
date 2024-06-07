package com.ibp.admin

import com.ibp.admin.common.BaseDomain
import groovy.transform.ToString

@ToString(includeNames = true, includePackage = false)
class Advertisement extends BaseDomain implements Cloneable{

    String advertisementId
    String title
    String tags
    String adsType
    String videoType
    Association association
    String advertiseCategory
    String url
    String videoUrl
    String youtubeUrl
    String updatedBy
    String createdBy
    String adsPosition
    Boolean status = Boolean.FALSE


    static constraints = {
    }

    static mapping = {
        version false
        title  sqlType: 'text'
        tags  sqlType: 'text'
    }
}
