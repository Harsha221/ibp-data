package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class ModulePermissions extends BaseDomain {
    String modulePermissionsId
    String moduleName
    String permissionName
    String urls

    static constraints = {
        moduleName blank: false, nullable: false
    }

    static mapping = {
        version false
    }

    static ModulePermissions build(String moduleName, String permissionName, List<String> urls) {
        new ModulePermissions(moduleName: moduleName, permissionName: permissionName, urls: urls?.join(','))
    }
}
