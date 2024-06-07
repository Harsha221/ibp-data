package com.ibp.admin

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional(readOnly = true)
class UserService {

    SpringSecurityService springSecurityService

    def list(Map params) {
        UserProfile.createCriteria().list(params) {
            if (params?.name) {
                user {
                    ilike('username', "%${params?.name}%")
                }
            }
            if (params?.email) {
                ilike('email', "%${params?.email}%")
            }
            if(params?.role){
                eq('role', Role.findById(params?.role)?.roleName)
            }
            if (params?.searchUserStatus) {
                if(params?.searchUserStatus?.equals("Activated")) {
                    user {
                        eq('enabled', true)
                    }
                } else {
                    user {
                        eq('enabled', false)
                    }
                }
            }
        }
    }

    @Transactional
    UserProfile save(UserProfile userProfile) {
        userProfile.save(flush: true, failOnError: true)
    }

    @Transactional
    User save(User user) {
        user.save(flush: true, failOnError: true)
    }

    UserProfile get(Long id) {
        UserProfile.read(id)
    }

    @Transactional
    UserProfile delete(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }

    User getLoggedInUser() {
        springSecurityService.currentUser as User
    }

    @Transactional
    UserRole save(UserProfile userProfile, Role role) {
        UserRole.create(userProfile.user, role, true)
    }

    @Transactional
    UserRole saveUserRole(UserProfile userProfile) {
        Role role = Role.findByRoleName(userProfile.role)
        UserRole userRole = UserRole.findByUser(userProfile.user)
        userRole.setRole(role)
        userRole.save(flush: true, failOnError: true)
    }

    @Transactional
    UserRole save(UserRole userRole) {
        userRole.save(flush: true, failOnError: true)
    }
}
