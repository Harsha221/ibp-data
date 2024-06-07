package com.ibp.admin

import com.ibp.admin.commands.AssociationCommand
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class AssociationController {

    def awsService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def  associationService
    UserService userService
    AuditLogsService auditLogsService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def associationList = associationService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    associationList : associationList,
                    associationCount: associationList.totalCount
            ]
            return
        }
        respond associationList, model: [associationCount: associationList.totalCount]
    }

    def show(Long id) {
        respond associationService.get(id)
    }

    def create() {
        respond new Association(params)
    }

    def save(AssociationCommand associationCommand) {
        if (associationCommand == null) {
            notFound()
            return
        }
        if (associationCommand?.hasErrors()) {
            render view: 'create', model: [associationCommand: associationCommand]
            return
        }
        Association association = new Association()
        bindData(association, associationCommand)
        try {
            if (associationCommand?.headerFile?.size > 0) {
                String headerUrl = awsService.uploadImageToS3(associationCommand?.headerFile , 'association' , association?.associationId+'_header')
                association?.headerUrl = headerUrl
            }
            if (associationCommand?.logoFile?.size > 0) {
                String logoUrl = awsService.uploadImageToS3(associationCommand?.logoFile , 'association' , association?.associationId+'_logo')
                association?.logoUrl = logoUrl
            }
            if (associationCommand?.bannerFile?.size > 0) {
                String bannerUrl = awsService.uploadImageToS3(associationCommand?.bannerFile , 'association' , association?.associationId+'_banner')
                association?.bannerUrl = bannerUrl
            }
            if (associationCommand?.mobileBannerFile?.size > 0) {
                String mobileBannerUrl = awsService.uploadImageToS3(associationCommand?.mobileBannerFile , 'association' , association?.associationId+'_mobilebanner')
                association?.mobileBannerUrl = mobileBannerUrl
            }
            association?.createdBy = userService?.getLoggedInUser()?.username
            association?.updatedBy = userService?.getLoggedInUser()?.username
            associationService.save(association)
            auditLogsService.logAction('Save','Association',association?.id,userService.getLoggedInUser()?.username,(association as JSON).toString(false),(association as JSON).toString(false))
        } catch (ValidationException e) {
            respond association.errors, view:'create'
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = 'Association created successfully'
                redirect action: 'index'
            }
            '*' { respond association, [status: CREATED] }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = 'default.not.found.message'
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def edit(Long id) {
        Association association = associationService.get(id)
        respond association
    }

    def update(AssociationCommand associationCommand) {

        if (associationCommand == null) {
            notFound()
            return
        }
        /* if (associationCommand?.validate()) {
            println(associationCommand?.errors)
            render view: 'create', model: [associationCommand: associationCommand]
            return
        }*/
        Association associationInstance= Association.findById(params?.id)
        if (!associationInstance) {
            notFound()
            return
        }
        def oldAssociation = associationInstance.clone()
        try {
            String logoUrl = associationInstance?.logoUrl
            String headerUrl = associationInstance?.headerUrl
            String bannerUrl = associationInstance?.bannerUrl
            String mobileBannerUrl = associationInstance?.mobileBannerUrl
            bindData(associationInstance, associationCommand)
            if (associationCommand?.headerFile?.size >0) {
                headerUrl = awsService.uploadImageToS3(associationCommand?.headerFile, 'association', associationInstance?.associationId+'_header')
                associationInstance?.headerUrl = headerUrl
            } else if (associationCommand?.headerFile?.size == 0 && headerUrl) {
                associationInstance?.headerUrl = headerUrl
            }
            else {
                awsService.removeImageFromS3(headerUrl , 'prod/association' , associationInstance?.associationId+'_header')
            }
            if (associationCommand?.logoFile?.size >0) {
                logoUrl = awsService.uploadImageToS3(associationCommand?.logoFile, 'association', associationInstance?.associationId+'_logo')
                associationInstance?.logoUrl = logoUrl
            } else if (associationCommand?.logoFile?.size == 0 && logoUrl) {
                associationInstance?.logoUrl = logoUrl
            }
            else {
                awsService.removeImageFromS3(logoUrl , 'prod/association' , associationInstance?.associationId+'_logo')
            }
            if (associationCommand?.bannerFile?.size >0) {
                bannerUrl = awsService.uploadImageToS3(associationCommand?.bannerFile, 'association', associationInstance?.associationId+'_banner')
                associationInstance?.bannerUrl = bannerUrl
            } else if (associationCommand?.bannerFile?.size == 0 && bannerUrl) {
                associationInstance?.bannerUrl = bannerUrl
            }
            else {
                awsService.removeImageFromS3(bannerUrl , 'prod/association' , associationInstance?.associationId+'_banner')
            }
            if (associationCommand?.mobileBannerFile?.size >0) {
                mobileBannerUrl = awsService.uploadImageToS3(associationCommand?.mobileBannerFile, 'association', associationInstance?.associationId+'_mobilebanner')
                associationInstance?.mobileBannerUrl = mobileBannerUrl
            } else if (associationCommand?.mobileBannerFile?.size == 0 && mobileBannerUrl) {
                associationInstance?.mobileBannerUrl = mobileBannerUrl
            }
            else {
                awsService.removeImageFromS3(mobileBannerUrl , 'prod/association' , associationInstance?.associationId+'_mobilebanner')
            }
            associationInstance?.updatedBy = userService?.getLoggedInUser()?.username
            associationService.save(associationInstance)
            auditLogsService.logAction('Update','Association',associationInstance?.id,userService.getLoggedInUser()?.username,(oldAssociation as JSON).toString(false),(associationInstance as JSON).toString(false))
        } catch (ValidationException ignored) {
            respond associationInstance.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Association updated successfully'
                redirect action: 'index'
            }
            '*'{ respond associationInstance, [status: OK] }
        }
    }

    def toggleStatus(Long toggleId) {
        if (toggleId == null) {
            notFound()
            return
        }
        String message
        Association association = Association.findById(toggleId)
        def oldAssociationStatus = association.clone()
        if(association?.active) {
            association.active = false
            message = 'Association has been Deactivated successfully'
        }
        else {
            association.active = true
            message = 'Association has been Activated successfully'
        }
        associationService.save(association)

        if(association?.active == false){
            auditLogsService.logAction('DeActivated','Association',association?.id,userService?.getLoggedInUser()?.username,(oldAssociationStatus as JSON).toString(false),(association as JSON).toString(false))
        }
        else{
            auditLogsService.logAction('Activated','Association',association?.id,userService?.getLoggedInUser()?.username,(oldAssociationStatus as JSON).toString(false),(association as JSON).toString(false))
        }
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }
}
