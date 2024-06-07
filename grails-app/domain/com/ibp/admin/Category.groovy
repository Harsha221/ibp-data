package com.ibp.admin

import com.ibp.admin.common.BaseDomain
import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.web.multipart.MultipartFile

@GrailsCompileStatic
@EqualsAndHashCode(includes = ['name', 'active'])
@ToString(includes = ['name', 'active'], includeNames = true, includePackage = false)
class Category extends BaseDomain implements Cloneable {

    String categoryId
    String name
    Boolean active = Boolean.FALSE
    String description
    String bannerUrl
    String iconUrl
    String metaTitle
    String metaDescription
    String metaKeyword
    String updatedBy
    String createdBy
    String displayName
    Integer sequence

    static constraints = {

        name nullable: false, blank: false
        active nullable: true, blank: false
    }

    static mapping = {
        version false
        description  sqlType: 'longText'
        bannerUrl    sqlType: 'text'
        metaDescription sqlType: 'TEXT'
    }
}
