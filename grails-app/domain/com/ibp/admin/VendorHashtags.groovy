package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class VendorHashtags extends BaseDomain {

    String hashtagUUID
    String hashTag
    VendorBusinessDetails vendorBusiness

    static constraints = {
    }

    static mapping = {
        version false
    }
}
