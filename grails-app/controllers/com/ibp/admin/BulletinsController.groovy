package com.ibp.admin

import com.ibp.admin.commands.BulletinsCommand
import org.apache.el.util.Validation
import software.amazon.awssdk.services.s3.endpoints.internal.Value

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class BulletinsController {
    def bulletinsService
    def awsService
    UserService userService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def bulletinsList = bulletinsService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    bulletinsList : bulletinsList,
                    bulletinsCount: bulletinsList.totalCount
            ]
            return
        }
        [bulletinsList : bulletinsList, bulletinsCount: bulletinsList?.totalCount, params: params]
    }

    def create(){
        respond new Bulletins(params)
    }

    def save(BulletinsCommand bulletinsCommand){
        if(bulletinsCommand == null) {
            notFound()
            return
        }
        if(bulletinsCommand?.hasErrors()){
            render view:'create' , model:[bulletinsCommand:bulletinsCommand]
            return
        }
        Bulletins bulletins = new Bulletins()
        bindData(bulletins,bulletinsCommand)
        try{
            bulletins?.status = bulletinsCommand?.active
            if(bulletinsCommand?.imageFile?.size>0){
                String thumbUrl = awsService.uploadImageToS3(bulletinsCommand?.imageFile,Constants.S3Folders.BULLETINS,"thumbUrl_"+bulletins?.bulletinsId)
                bulletins?.thumbUrl = thumbUrl
            }
            bulletins?.createdBy = userService?.getLoggedInUser()?.username
            bulletins?.updatedBy = userService?.getLoggedInUser()?.username
            bulletinsService.save(bulletins)
        } catch (ValidationException e){
            respond bulletins.errors,view: 'create'
            return
        }
        request.withFormat {
            form multipartForm{
                flash.message = 'Bulletins Created successfully'
                redirect action:'index'
            }
            '*' { respond bulletins, [status: CREATED] }
        }
    }

    def edit(Long id){
        Bulletins bulletins = bulletinsService.get(id)
        respond bulletins
    }

    def update(BulletinsCommand bulletinsCommand){
        if(bulletinsCommand == null){
            notFound()
            return
        }
        Bulletins bulletins = Bulletins.findById(params?.id)
        if(!bulletins){
            notFound()
            return
        }
        try{
            bulletins?.status = bulletinsCommand?.active
            String thumbUrl = bulletins?.thumbUrl
            bindData(bulletins,bulletinsCommand)
            if(bulletinsCommand?.imageFile?.size>0){
                thumbUrl = awsService.uploadImageToS3(bulletinsCommand?.imageFile,'bulletins',"thumbUrl_"+bulletins?.bulletinsId)
                bulletins?.thumbUrl = thumbUrl
            }
            else if(bulletinsCommand?.imageFile?.size == 0 && thumbUrl){
                bulletins?.thumbUrl = thumbUrl
            }
            else{
                awsService.removeImageFromS3(thumbUrl,'prod/bulletins',"thumbUrl_"+bulletins?.bulletinsId)
            }
            bulletins?.updatedBy = userService?.getLoggedInUser()?.username
            bulletinsService.save(bulletins)
        }
        catch (ValidationException ignored){
            respond bulletins.errors, view: 'edit'
            return
        }
        request.withFormat {
            from multipartForm{
                flash.message = 'Bulletins updated successfully'
                redirect action: 'index'
            }
            '*'{ respond bulletins, [status: OK] }
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
    def toggleStatus(Long toggleId) {
        if (toggleId == null) {
            notFound()
            return
        }

        String message
        Bulletins bulletins = Bulletins.findById(toggleId)
        if(bulletins?.status){
            bulletins.status = false
            message = 'Bulletins has been Deactivated successfully'
        }
        else {
            bulletins.status = true
            message = 'Bulletins has been Activated successfully'
        }
        bulletinsService.save(bulletins)
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }
}



