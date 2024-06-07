package com.ibp.admin

import com.ibp.admin.common.BaseDomain

import java.time.LocalDateTime

class UserClient extends BaseDomain {

    String userClientId
    String username
    String password
    String email //email1
    String phoneNumber // Mobile 1
    Integer type //Vendor=1
    String businessName
    Integer status //
    String otp
    LocalDateTime otpExpiredDate
    String fileName
    String logoUrl
    String fileExtension

    static constraints = {
    }
    static mapping = {
        version false
        logoUrl  sqlType: 'text'
    }
}
