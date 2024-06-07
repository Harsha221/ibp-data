package com.ibp.admin

import com.ibp.admin.common.BaseDomain
import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes = 'email')
@ToString(includes = 'email', includeNames = true, includePackage = false)
class UserProfile extends BaseDomain implements Cloneable{
    String userProfileId = UUID.randomUUID()
    String firstName
    String lastName
    String email
    String contactNo
    Department department
    String role
    Boolean isReset = Boolean.FALSE


    static belongsTo = [
            user: User
    ]

    static constraints = {
        email email: true
    }

    static mapping = {
        version false
    }
}
