// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.ibp.admin.User'
//grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.ibp.admin.Vendors'//getUserDomainClassName()// 'com.ibp.admin.User'
//grails.plugin.springsecurity.userLookup.usernamePropertyName = 'mobileNo'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.ibp.admin.UserRole'
grails.plugin.springsecurity.authority.className = 'com.ibp.admin.Role'
grails.plugin.springsecurity.requestMap.className = 'com.ibp.admin.Requestmap'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'
//grails.plugin.springsecurity.securityConfigType = "Annotation"
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/passwordReset/**', access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/login', 		 access: ['permitAll']],
	[pattern: '/login.*', 		 access: ['permitAll']],
	[pattern: '/login/*', 		 access: ['permitAll']],
	[pattern: '/logout', 		 access: ['permitAll']],
	[pattern: '/logout.*', 		 access: ['permitAll']],
	[pattern: '/logout/*', 		 access: ['permitAll']],
	[pattern: '/home', 			 access: ['permitAll']],
	[pattern: '/vendors/editByVendor/*', access: ['permitAll']],
	[pattern: '/editByVendor/**', access: ['permitAll']],
	[pattern: '/vendors/login',     access: ['permitAll']],
	[pattern: '/vendors/update/*',     access: ['permitAll']],
	[pattern: '/vendors/sendOtp',   access: ['permitAll']],
	[pattern: '/vendors/authenticate', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.successHandler.alwaysUseDefault = true
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/vendors/uploads'
grails.plugin.springsecurity.logout.postOnly = false

//grails.plugin.springsecurity.rejectIfNoRule = false
//grails.plugin.springsecurity.fii.rejectPublicInvocations = true

grails.gorm.default.constraints = {
	'*'(nullable: true, blank: true)
}


