package com.ibp.admin

import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserController {

    UserService userService
    AmazonSESService amazonSESService
    AuditLogsService auditLogsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def userList = userService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    userList : userList,
                    userCount: userList.totalCount
            ]
            return
        }
        respond userList, model: [userCount: userList.totalCount, userList: userList]
    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new UserProfile(params)
    }

    def save(UserProfile userProfile) {
        if (userProfile == null) {
            notFound()
            return
        }
        def password = userProfile?.user?.password
        def fname = userProfile?.firstName
        def lname = userProfile?.lastName

        try {
            Role role = Role.findByRoleName(userProfile.role)
            userService.save(userProfile)
            auditLogsService.logAction('Save',userProfile.class.simpleName,userProfile?.id,userService.getLoggedInUser().username,(userProfile as JSON).toString(false),(userProfile as JSON).toString(false))
            userService.save(userProfile, role)
            amazonSESService.sendConfirmationEmail(userProfile.email,userProfile?.user?.username, password,fname,lname)

        } catch (ValidationException e) {
            respond userProfile.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'User created successfully' //message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action: 'index'
            }
            '*' { respond userProfile, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userService.get(id)
    }

    def update(UserProfile userProfile) {
        if (userProfile == null) {
            notFound()
            return
        }
        def oldUserProfile = userProfile.clone()
        try {
            userService.save(userProfile)
            auditLogsService.logAction('Update',userProfile.class.simpleName,userProfile?.id,userService?.getLoggedInUser()?.username,(oldUserProfile as JSON).toString(false),(userProfile as JSON).toString(false))
            //userService.saveUserRole(userProfile)
        } catch (ValidationException e) {
            e.printStackTrace()
            respond userProfile.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'User updated successfully' //message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action: 'index'
            }
            '*'{ respond userProfile, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = 'User deleted successfully' //message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def toggleStatus(Long toggleId) {
        if (toggleId == null) {
            notFound()
            return
        }
        String message
        User user = UserProfile.findById(toggleId)?.user
        if(user?.enabled) {
            user.enabled = false
            message = 'User has been Deactivated successfully'
        }
        else {
            user.enabled = true
            message = 'User has been Activated successfully'
        }
        userService.save(user)
        userService.save(UserProfile.findById(toggleId))
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }
}
