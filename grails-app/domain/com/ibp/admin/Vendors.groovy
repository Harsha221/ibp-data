package com.ibp.admin

import com.ibp.admin.common.BaseDomain
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class Vendors extends BaseDomain implements Cloneable, UserDetails {

    String vendorId
    UserClient userClient
    String email
    String mobileNo
    String updatedBy
    String createdBy
    String otp
    Date otpExpiry
    Boolean modifiedByVendor = false    // for Vendors when they verify their data

    static constraints = {
        vendorId blank: true, nullable: true, unique: true
        mobileNo unique: true, nullable: false
        otp nullable: true
        otpExpiry nullable: true
    }

    static mapping = {
        version false
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return null
    }

    @Override
    String getPassword() {
        return otp
    }

    @Override
    String getUsername() {
        return mobileNo
    }

    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return false
    }
}
