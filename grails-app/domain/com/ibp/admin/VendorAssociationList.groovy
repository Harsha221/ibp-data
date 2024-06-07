package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class VendorAssociationList extends BaseDomain implements Cloneable {

    VendorBusinessDetails vendorBusiness
    Association association
    String associationName

    static constraints = {
    }
    static mapping = {
        version false
    }
}