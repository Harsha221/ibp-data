package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Address extends BaseDomain {
    
    String addressId
    String address
    String area
    String city
    String district
    String pinCode
    String state
    String latitude
    String longitude

    static constraints = {
        address blank: false, nullable: false
        area blank: false, nullable: false
        city blank: false, nullable: false
        district blank: false, nullable: false
        pinCode blank: false, nullable: false
        state blank: false, nullable: false
        latitude blank: true, nullable: true
        longitude blank: true, nullable: true
    }

    static mapping = {
        version false
        address sqlType: 'mediumtext'
        pinCode length: 6
        city length: 45
        district length: 45
        state length: 50
        area length: 50
        latitude length: 45
        longitude length: 45
    }
}
