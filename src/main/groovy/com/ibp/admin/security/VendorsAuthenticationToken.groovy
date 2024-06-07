package com.ibp.admin.security


import org.springframework.security.authentication.AbstractAuthenticationToken

class VendorsAuthenticationToken extends AbstractAuthenticationToken {

    private final String mobileNo
    private final String otp

    VendorsAuthenticationToken(String mobileNo, String otp) {
        super(null)
        this.mobileNo = mobileNo
        this.otp = otp
        setAuthenticated(false)
    }

    VendorsAuthenticationToken(String mobileNo, String otp, Collection authorities) {
        super(authorities)
        this.mobileNo = mobileNo
        this.otp = otp
//        this.authenticated = true
        setAuthenticated(true)
    }

    String getMobileNo() {
        return this.mobileNo
    }

    String getOtp() {
        return this.otp
    }

    @Override
    Object getCredentials() {
        return this.otp
    }

    @Override
    Object getPrincipal() {
        return this.mobileNo
    }
}
