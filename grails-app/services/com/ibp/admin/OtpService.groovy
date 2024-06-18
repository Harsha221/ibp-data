package com.ibp.admin

import grails.gorm.transactions.Transactional

import java.time.LocalDateTime
import java.time.ZoneId

@Transactional
class OtpService {

    def generateOtp(String mobileNo) {
        def vendor = Vendors.findByMobileNo(mobileNo)
        if (!vendor) {
            throw new IllegalArgumentException("Vendor not found")
        }

        String otp = (100000 + new Random().nextInt(900000)).toString()
//        String otp = "123456"

        // Convert LocalDateTime to Date
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(360)
        Date otpExpiry = Date.from(expiryTime.atZone(ZoneId.systemDefault()).toInstant())

        vendor.otp = otp
        vendor.otpExpiry = otpExpiry // OTP valid for 5 minutes
        vendor.save(flush: true)

        // Send OTP via SMS (implementation depends on your SMS service provider)
        return vendor
    }

    def validateOtp(String mobileNo, String otp) {
        def vendor = Vendors.findByMobileNo(mobileNo)
        if (!vendor) {
            return false
        }
        if (vendor.otp == otp && vendor.otpExpiry > new Date()) {
            return true
        }
        return false
    }

}
