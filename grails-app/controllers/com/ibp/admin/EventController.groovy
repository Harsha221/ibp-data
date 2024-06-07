package com.ibp.admin

import com.ibp.admin.commands.EventCommand
import com.ibp.admin.enums.MediaType
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class EventController {
    def eventService
    UserService userService

    AwsService awsService



    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def eventList = eventService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    eventList : eventList,
                    eventCount: eventList.totalCount
            ]
            return
        }
        [eventList: eventList, eventCount: eventList?.totalCount, params: params]
    }

    def create() {
        respond new Event(params)
    }

    def save(EventCommand eventCommand) {
        if (eventCommand == null) {
            notFound()
            return
        }
        if (eventCommand?.hasErrors()) {
            render view: 'create', model: [eventCommand: eventCommand]
            return
        }

        Event event = new Event()
        bindData(event, eventCommand)
        try {
            event?.eventStartDate = eventCommand?.eventStartDate
            event?.eventEndDate = eventCommand?.eventEndDate
            event?.time = eventCommand?.time
            event?.status = eventCommand?.active
            if (eventCommand?.thumbImageFile?.size > 0) {
                String thumbUrl = awsService.uploadImageToS3(eventCommand?.thumbImageFile, Constants.S3Folders.EVENT, "thumbUrl_" + event?.eventId)
                event?.thumbUrl = thumbUrl
            }
            if (eventCommand?.bannerImageFiles?.size > 0) {
                String bannerUrl = awsService.uploadImageToS3(eventCommand?.bannerImageFiles, Constants.S3Folders.EVENT, "bannerUrl_" + event?.eventId)
                event?.bannerUrl = bannerUrl
            }
            if(eventCommand?.imageFiles?.size() > 0) {
                eventCommand?.imageFiles?.each {
                    if(it?.size > 0) {
                        eventService.saveEventPhotos(event,it ,event?.eventId,)
                    }
                }
            }
            event?.createdBy = userService?.getLoggedInUser()?.username
            event?.updatedBy = userService?.getLoggedInUser()?.username

            eventService.save(event)
        } catch (ValidationException e) {
            respond event.errors, view: 'create'
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = 'Event Created successfully'
                redirect action: 'index'
            }
            '*' { respond event, [status: CREATED] }
        }
    }

    def edit(Long id){


        Event event = eventService.get(id)
        def eventPhotosList = EventPhotos?.findAllByEvent(event)
//        EventCommand eventCommand1 = new EventCommand();
//        eventCommand1?.imageFiles = eventPhotosList
        [event: event, eventPhotosList:eventPhotosList]
    }

    def update(EventCommand eventCommand){
        if(eventCommand == null){
            notFound()
            return
        }
        Event event = Event.findById(params?.id)
        if(!event){
            notFound()
            return
        }
        try {
            def eventPhotosList = EventPhotos?.findAllByEvent(event)
            event?.eventStartDate = eventCommand?.eventStartDate
            event?.eventEndDate = eventCommand?.eventEndDate
            event?.time = eventCommand?.time
            event?.status = eventCommand?.active
            String thumbUrl = event?.thumbUrl
            String bannerUrl = event?.bannerUrl


            bindData(event,eventCommand)
            if(eventCommand?.thumbImageFile?.size>0){
                thumbUrl = awsService.uploadImageToS3(eventCommand?.thumbImageFile,'event',"thumbUrl_"+event?.eventId)
                event?.thumbUrl = thumbUrl
            }else if(eventCommand?.thumbImageFile?.size == 0 && thumbUrl){
                event?.thumbUrl = thumbUrl
            }
            else{
                awsService.removeImageFromS3(thumbUrl,'prod/event',"bannerUrl_"+event?.eventId)
            }
            if (eventCommand?.bannerImageFiles?.size >0) {
                bannerUrl = awsService.uploadImageToS3(eventCommand?.bannerImageFiles, 'event', "bannerUrl_"+event?.eventId)
                event?.bannerUrl = bannerUrl
            } else if (eventCommand?.bannerImageFiles?.size == 0 && bannerUrl) {
                event?.bannerUrl = bannerUrl
            }
            else {
                awsService.removeImageFromS3(bannerUrl , 'prod/event' ,"bannerUrl_"+ event?.eventId)
            }

            List<EventPhotos> configAds = []
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
            eventPhotosList?.each{
                if(oldAds?.indexOf(it?.id?.toString()) < 0) {
                    configAds.add(it)
                }
            }


            configAds?.each {
                awsService.removeImageFromS3(it.imageUrl, 'prod/event', event?.eventId+"_"+it?.eventPhotosId)
                //homepageConfig.removeFromHomePageAds(it)
                eventService.removeEventPhotos(it)
            }
            eventCommand?.imageFiles?.each {
                if(!it.empty){
                    eventService.saveEventPhotos(event,it ,event?.eventId,)
                }

            }


            event?.updatedBy = userService?.getLoggedInUser()?.username
            eventService.save(event)
        }catch (ValidationException ignored){
            respond event.errors, view: 'edit'
            return
        }
        request.withFormat {
            from multipartForm{
                flash.message = 'Event updated successfully'
                redirect action: 'index'
            }
            '*'{ respond event, [status: OK] }
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
        Event event = Event.findById(toggleId)
        if(event?.status){
            event.status = false
            message = 'Event has been Deactivated successfully'
        }
        else {
            event.status = true
            message = 'Event has been Activated successfully'
        }
        eventService.save(event)
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }
}






