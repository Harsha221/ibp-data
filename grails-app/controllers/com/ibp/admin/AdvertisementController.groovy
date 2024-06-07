package com.ibp.admin

import com.ibp.admin.commands.AdvertisementCommand
import grails.converters.JSON
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AdvertisementController {

    def advertisementService
    def awsService
    UserService userService
    AuditLogsService auditLogsService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def advertisementList = advertisementService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    advertisementList : advertisementList,
                    advertisementCount: advertisementList.totalCount
            ]
            return
        }
        respond advertisementList, model: [advertisementCount: advertisementList.totalCount]
    }

    def toggleStatus(Long toggleId) {
        System.out.println("toggleid : " + toggleId)
        if (toggleId == null) {
            notFound()
            return
        }
        String message
        Advertisement advertisement = Advertisement.findById(toggleId)
        def oldAdvertisementStatus = advertisement.clone()
        if (advertisement?.status) {
            advertisement.status = false
            message = 'Advertisement has been Deactivated successfully'
        } else {
            advertisement.status = true
            message = 'Advertisement has been Activated successfully'
        }
        advertisementService.save(advertisement)
        if(advertisement?.status == false){
            auditLogsService.logAction('DeActivated','Advertisement',advertisement?.id,userService?.getLoggedInUser()?.username,(oldAdvertisementStatus as JSON).toString(false),(advertisement as JSON).toString(false))
        }
        else {
            auditLogsService.logAction('Activated','Advertisement',advertisement?.id,userService?.getLoggedInUser()?.username,(oldAdvertisementStatus as JSON).toString(false),(advertisement as JSON).toString(false))
        }
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action: "index", method: "GET"
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = 'default.not.found.message'
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def show(Long id) {
        respond advertisementService.get(id)
    }

    def create() {
        respond new Advertisement(params)
    }

    def save(AdvertisementCommand advertisementCommand) {
        if (advertisementCommand == null) {
            notFound()
            return
        }


        Advertisement advertisement = new Advertisement()
        bindData(advertisement, advertisementCommand)
        try {
            advertisement.adsPosition = advertisementCommand.adsPosition
            advertisement.adsType = advertisementCommand.mediaSelection
            advertisement.videoType = advertisementCommand.mySelections
            advertisement.title = advertisementCommand.name
            advertisement.tags = advertisementCommand.tags
            advertisement.status = advertisementCommand.active
            if (!advertisement?.advertisementId) {
                advertisement?.advertisementId = UUID.randomUUID().toString()
            }
            Association association = Association.findById(advertisementCommand?.associationId)
            advertisement?.association = association
            if (advertisementCommand?.mediaSelection && advertisementCommand?.mediaSelection?.equalsIgnoreCase("Image")) {
                if (advertisementCommand?.imageFile?.size > 0) {
                    String imageUrl = awsService.uploadImageToS3(advertisementCommand?.imageFile, Constants.S3Folders.ADVERTISEMENT,
                            advertisement?.advertisementId)
                    advertisement?.url = imageUrl
                }
            } else {
                if (advertisementCommand?.mySelections && advertisementCommand?.mySelections?.equalsIgnoreCase("Custom")) {
                    String videoUrl = awsService.uploadImageToS3(advertisementCommand?.videoFile, Constants.S3Folders.ADVERTISEMENT,
                            advertisement?.advertisementId)
                    advertisement?.videoUrl = videoUrl
                } else {

                    advertisement?.youtubeUrl = advertisementCommand?.youtubeUrl
                }

            }
            advertisement?.createdBy = userService?.getLoggedInUser()?.username
            advertisement?.updatedBy = userService?.getLoggedInUser()?.username
            advertisementService.save(advertisement)
            auditLogsService.logAction('Save','Advertisement',advertisement?.id,userService.getLoggedInUser()?.username,(advertisement as JSON).toString(false),(advertisement as JSON).toString(false))

        } catch (ValidationException e) {
            respond advertisement.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Advertisement created successfully'
                redirect action: 'index'
            }
            '*' { respond advertisement, [status: CREATED] }
        }
    }

    def edit(Long id) {
        Advertisement advertisement = advertisementService.get(id)
        respond advertisement
    }

    def update(AdvertisementCommand advertisementCommand) {

        if (advertisementCommand == null) {
            notFound()
            return
        }

        Advertisement advertisement = Advertisement.findById(params?.id)
        if (!advertisement) {
            notFound()
            return
        }

        def oldAdvertisement = advertisement.clone()
        try {

            advertisement.adsType = advertisementCommand.mediaSelection
            advertisement.videoType = advertisementCommand.mySelections
            advertisement.title = advertisementCommand.name
            advertisement.tags = advertisementCommand.tags
            advertisement.status = advertisementCommand.active
            String imageurl = advertisement?.url
            bindData(advertisement, advertisementCommand)

            if (!advertisement?.advertisementId) {
                advertisement?.advertisementId = UUID.randomUUID().toString()
            }
            Association association = Association.findById(advertisementCommand?.associationId)
            advertisement?.association = association
            if(advertisement?.association.name.equals('GCCI') && advertisementCommand?.mediaSelection?.equalsIgnoreCase("Image")) {
                advertisement.adsPosition = advertisementCommand.adsPosition
            }
            else{
                advertisement.adsPosition=""

            }
            if (advertisementCommand?.mediaSelection && advertisementCommand?.mediaSelection?.equalsIgnoreCase("Image")) {
                System.out.println("Inside image ")
                if (advertisementCommand?.imageFile?.size > 0) {
                    awsService.removeImageFromS3(advertisement.url, 'prod/advertisement', advertisement?.advertisementId)
                    String imageUrl = awsService.uploadImageToS3(advertisementCommand?.imageFile, Constants.S3Folders.ADVERTISEMENT,
                            advertisement?.advertisementId)
                    advertisement?.url = imageUrl
                } else if (advertisementCommand?.imageFile?.size == 0 && imageurl) {
                    advertisement?.url = imageurl
                } else {
                    System.out.println("Inside image file size in else "+advertisementCommand?.imageFile?.size)
                    awsService.removeImageFromS3(advertisement.url, 'prod/advertisement', advertisement?.advertisementId)
                    System.out.println("3")
                }
                advertisement?.videoUrl = ""
                advertisement?.youtubeUrl = ""
                advertisement.videoType= ""


            } else {
                System.out.println("Inside video ")
                if (advertisementCommand?.mySelections && advertisementCommand?.mySelections?.equalsIgnoreCase("Custom")) {
                    if (advertisementCommand?.videoFile?.size > 0) {
                        String videoUrl = awsService.uploadImageToS3(advertisementCommand?.videoFile, Constants.S3Folders.ADVERTISEMENT,
                                advertisement?.advertisementId)
                        advertisement?.videoUrl = videoUrl
                    } else if (advertisementCommand?.videoFile?.size == 0 && videoUrl) {
                        advertisement?.videoUrl = videoUrl
                    } else {
                        awsService.removeImageFromS3(advertisement.videoUrl, 'prod/advertisement', advertisement?.advertisementId)
                    }
                    advertisement?.youtubeUrl = ""

                } else {
                    advertisement?.youtubeUrl = advertisementCommand?.youtubeUrl
                    advertisement?.videoUrl = ""
                }
                advertisement?.url = ""
            }
            advertisement?.updatedBy = userService?.getLoggedInUser()?.username
            advertisementService.save(advertisement)
            auditLogsService.logAction('Update','Advertisement',advertisement?.id,userService.getLoggedInUser()?.username,(oldAdvertisement as JSON).toString(false),(advertisement as JSON).toString(false))

            request.withFormat {
                form multipartForm {
                    flash.message = 'Advertisement updated successfully'
                    redirect action: 'index'
                }
                '*' { respond category, [status: OK] }
            }

        } catch (ValidationException ignored) {
            respond advertisement.errors, view: 'edit'
            return
        }
    }
}

