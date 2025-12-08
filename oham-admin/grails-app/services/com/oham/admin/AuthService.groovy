package com.oham.admin

import grails.gorm.transactions.Transactional

@Transactional
class AuthService {

    void intiRoles() {
        log.info "[intiRoles] :: Initializing default roles..."
        if(!Role.findByAuthority(Constants.ROLE_SUPER_ADMIN)) {
            Role role = new Role(authority: Constants.ROLE_SUPER_ADMIN, roleName: 'Super Admin').save(flush: true, failOnError: true)
            log.info "[intiRoles] :: Created Role :: Id: ${role?.id}, RoleName: ${role?.roleName}..."
        }
    }

    void initDefaultUser() {
        log.info "[initDefaultUser] :: Initializing users and roles..."
        if (!AdminUsers.findByUsername('superadmin')) {
            AdminUsers superAdminUser = new AdminUsers(username: 'superadmin', password: 'admin').save(flush: true, failOnError: true)
            Role superAdminRole = Role.findByAuthority(Constants.ROLE_SUPER_ADMIN)
            UserRole userRole = UserRole.create(superAdminUser, superAdminRole, true)
            log.info "[initDefaultUser] :: Created AdminUsers :: Id: ${superAdminUser?.id}, Username: ${superAdminUser?.username}..."
            log.info "[initDefaultUser] :: Created Role :: Id: ${superAdminRole?.id}, Authority: ${superAdminRole?.authority}..."
            log.info "[initDefaultUser] :: Created UserRole :: Id: ${userRole?.id}, UserId: ${userRole?.user?.id}, , RoleId: ${userRole?.role?.id}..."
        }
    }

}
