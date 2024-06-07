package com.ibp.admin

import com.ibp.admin.commands.LogoCommand
import grails.converters.JSON

import javax.xml.bind.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class LogoController {
    LogoService logoService
    AwsService awsService
    UserService userService
    AuditLogsService auditLogsService

    def index(Integer max){
        params.max = Math.min(max ?: 20, 100)
        def logoList = logoService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    logoList : logoList,
                    logoCount: logoList.totalCount
            ]
            return
        }
        [logoList : logoList, logoCount: logoList.totalCount, params: params]
    }

    def create(){
        respond new Logo(params)
    }

    def save(LogoCommand logoCommand){
        if(logoCommand == null){
            notfound()
            return
        }
        if(logoCommand?.hasErrors()){
            render view:'create' , model:[logoCommand:logoCommand]
            return
        }
        Logo logo = new Logo()
        bindData(logo,logoCommand)
        try{
            logo?.status = logoCommand?.active
            if(logoCommand?.imageFile?.size>0){
                String logoUrl = awsService.uploadImageToS3(logoCommand?.imageFile,Constants.S3Folders.LOGO,"logoUrl_"+logo?.logoId)
                logo?.logoUrl = logoUrl
            }
            logo?.createdBy = userService?.getLoggedInUser()?.username
            logo?.updatedBy = userService?.getLoggedInUser()?.username
            logoService.save(logo)
            auditLogsService.logAction('Save','Logo',logo?.id,userService?.getLoggedInUser()?.username,(logo as JSON).toString(false),(logo as JSON).toString(false))
        }
        catch (ValidationException e){
            respond logo.errors,view:'create'
            return
        }
        request.withFormat {
            form multipartForm{
                flash.message = 'Logo Created successfully'
                redirect action: 'index'
            }
            '*'{ respond clients, [status: CREATED] }
        }

    }
    def edit(Long id){
        Logo logo = logoService.get(id)
        respond logo
    }

    def update(LogoCommand logoCommand){
        if(logoCommand == null){
            notFound()
            return
        }

        Logo logo = Logo.findById(params?.id)
        if(!logo){
            notFound()
            return
        }
        def oldLogo = logo.clone()
        try {
            logo?.status = logoCommand?.active
            String logoUrl = logo?.logoUrl
            bindData(logo,logoCommand)
            if(logoCommand?.imageFile?.size>0){
                logoUrl = awsService.uploadImageToS3(logoCommand?.imageFile,'logo',"logoUrl_"+logo?.logoId)
                logo?.logoUrl = logoUrl
            }
            else if(logoCommand?.imageFile?.size ==0 && logoUrl){
                logo?.logoUrl = logoUrl
            }
            else{
                awsService.removeImageFromS3(logoUrl,'prod/logo',"logoUrl_"+logo?.logoId)
            }
            logo?.updatedBy = userService?.getLoggedInUser()?.username
            logoService.save(logo)
            auditLogsService.logAction('Update','Logo',userService?.getLoggedInUser()?.username,(oldLogo as JSON).toString(false),(logo as JSON).toString(false))
        }
        catch (ValidationException ignored){
            respond logo.errors,view: 'edit'
            return
        }
        request.withFormat {
            from multipartForm{
                flash.message = 'Logo updated successfully '
                redirect action: 'index'
            }
            '*'{ respond clients, [status: OK] }
        }

    }
    protected void notFound(){
        request.withFormat {
            form multipartForm{
                flash.message = 'default.not.found.message'
                redirect action: "index",method:"GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def toggleStatus (Long toggleId){
        if(toggleId == null){
            notFound()
            return
        }
        String message
        Logo logo = Logo.findById(toggleId)
        def oldLogoStatus = logo.clone()
        if(logo?.status){
            logo.status = false
            message = 'Logo has been Deactivated successfully'
        }
        else{
            logo.status = true
            message = 'Logo  has been Activated successfully'
        }
        logoService.save(logo)

        if(logo?.status == false){
            auditLogsService.logAction('DeActivated','Logo',logo?.id,userService?.getLoggedInUser()?.username,(oldLogoStatus as JSON).toString(false),(logo as JSON).toString(false))
        }
        else {
            auditLogsService.logAction('Activated','Logo',logo?.id,userService?.getLoggedInUser()?.username,(oldLogoStatus as JSON).toString(false),(logo as JSON).toString(false))
        }

        request.withFormat {
            form multipartForm{
                flash.message = message
                redirect action: "Index",method: "GET"
            }
        }
    }





}
