package com.ibp.admin

class VendorsLoginController {

//    AuthenticationManager authenticationManager

    VendorsService vendorsService

    OtpService otpService

    def login() {
        render(view: "vendorsLogin")
    }

    def sendOtp() {
        otpService.generateOtp(params.mobileNo)
        render(view: "verifyOtp", model: [mobileNo: params.mobileNo])
    }

    def authenticate() {
        Map<String, String> vendorMap = new HashMap<>()
        vendorMap.put("name", params?.mobileNo)
        List<VendorBusinessDetails> vendorsList = vendorsService.list(vendorMap)
        if (true) {
            render template: 'list', model: [
                    vendorsList : vendorsList,
                    vendorsCount: vendorsList?.totalCount
            ]
            return
        }
        [vendorsList: vendorsList, vendorsCount: vendorsList?.totalCount, params: params]
//        System.getenv().put("IS_VENDOR_LOGIN", "true")

//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(params?.mobileNo, params?.otp))
//        Authentication authentication = authenticationManager.authenticate(new VendorsAuthenticationToken(params?.mobileNo, params?.otp))
//        Authentication authentication = authenticationManager.authenticate(new VendorsAuthenticationToken(params?.mobileNo, params?.otp))

        /*def token = new VendorsAuthenticationToken(params?.mobileNo, params?.otp)
        Authentication authentication = authenticationManager.authenticate(token)*/

        /*if (authentication.isAuthenticated()) {
            SecurityContextHolder.context.setAuthentication(authentication)
            redirect(uri: "/vendors/home")
        } else {
            redirect(uri: "/vendors/login?error=true")
        }*/
    }

    def home() {
        // Vendor home page
        log.info '[home] welcome home'
    }

    def logout() {
        // Handle logout
        session.invalidate()
        redirect(action: 'login')
    }
    
}
