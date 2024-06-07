package com.ibp.admin

import com.ibp.admin.security.ModuleConfigs
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class AuthService {

    SpringSecurityService springSecurityService
    GrailsApplication grailsApplication

    void intiRoles() {
        log.info "[intiRoles] :: Initializing default roles..."
        if(!Role.findByAuthority(Constants.ROLE_SUPER_ADMIN)) {
            Role role = new Role(authority: Constants.ROLE_SUPER_ADMIN, roleName: 'Super Admin').save(flush: true, failOnError: true)
            log.info "[intiRoles] :: Created Role :: Id: ${role?.id}, RoleName: ${role?.roleName}..."
        }
    }

    void initRequestMaps() {
        log.info "[initRequestMaps] :: Initializing default request maps..."
        List<Map<String, Object>> APPLICATION_REQUEST_MAPS_STATIC_RULES = grailsApplication.config.getProperty(
                'grails.plugin.springsecurity.controllerAnnotations.staticRules', List
        )
        APPLICATION_REQUEST_MAPS_STATIC_RULES.each { rule ->
            String roles = (rule?.access as List).join(',')
            if (!Requestmap.findByUrlAndConfigAttribute(rule?.pattern as String, roles)) {
                Requestmap requestmap = new Requestmap(url: rule?.pattern, configAttribute: roles).save(flush: true, failOnError: true)
                log.info "[initRequestMaps] :: Creating requestmap : Id: ${requestmap?.id}, URL: ${rule?.pattern}, Access: ${roles}"
            }
        }

        Role superAdminRole = Role.findByAuthority(Constants.ROLE_SUPER_ADMIN)
        ModulePermissions.list().each { module ->
            if (!superAdminRole?.permissions?.contains(module)) {
                superAdminRole.addToPermissions(module)
                log.info "[initRequestMaps] :: Adding permission : Id: ${module?.id}, ${module?.moduleName}/${module?.permissionName}, Access: ${superAdminRole.authority}"
            }
            module?.urls?.tokenize(',')?.each { url ->
                Requestmap requestMap = Requestmap.findByUrlAndConfigAttributeLike(url, '%' + superAdminRole.authority + '%') ?: new Requestmap(url: url)
                if (!requestMap?.id) {
                    Set<String> roles = requestMap?.configAttribute?.tokenize(',')?.toSet() ?: [] as Set<String>
                    roles.add(superAdminRole.authority)
                    requestMap.configAttribute = roles.join(',')
                    requestMap.save(flush: true, failOnError: true)
                    log.info "[initRequestMaps] :: Adding requestmap : Id: ${requestMap?.id}, URL: ${requestMap?.url}, Access: ${superAdminRole.authority}"
                }
            }
        }
        superAdminRole.save(flush: true, failOnError: true)
        springSecurityService.clearCachedRequestmaps()
    }

    void initDefaultUser() {
        log.info "[initDefaultUser] :: Initializing users and roles..."
        if (!User.findByUsername('superadmin')) {
            User superAdminUser = new User(username: 'superadmin', password: 'admin').save(flush: true, failOnError: true)
            Role superAdminRole = Role.findByAuthority(Constants.ROLE_SUPER_ADMIN)
            UserRole userRole = UserRole.create(superAdminUser, superAdminRole, true)
            log.info "[initDefaultUser] :: Created User :: Id: ${superAdminUser?.id}, Username: ${superAdminUser?.username}..."
            log.info "[initDefaultUser] :: Created Role :: Id: ${superAdminRole?.id}, Authority: ${superAdminRole?.authority}..."
            log.info "[initDefaultUser] :: Created UserRole :: Id: ${userRole?.id}, UserId: ${userRole?.user?.id}, , RoleId: ${userRole?.role?.id}..."
        }
    }

    void initModulePermissions() {
        log.info "[initModulePermissions] :: Initializing module permissions..."
        ModuleConfigs.MODULE_PERMISSIONS.each { module ->
            ModulePermissions modulePermissions = ModulePermissions.findByModuleNameAndPermissionName(module.moduleName, module.permissionName)
            if (!modulePermissions) {
                module.save(flush: true, failOnError: true)
                log.info "[initModulePermissions] :: Created module permission Id: ${module?.id}: ${module?.moduleName} -> ${module?.permissionName} -> ${module?.urls}..."
            } else if (modulePermissions && modulePermissions.urls != module.urls) {
                modulePermissions.urls = module.urls
                modulePermissions.save(flush: true, failOnError: true)
                log.info "[initModulePermissions] :: Updating module permission Id: ${modulePermissions?.id}: ${modulePermissions?.moduleName} -> ${modulePermissions?.permissionName} -> ${modulePermissions?.urls}..."
            }
        }
    }

    void initAssociations() {
        if(Association?.count == 0) {
            log.info "[initAssociations] :: Initializing Association"
            List<String> associations = ['IBPHUB', 'GCCI']
            associations.each {
                Association association = new Association(name: it, associationId: UUID.randomUUID()).save(flush: true, failOnError: true)
                log.info "[initAssociations] :: Association Created :: Id: ${association?.id}...."
            }
        }
    }

    void initBlogCategory() {
        if(BlogCategory?.count == 0) {
            log.info "[initBlogCategory] :: Initializing Blog category"
            List<String> category = ['Local', 'Global', 'Technology', 'Medical', 'Hospitality', 'Textile', 'International']
            category.each {
                BlogCategory blogCategory = new BlogCategory(name: it, blogCategoryId: UUID.randomUUID()).save(flush: true, failOnError: true)
                log.info "[initBlogCategory] :: Blog Category Created :: Id: ${blogCategory?.id}...."
           }
        }
    }

    void initNewsCategory() {
        if(NewsCategory?.count == 0 ) {
        log.info "[initNewsCategory] :: Initializing News category"
        List<String> category = ['Local','Global','Technology','Medical','Hospitality','Textile','Law','Business']
        category.each {
            NewsCategory newsCategory = new NewsCategory(name: it ,newsCategoryId: UUID.randomUUID()).save(flush: true, failOnError: true)
            log.info "[initNewsCategory] :: News Category Created :: Id: ${newsCategory?.id}...."
            }
        }
    }

    void initBusinessType() {
        if(BusinessType?.count == 0 ) {
            log.info "[initBusinessType] :: Initializing Business Type"
            List<String> type = ['Manufacturer','Retailer','Supplier','Wholesaler','Exporter','Importer','Agency','Dealer','Distributor','Trader','Manufacturer & Supplier','Service Provider']
            type.each {
                BusinessType businessType = new BusinessType(name: it ,businessTypeId: UUID.randomUUID()).save(flush: true, failOnError: true)
                log.info "[initBusinessType] :: Business Type Created :: Id: ${businessType?.id}...."
            }
        }
    }

    void initConfig() {
        Constants.IbpConfigs.each {
            Config config = Config.findByName(it?.key?.toString()?.trim())
            if (!config) {
                new Config(name: it.key, value: it.value, isActive: true).save(flush: true, failOnError: true)
                log.info "[initConfig] :: Config Created :: Name:: ${it.key}...."
            }
        }
    }


}
