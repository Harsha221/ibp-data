package com.ibp.admin

import com.ibp.admin.commands.NewsCommand
import com.ibp.admin.commands.VideoGalleryCommand
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class VideoGalleryController {

    def videoGalleryService
    def awsService
    UserService userService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def videoGalleryList = videoGalleryService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    videoGalleryList : videoGalleryList,
                    videoGalleryCount: videoGalleryList.totalCount
            ]
            return
        }
        [videoGalleryList: videoGalleryList, videoGalleryCount: videoGalleryList?.totalCount, params: params]
    }
    def toggleStatus(Long toggleId) {
        if (toggleId == null) {
            notFound()
            return
        }
        String message
        VideoGallery videoGallery = VideoGallery.findById(toggleId)
        if (videoGallery?.status) {
            videoGallery.status = false
            message = 'Video Gallery has been Deactivated successfully'
        } else {
            videoGallery.status = true
            message = 'Video Gallery has been Activated successfully'
        }
        videoGalleryService.save(videoGallery)
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action: "index", method: "GET"
            }
        }
    }

    def create() {
        respond new VideoGallery(params)
    }

    def save(VideoGalleryCommand videoGalleryCommand){
        if(videoGalleryCommand == null){
            notFound()
            return
        }
        if(videoGalleryCommand?.hasErrors()){
            render view: 'create' , model: [videoGalleryCommand: videoGalleryCommand]
            return
        }

        VideoGallery videoGallery = new VideoGallery()
        bindData(videoGallery,videoGalleryCommand)
        try{
            videoGallery?.status = videoGalleryCommand?.active
            videoGallery?.createdBy = userService?.getLoggedInUser()?.username
            videoGallery?.updatedBy = userService?.getLoggedInUser()?.username

            videoGalleryService.save(videoGallery)

        }catch (ValidationException e) {
            respond videoGallery.errors, view:'create'
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = 'Video Gallery created successfully'
                redirect action: 'index'
            }
            '*' { respond videoGallery, [status: CREATED] }
        }
    }
    def edit(Long id){
        VideoGallery videoGallery = videoGalleryService.get(id)
        respond videoGallery
    }

    def update(VideoGalleryCommand videoGalleryCommand) {
        if (videoGalleryCommand == null) {
            notFound()
            return
        }
        VideoGallery videoGallery = VideoGallery.findById(params?.id)
        if (!videoGallery) {
            notFound()
            return
        }
        try {
            videoGallery?.status = videoGalleryCommand?.active
            bindData(videoGallery, videoGalleryCommand)
            videoGallery?.updatedBy = userService?.getLoggedInUser()?.username
            videoGalleryService.save(videoGallery)
        }catch (ValidationException ignored) {
            respond videoGallery.errors, view:'edit'
            return

        }
        request.withFormat {
            form multipartForm {
                flash.message = 'Video Gallery updated successfully'
                redirect action: 'index'
            }
            '*'{ respond videoGallery, [status: OK] }
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

}
