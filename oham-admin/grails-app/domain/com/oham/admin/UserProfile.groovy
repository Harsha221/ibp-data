package com.oham.admin

import com.oham.admin.common.BaseDomain
import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes = 'email')
@ToString(includes = 'email', includeNames = true, includePackage = false)
class UserProfile extends BaseDomain implements Cloneable{

    String firstName
    String lastName
    String email
    String contactNo
    String role
    Boolean isReset = Boolean.FALSE


    static belongsTo = [
            user: AdminUsers
    ]

    static constraints = {
        email email: true
    }

    static mapping = {
        version false
    }
}
