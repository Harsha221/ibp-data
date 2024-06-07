package com.ibp.admin

import com.ibp.admin.common.BaseDomain
import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class Role extends BaseDomain implements Serializable,Cloneable {

	private static final long serialVersionUID = 1
	String roleId
	String authority
	String roleName

	static hasMany = [
		permissions: ModulePermissions
	]

	static constraints = {
		authority nullable: false, blank: false, unique: true
		roleName nullable: false, blank: false, unique: true
		roleName matches: '[a-zA-Z0-9 ]+'
	}

	static mapping = {
		version false
		cache true
	}
}
