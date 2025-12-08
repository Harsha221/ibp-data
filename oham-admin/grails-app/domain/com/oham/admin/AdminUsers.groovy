package com.oham.admin

import com.oham.admin.common.BaseDomain

class AdminUsers extends BaseDomain {

    String username
    String password
    boolean enabled = true

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static hasOne = [
            userProfile: UserProfile
    ]
    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
    }

    static mapping = {
        version false
        password column: '`password`'
    }

}
