package com.ibp.admin

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class VendorUploadController {

    VendorsService vendorsService
    UserService userService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static defaultAction = "index"

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def vendorsList = vendorsService.list(params)
        if (request.xhr) {
            render template: 'list', model:[
                vendorsList: vendorsList,
                vendorsCount: vendorsList.totalCount
            ]
            return
        }
        respond vendorsList, model:[vendorsCount: vendorsList.totalCount]
    }

    def create() {
        respond new Vendors(params)
    }

    def save(Vendors vendors) {
        if (vendors == null) {
            notFound()
            return
        }

        try {
            vendors?.createdBy = userService?.getLoggedInUser()?.username
            vendorsService.save(vendors)
        } catch (ValidationException e) {
            respond vendors.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Vendor created successfully'
                //message(code: 'default.created.message', args: [message(code: 'vendors.label', default: 'Vendors'), vendors.id])
                redirect action: 'index' // vendors
            }
            '*' { respond vendors, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond vendorsService.get(id)
    }

    def update(Vendors vendors) {
        if (vendors == null) {
            notFound()
            return
        }

        try {
            vendors?.updatedBy = userService?.getLoggedInUser()?.username
            vendorsService.save(vendors)
        } catch (ValidationException e) {
            respond vendors.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Vendor updated successfully'
                //message(code: 'default.updated.message', args: [message(code: 'vendors.label', default: 'Vendors'), vendors.id])
                redirect action: 'index' // vendors
            }
            '*'{ respond vendors, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        vendorsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = 'Vendor deleted successfully'
                //message(code: 'default.deleted.message', args: [message(code: 'vendors.label', default: 'Vendors'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = "Vendor not found with id ${params.id}"
                // message(code: 'default.not.found.message', args: [message(code: 'vendors.label', default: 'Vendors'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
