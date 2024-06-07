package com.ibp.admin

import com.ibp.admin.common.BaseDomain
import com.ibp.admin.utils.IbpHubUtils

class AffiliationProductCategory extends BaseDomain {

    String affiliationProductCategoryId
    Affiliation affiliation
    String name
    String websiteLink
    String imagePath
    Boolean active = Boolean.FALSE
    String displayName

    String updatedBy
    String createdBy

    static constraints = {
    }
    static mapping = {
        version false
    }
}
