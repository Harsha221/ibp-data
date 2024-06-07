package com.ibp.admin

class VerifyUserDataController {

    VendorsService vendorsService

    def isUser(String phoneNumber) {
        log.info("[isUser] verifying user data.................................")
        render view: 'verify-user-data', model: [params: params]
    }

    def otpVerification() {

        log.info("[otpVerification] :: Entered")

        String otp = params?.otp
        log.info "[otpVerification] user OTP number = ${otp}"
        String phoneNumber = params?.phoneNumber
        log.info "[otpVerification] user phone number = ${phoneNumber}"

        if (otp == null) {
            //todo:
        }


        if (otp == "123456") {
            log.info "[otpVerification] OTP is correct"
            // Todo: provide form to edit user data
            def map = new HashMap<String, String>()
            map.put("name", phoneNumber)
            List<VendorBusinessDetails> vendorBusinessDetailsList = vendorsService.list(map)
            VendorBusinessDetails vendorBusinessDetails = vendorBusinessDetailsList.get(0)
            log.info "[otpVerification] user found email = ${vendorBusinessDetails.businessEmail}"
        } else {
            log.info "[otpVerification] OTP is incorrect"
        }

        log.info "[otpVerification] :: Exited"

        render view: 'user-detail', model: [
                vendorBusinessDetails: vendorBusinessDetails
        ]
    }

}
