package com.ibp.admin

import com.ibp.admin.commands.HomeBannersCommand
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class HomeBannersController {

    def homeBannersService
    UserService userService
    AwsService awsService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def homeBannersList = homeBannersService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    homeBannersList : homeBannersList,
                    homeBannersCount: homeBannersList.totalCount
            ]
            return
        }
        [homeBannersList: homeBannersList, homeBannersCount: homeBannersList?.totalCount, params: params]
    }

    def create() {
        respond new HomeBanners(params)
    }
    def save(HomeBannersCommand homeBannersCommand){
        log.info("Homebanner command   "+homeBannersCommand);

        if(homeBannersCommand == null){
            notFound()
            return
        }
        if(homeBannersCommand?.hasErrors()){
            render view: 'create', model: [homeBannersCommand: homeBannersCommand]
            return
        }
        HomeBanners homeBanners = new HomeBanners()
        bindData(homeBanners,homeBannersCommand)
        try{
            if(homeBannersCommand?.imageFile?.size>0){
                String thumbUrl = awsService.uploadImageToS3(homeBannersCommand?.imageFile,Constants.S3Folders.HOMEBANNERS,"thumbUrl_"+homeBanners?.homeBannersId)
                homeBanners?.thumbUrl = thumbUrl
            }
            if(homeBannersCommand?.imageFiles?.size>0){
                String imageUrl = awsService.uploadImageToS3(homeBannersCommand?.imageFiles,Constants.S3Folders.HOMEBANNERS,"imageUrl_"+homeBanners?.homeBannersId)
                homeBanners?.imageUrl = imageUrl
            }
            homeBanners?.createdBy = userService?.getLoggedInUser()?.username
            homeBanners?.updatedBy = userService?.getLoggedInUser()?.username
            log.info("thumb url"+homeBanners?.thumbUrl);


            homeBannersService.save(homeBanners)
        } catch (ValidationException e){
            respond homeBanners.errors,view: 'create'
            return
        }
        request.withFormat {
            form multipartForm{
                flash.message = 'Home Banners Created successfully'
                redirect action: 'index'
            }
            '*' { respond homeBanners, [status: CREATED] }
        }
    }

    def edit(Long id){
        HomeBanners homeBanners = homeBannersService.get(id)
        respond homeBanners
    }

    def update(HomeBannersCommand homeBannersCommand){
        if(homeBannersCommand == null){
            notFound()
            return
        }
        HomeBanners homeBanners = HomeBanners.findById(params?.id)
        if(!homeBanners){
            notFound()
            return
        }
        try{

            String thumbUrl = homeBanners?.thumbUrl
            String imageUrl = homeBanners?.imageUrl
            bindData(homeBanners,homeBannersCommand)
            if(homeBannersCommand?.imageFile?.size>0){
                thumbUrl = awsService.uploadImageToS3(homeBannersCommand?.imageFile,'homeBanners',"thumbUrl_"+homeBanners?.homeBannersId)
                homeBanners?.thumbUrl = thumbUrl
            }else if(homeBannersCommand?.imageFile?.size == 0 && thumbUrl){
                 homeBanners?.thumbUrl = thumbUrl
            }
            else{
                awsService.removeImageFromS3(thumbUrl,'prod/homeBanners',"thumbUrl_"+homeBanners?.homeBannersId)
            }
            if (homeBannersCommand?.imageFiles?.size >0) {
                imageUrl = awsService.uploadImageToS3(homeBannersCommand?.imageFiles, 'homeBanners', "imageUrl_"+homeBanners?.homeBannersId)
                homeBanners?.imageUrl = imageUrl
            } else if (homeBannersCommand?.imageFiles?.size == 0 && imageUrl) {
                homeBanners?.imageUrl = imageUrl
            }
            else {
                awsService.removeImageFromS3(imageUrl , 'prod/homeBanners' ,"imageUrl_"+ homeBanners?.homeBannersId)
            }
            homeBanners?.updatedBy = userService?.getLoggedInUser()?.username
            homeBannersService.save(homeBanners)
        }catch (ValidationException ignored){
            respond homeBanners.errors, view: 'edit'
            return
        }
        request.withFormat {
            from multipartForm{
                flash.message = 'Home Banners updated successfully'
                redirect action: 'index'
            }
            '*'{ respond homeBanners, [status: OK] }
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
        HomeBanners homeBanners = HomeBanners.findById(toggleId)
        if(homeBanners?.active){
            homeBanners.active = false
            message = 'Home Banners has been Deactivated successfully'
        }
        else {
            homeBanners.active = true
            message = 'Home Banners has been Activated successfully'
        }
        homeBannersService.save(homeBanners)
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }
}
