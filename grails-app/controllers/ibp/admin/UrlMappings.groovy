package ibp.admin

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(redirect: "/login/auth")
        "/vendors"(view: "/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        "/vendors/login"(controller: 'vendors', action: 'login')
        "/vendors/sendOtp"(controller: 'vendors', action: 'sendOtp')
        "/vendors/authenticate"(controller: 'vendors', action: 'authenticate')
        "/vendors/update/$id"(controller: 'vendors', action: 'update')
        "/vendors/editByVendor/$id?"(controller: 'vendors', action: 'edit')

    }
}
