package com.ibp.admin

import com.ibp.admin.common.BaseDomain
import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes = 'name')
@ToString(includes = 'name', includeNames = true, includePackage = false)
class Department extends BaseDomain {
    String departmentId
    String name

    static constraints = {
        name nullable: false, blank: false, unique: true
    }

    static mapping = {
        version false
    }
}
