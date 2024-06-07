package com.ibp.admin

import grails.gorm.transactions.Transactional
import org.springframework.web.multipart.MultipartFile


@Transactional
class EventService {

    AwsService awsService
    UserService userService

    def list(Map params) {
        Event.createCriteria().list(params) {
            if (params?.name) {
                ilike('title', "%${params?.name}%")
            }
            if(params?.associationId) {
                Association association = Association.findById(params?.associationId)
                eq('association' , association)
            }
            if (params?.eventStatus) {
                if(params?.eventStatus?.equals("Activated")) {
                    eq('status', true)
                } else {
                    eq('status', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
    EventPhotos getMedia(Long id) {
        EventPhotos.read(id)
    }
    @Transactional
    Event save(Event event){
        event.save(flush:true,failOnError:true)
    }
    @Transactional
    Event get(Long id) {
        Event.read(id)
    }
    Event delete(Long id){
        get(id).delete(flush: true, failOnError: true)
    }

    @Transactional
    def saveEventPhotos(Event event,MultipartFile multipartFile,String eventId){
        EventPhotos eventPhotos = new EventPhotos()
        eventPhotos.eventPhotosId = UUID.randomUUID()
        eventPhotos.event = event
        String imagePath = awsService.uploadImageToS3(multipartFile,Constants.S3Folders.EVENT,eventId+"_"+eventPhotos.eventPhotosId)
        eventPhotos.imageUrl = imagePath
        eventPhotos?.createdBy = userService?.getLoggedInUser()?.username
        eventPhotos?.updatedBy = userService?.getLoggedInUser()?.username

        eventPhotos.save(flush: true, failOnError: true)


    }
    @Transactional
    def removeEventPhotos(EventPhotos eventPhotos){
        deleteEventPhotos(eventPhotos?.id)
    }
    EventPhotos deleteEventPhotos(Long id) {
        getMedia(id).delete(flush: true, failOnError: true)
    }
}
