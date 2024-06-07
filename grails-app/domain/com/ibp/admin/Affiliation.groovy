package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Affiliation extends BaseDomain implements Cloneable{

    String affiliationId
    String name
    String websiteLink
    String displayName
    String imagePath
    Boolean active = Boolean.FALSE
    String updatedBy
    String createdBy

    static constraints = {
    }
    static mapping = {
        version false
    }
}
