package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class VendorMedia extends BaseDomain implements Cloneable{

    String vendorMediaUUID
    VendorBusinessDetails vendorBusiness
    Integer type
    String name
    String path
    String docType
    Long size
    String mediaVersion

    static mapping = {
        version false
        path  sqlType: 'text'
    }

    static constraints = {
    }
}
