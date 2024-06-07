package com.ibp.admin

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional(readOnly = true)
class RoleService {

    SpringSecurityService springSecurityService

    def list(Map params) {
        Role.createCriteria().list(params) {
            if (params?.roleName) {
                ilike('roleName', "%${params?.roleName}%")
            }
        }
    }

    @Transactional
    Role save(Role role, Map params) {
        role.authority = 'ROLE_' + role.roleName.trim().replaceAll(' ', '_').toUpperCase()
        List<String> updatedPermission = params.modulePermissions ? Arrays.asList(params.modulePermissions) as List<String> : [] as List<String>
        List<String> currentPermission = Role?.read(role.id) ? Role.read(role.id).permissions.collect { it?.id?.toString() } as List<String> : []
        List<String> deletedPermission = currentPermission - updatedPermission
        List<String> addedPermission = updatedPermission - currentPermission
        log.info "[save] addedPermission: ${addedPermission}"
        log.info "[save] deletedPermission: ${deletedPermission}"
        if (addedPermission) {
            ModulePermissions.findAllByIdInList(addedPermission)?.each { modulePermission ->
                role.addToPermissions(modulePermission)
                modulePermission.urls?.tokenize(',')?.each { url ->
                    Requestmap requestMap = Requestmap.findByUrl(url) ?: new Requestmap(url: url)
                    log.info "[save] :: Adding requestmap : URL: ${requestMap?.url}, Access: ${role.authority}"
                    Set<String> roles = requestMap?.configAttribute?.tokenize(',')?.toSet() ?: [] as Set<String>
                    roles.add(role.authority)
                    requestMap.configAttribute = roles.join(',')
                    requestMap.save(flush: true, failOnError: true)
                }
            }
        }
        if (deletedPermission) {
            ModulePermissions.findAllByIdInList(deletedPermission)?.each { modulePermission ->
                role.removeFromPermissions(modulePermission)
                modulePermission.urls?.tokenize(',')?.each { url ->
                    Requestmap requestMap = Requestmap.findByUrl(url)
                    if (requestMap) {
                        log.info "[save] :: Deleting requestmap : URL: ${requestMap?.url}, Access: ${role.authority}"
                        Set<String> roles = requestMap?.configAttribute?.tokenize(',')?.toSet() ?: [] as Set<String>
                        roles.remove(role.authority)
                        requestMap.configAttribute = roles.join(',')
                        requestMap.save(flush: true, failOnError: true)
                    }
                }
            }
        }
        role.save(flush: true, failOnError: true)
        springSecurityService.clearCachedRequestmaps()
    }

    Role get(Long id) {
        Role.read(id)
    }

    @Transactional
    Role delete(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }

}
