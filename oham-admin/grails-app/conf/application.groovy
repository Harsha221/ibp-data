// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.oham.admin.AdminUsers'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.oham.admin.UserRole'
grails.plugin.springsecurity.authority.className = 'com.oham.admin.Role'
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
	[pattern: '/home', 			 access: ['permitAll']]
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
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/'
grails.plugin.springsecurity.logout.postOnly = false

// Session Management Configuration
grails.plugin.springsecurity.sessionManagement.sessionFixation = 'newSession'  // Prevent session fixation
grails.plugin.springsecurity.sessionManagement.maxSessions = 1   // Optional: Limit to 1 session per user
grails.plugin.springsecurity.sessionManagement.expiredUrl = '/login?expired=true'  // Redirect to login page on session expiration
grails.plugin.springsecurity.sessionManagement.sessionTimeout = 5 * 60 * 60	// 5hr

//grails.plugin.springsecurity.rejectIfNoRule = false
//grails.plugin.springsecurity.fii.rejectPublicInvocations = true

grails.gorm.default.constraints = {
	'*'(nullable: true, blank: true)
}


