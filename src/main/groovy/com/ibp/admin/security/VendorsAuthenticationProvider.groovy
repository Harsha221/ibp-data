package com.ibp.admin.security

import com.ibp.admin.OtpService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority

class VendorsAuthenticationProvider implements AuthenticationProvider {

    OtpService otpService

    VendorsAuthenticationProvider(OtpService otpService) {
        this.otpService = otpService
    }
    VendorsAuthenticationProvider() {    }

    @Override
    Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String mobileNo = authentication.getPrincipal()
        String otp = authentication.getCredentials()

        if (otpService.validateOtp(mobileNo, otp)) {
            List<GrantedAuthority> authorities = getAuthoritiesForVendor(mobileNo)
            return new UsernamePasswordAuthenticationToken(mobileNo, otp, authorities)
        }
        throw new BadCredentialsException("Invalid OTP")
    }

    @Override
    boolean supports(Class<?> authentication) {
//        return VendorsAuthenticationToken.isAssignableFrom(authentication)
        return VendorsAuthenticationToken.class.isAssignableFrom(authentication)
    }

    private List<GrantedAuthority> getAuthoritiesForVendor(String mobileNo) {
        // Fetch and return the authorities for the vendor
        return Collections.emptyList()
    }

}
