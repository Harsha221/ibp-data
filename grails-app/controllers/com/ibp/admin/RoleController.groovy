package com.ibp.admin

import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class RoleController {

    RoleService roleService
    AuditLogsService auditLogsService
    UserService userService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def roleList = roleService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    roleList : roleList,
                    roleCount: roleList.totalCount
            ]
            return
        }
        respond roleList, model: [roleCount: roleList.totalCount]
    }

    def create() {
        [
                role             : new Role(params),
                modulePermissions: ModulePermissions.list()
        ]
    }

    def save(Role role) {

        if (role == null) {
            notFound()
            return
        }

        try {
            roleService.save(role, params)
            auditLogsService.logAction('Save',role.class.simpleName,role?.id,userService?.getLoggedInUser()?.username,(role as JSON).toString(false),(role as JSON).toString(false))

        } catch (ValidationException e) {
            respond role.errors, view: 'create', model: [
                    modulePermissions: ModulePermissions.list()
            ]
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = 'Role created successfully'
                //message(code: 'default.created.message', args: [message(code: 'role.label', default: 'Role'), role.id])
                redirect action: 'index' // role
            }
            '*' { respond role, [status: CREATED] }
        }
    }

    def edit(Long id) {
        Role role = roleService.get(id)
        if (role.authority == 'ROLE_SUPER_ADMIN') {
            redirect action: 'index'
            return
        }
        [
                role             : role,
                modulePermissions: ModulePermissions.list()
        ]
    }

    def update(Role role) {
        if (role == null) {
            notFound()
            return
        }
        def oldRole = role.clone()
        try {
            roleService.save(role, params)
            auditLogsService.logAction('Update',role?.class?.simpleName,role?.id,userService?.getLoggedInUser()?.username,(oldRole as JSON).toString(false),(role as JSON).toString(false))
        } catch (ValidationException e) {
            respond role.errors, view: 'edit', model: [
                modulePermissions: ModulePermissions.list()
            ]
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = 'Role updated successfully'
                //message(code: 'default.updated.message', args: [message(code: 'role.label', default: 'Role'), role.id])
                redirect action: 'index' // role
            }
            '*' { respond role, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        Role role = roleService.get(id)
        if (role.authority == 'ROLE_SUPER_ADMIN') {
            redirect action: 'index'
            return
        }
        roleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = 'Role deleted successfully'
                //message(code: 'default.deleted.message', args: [message(code: 'role.label', default: 'Role'), id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'role.label', default: 'Role'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
