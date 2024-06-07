package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class UserRefreshToken extends BaseDomain {
    String userRefreshTokenId
    String username
    String refreshToken
    byte[] authentication

    static constraints = {
    }
    static mapping = {
        version false
        authentication sqlType: 'BLOB'
    }
}
