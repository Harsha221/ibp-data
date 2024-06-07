package com.ibp.admin

import com.ibp.admin.commands.PublicationCommand
import grails.converters.JSON

import javax.xml.bind.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class PublicationController {
    PublicationService publicationService
    AwsService awsService
    UserService userService
    AuditLogsService auditLogsService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def publicationList = publicationService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    publicationList : publicationList,
                    publicationCount: publicationList.totalCount
            ]
            return
        }
        [publicationList : publicationList, publicationCount: publicationList.totalCount, params: params]
    }

    def create(){
        respond new Publication(params)
    }

    def save(PublicationCommand publicationCommand){
        if(publicationCommand == null){
            notFound()
            return
        }
        if(publicationCommand?.hasErrors()){
            render view: 'create' , model:[publicationCommand:publicationCommand]
            return
        }
        Publication publication = new Publication()
        bindData(publication,publicationCommand)
        try{
            publication?.status = publicationCommand?.active
            if(publicationCommand?.imageFile?.size>0){
                String thumbUrl = awsService.uploadImageToS3(publicationCommand?.imageFile,Constants.S3Folders.PUBLICATION,publication?.publicationId)
                publication?.thumbUrl = thumbUrl
            }
            if(publicationCommand?.imageFiles?.size() > 0) {
                publicationCommand?.imageFiles?.each {
                    if(it?.size > 0) {
                        publicationService.savePublicationPhotos(publication,it ,publication?.publicationId,)
                    }
                }
            }
            publication?.createdBy = userService?.getLoggedInUser()?.username
            publication?.updatedBy = userService?.getLoggedInUser()?.username
            publicationService.save(publication)
            auditLogsService.logAction('Save','Publication',publication?.id,userService?.getLoggedInUser()?.username,(publication as JSON).toString(false),(publication as JSON).toString(false))


        } catch (ValidationException e){
            respond publication.errors,view: 'create'
            return
        }
        request.withFormat {
            form multipartForm{
                flash.message = 'Publication Created successfully'
                redirect action: 'index'
            }
            '*'{ respond publication, [status: CREATED] }
        }
    }

    def edit(Long id){
        Publication publication = publicationService.get(id)
        def publicationPhotosList = PublicationPhotos.findAllByPublication(publication)
        [publication:publication,publicationPhotosList:publicationPhotosList]
    }

    def update(PublicationCommand publicationCommand){
        if(publicationCommand == null){
            notFound()
            return
        }
        Publication publication = Publication.findById(params?.id)
        if(!publication){
            notFound()
            return
        }
        def oldPublication = publication.clone()
        try{
            def publicationPhotosList = PublicationPhotos?.findAllByPublication(publication)
            publication?.status = publicationCommand?.active
            String thumbUrl = publication?.thumbUrl
            bindData(publication,publicationCommand)
            if(publicationCommand?.imageFile?.size>0){
                thumbUrl = awsService.uploadImageToS3(publicationCommand?.imageFile,'publication',"thumbUrl_"+publication?.publicationId)
                publication?.thumbUrl = thumbUrl
            }
            else if(publicationCommand?.imageFile?.size ==0 && thumbUrl){
                publication?.thumbUrl = thumbUrl
            }
            else{
                awsService.removeImageFromS3(thumbUrl,'prod/publication',"thumbUrl_"+publication?.publicationId)
            }
            List<PublicationPhotos> configAds = []
            String oldAds = ''
            log.info("Old value : "+params?.old)
            if (params?.old)
            {
                if (params?.old instanceof List) {
                    oldAds = params?.old ? (params?.old as List).join(',') : ''
                }
                else
                {
                    oldAds = params?.old
                }

            }
            publicationPhotosList?.each{
                if(oldAds?.indexOf(it?.id?.toString()) < 0) {
                    configAds.add(it)
                }
            }


            configAds?.each {
                awsService.removeImageFromS3(it.imageUrl, 'prod/publication', publication?.publicationId+"_"+it?.publicationPhotosId)
                //homepageConfig.removeFromHomePageAds(it)
                publicationService.removePublicationPhotos(it)
            }
            publicationCommand?.imageFiles?.each {
                if(!it.empty){
                    publicationService.savePublicationPhotos(publication,it,publication?.publicationId,)

                }

            }

            publication?.updatedBy = userService?.getLoggedInUser()?.username
            publicationService.save(publication)
            auditLogsService.logAction('Update','Publication',publication?.id,userService?.getLoggedInUser()?.username,(oldPublication as JSON).toString(false),(publication as JSON).toString(false))
        }
        catch (ValidationException ignored){
            respond publication.errors,view: 'edit'
            return
        }
        request.withFormat {
            from multipartForm{
                flash.message = 'Publication updated successfully'
                redirect action: 'index'
            }
            '*'{ respond publication, [status: OK] }
        }
    }
    protected void notFound(){
        request.withFormat {
            form multipartForm{
                flash.message = 'default.not.found.message'
                redirect action : "index" , method:"GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def toggleStatus (Long toggleId){
        if (toggleId == null){
            notFound()
            return
        }
        String message
        Publication publication = Publication.findById(toggleId)
        def oldPublicationStatus = publication.clone()
        if(publication?.status){
            publication.status = false
            message = 'Publication has been Deactivated successfully'
        }
        else{
            publication.status = true
            message = 'Publication has been Activated successfully'
        }
        publicationService.save(publication)
        if(publication?.status == false){
            auditLogsService.logAction('DeActivated','Publication',publication?.id,userService?.getLoggedInUser()?.username,(oldPublicationStatus as JSON).toString(false),(publication as JSON).toString(false))
        }
        else{
            auditLogsService.logAction('Activated','Publication',publication?.id,userService?.getLoggedInUser()?.username,(oldPublicationStatus as JSON).toString(false),(publication as JSON).toString(false))
        }
        request.withFormat {
            form multipartForm{
                flash.message = message
                redirect action: "index",method: "GET"
            }
        }
    }

}
