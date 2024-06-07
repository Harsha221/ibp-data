package ibp.admin

import com.ibp.admin.AuthService

class BootStrap {

    AuthService authService

    def init = { servletContext ->
        authService.with {
            initConfig()
            intiRoles()
            initDefaultUser()
            initModulePermissions()
            initRequestMaps()
            initAssociations()
            initBlogCategory()
            initNewsCategory()
            initBusinessType()
        }
    }
    def destroy = {
    }
}
