package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class VendorBusinessReview extends BaseDomain {

    String vendorBusinessReviewId
    VendorBusinessDetails vendorBusiness
    Integer rating
    String description

    static constraints = {
    }

    static mapping = {
        version false
        description sqlType: 'TEXT'
    }
}
