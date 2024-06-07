package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class VendorBusinessViews extends BaseDomain {
    String vendorBusinessViewsId
    VendorBusinessDetails vendorBusiness

    static constraints = {
    }
    static mapping = {
        version false
    }
}
