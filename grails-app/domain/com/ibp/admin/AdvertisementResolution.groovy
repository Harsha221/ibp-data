package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class AdvertisementResolution extends BaseDomain {
    String advertisementResolutionId
    Advertisement advertisement
    String resolution
    String path
    String videoType
    String url

    static constraints = {
    }

    static mapping = {
        version false
        path  sqlType: 'text'
        url  sqlType: 'text'
    }
}
