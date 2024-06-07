package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class AdvertisementViews extends BaseDomain {

    String advertisementViewId
    Advertisement advertisement

    static constraints = {
    }

    static mapping = {
        version false

    }
}
