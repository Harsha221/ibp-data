package com.ibp.admin

import com.ibp.admin.common.BaseDomain
import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes = ['configAttribute', 'url'])
@ToString(includes = ['configAttribute', 'url'], cache = true, includeNames = true, includePackage = false)
class Requestmap extends BaseDomain implements Serializable {

    private static final long serialVersionUID = 1
    String configAttribute
    //HttpMethod httpMethod
    String url

    static constraints = {
        configAttribute nullable: false, blank: false
        //httpMethod nullable: true
        url nullable: false, blank: false, unique: true // 'httpMethod'
    }

    static mapping = {
        version false
        cache true
    }

}
