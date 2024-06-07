package com.ibp.admin

import com.ibp.admin.commands.AdvertisementRateCardCommand
import grails.converters.JSON

import javax.xml.bind.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class AdvertisementRateCardController {

    AdvertisementRateCardService advertisementRateCardService
    AwsService awsService
    UserService userService
    AuditLogsService auditLogsService

    def index(Integer max){
        params.max = Math.min(max ?: 20, 100)
        def advertisementRateCardList = advertisementRateCardService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    advertisementRateCardList : advertisementRateCardList,
                    advertisementRateCardCount: advertisementRateCardList.totalCount
            ]
            return
        }
        [advertisementRateCardList : advertisementRateCardList, advertisementRateCardCount: advertisementRateCardList.totalCount, params: params]
    }

    def create(){
        respond new AdvertisementRateCard(params)
    }

    def save(AdvertisementRateCardCommand advertisementRateCardCommand){
        if(advertisementRateCardCommand == null){
            notfound()
            return
        }
        if(advertisementRateCardCommand?.hasErrors()){
            render view:'create' , model:[advertisementRateCardCommand:advertisementRateCardCommand]
            return
        }
        AdvertisementRateCard advertisementRateCard = new AdvertisementRateCard()
        bindData(advertisementRateCard,advertisementRateCardCommand)
        try{
            advertisementRateCard?.status = advertisementRateCardCommand?.active
            if(advertisementRateCardCommand?.imageFile?.size>0){
                String imageUrl = awsService.uploadImageToS3(advertisementRateCardCommand?.imageFile,Constants.S3Folders.ADVERTISEMENT_RATE_CARD,"imageUrl_"+advertisementRateCard?.advertisementRateCardId)
                advertisementRateCard?.imageUrl = imageUrl
            }
            advertisementRateCard?.createdBy = userService?.getLoggedInUser()?.username
            advertisementRateCard?.updatedBy = userService?.getLoggedInUser()?.username
            advertisementRateCardService.save(advertisementRateCard)
            auditLogsService.logAction('Save','AdvertisementRateCard',advertisementRateCard?.id,userService?.getLoggedInUser()?.username,(advertisementRateCard as JSON).toString(false),(advertisementRateCard as JSON).toString(false))
        }
        catch (ValidationException e){
            respond advertisementRateCard.errors,view:'create'
            return
        }
        request.withFormat {
            form multipartForm{
                flash.message = 'Advertisement Rate Card Created successfully'
                redirect action: 'index'
            }
            '*'{ respond advertisementRateCard, [status: CREATED] }
        }

    }
    def edit(Long id){
        AdvertisementRateCard advertisementRateCard = advertisementRateCardService.get(id)
        respond advertisementRateCard
    }

    def update(AdvertisementRateCardCommand advertisementRateCardCommand){
        if(advertisementRateCardCommand == null){
            notFound()
            return
        }

        AdvertisementRateCard advertisementRateCard = AdvertisementRateCard.findById(params?.id)
        if(!advertisementRateCard){
            notFound()
            return
        }
        def oldAdvertisementRateCard = advertisementRateCard.clone()
        try {
            advertisementRateCard?.status = advertisementRateCardCommand?.active
            String imageUrl = advertisementRateCard?.imageUrl
            bindData(advertisementRateCard,advertisementRateCardCommand)
            if(advertisementRateCard?.isMultiplePages == Boolean.FALSE){  //off
                advertisementRateCard?.amount = advertisementRateCardCommand?.amount
                advertisementRateCard?.amountPage1 = null
                advertisementRateCard?.amountPage2 = null
                advertisementRateCard?.amountPage3 = null
                advertisementRateCard?.amountPage4 = null
                advertisementRateCard?.amountPage5 = null
            }
            if(advertisementRateCard?.isMultiplePages == Boolean.TRUE){  //on
                advertisementRateCard?.amount = null
                advertisementRateCard?.amountPage1 = advertisementRateCardCommand?.amountPage1
                advertisementRateCard?.amountPage2 = advertisementRateCardCommand?.amountPage2
                advertisementRateCard?.amountPage3 = advertisementRateCardCommand?.amountPage3
                advertisementRateCard?.amountPage4 = advertisementRateCardCommand?.amountPage4
                advertisementRateCard?.amountPage5 = advertisementRateCardCommand?.amountPage5
            }

            if(advertisementRateCardCommand?.imageFile?.size>0){
                imageUrl = awsService.uploadImageToS3(advertisementRateCardCommand?.imageFile,'advertisementRateCard',"imageUrl_"+advertisementRateCard?.advertisementRateCardId)
                advertisementRateCard?.imageUrl = imageUrl
            }
            else if(advertisementRateCardCommand?.imageFile?.size ==0 && imageUrl){
                advertisementRateCard?.imageUrl = imageUrl
            }
            else{
                awsService.removeImageFromS3(imageUrl,'prod/advertisementRateCard',"imageUrl_"+advertisementRateCard?.advertisementRateCardId)
            }
            advertisementRateCard?.updatedBy = userService?.getLoggedInUser()?.username
            advertisementRateCardService.save(advertisementRateCard)
            auditLogsService.logAction('Update','AdvertisementRateCard',advertisementRateCard?.id,userService?.getLoggedInUser()?.username,(oldAdvertisementRateCard as JSON).toString(false),(oldAdvertisementRateCard as JSON).toString(false))
        }
        catch (ValidationException ignored){
            respond advertisementRateCard.errors,view: 'edit'
            return
        }
        request.withFormat {
            from multipartForm{
                flash.message = 'Advertisement Rate Card updated successfully '
                redirect action: 'index'
            }
            '*'{ respond advertisementRateCard, [status: OK] }
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
        AdvertisementRateCard advertisementRateCard = AdvertisementRateCard.findById(toggleId)
        def oldAdvertisementStatus = advertisementRateCard.clone()
        if(advertisementRateCard?.status){
            advertisementRateCard?.status = false
            message = 'Advertisement Rate Card has been Deactivated successfully'
        }
        else{
            advertisementRateCard?.status = true
            message = 'Advertisement Rate Card  has been Activated successfully'
        }
        advertisementRateCardService.save(advertisementRateCard)

        if(advertisementRateCard?.status == false){
            auditLogsService.logAction('DeActivated','AdvertisementRateCard',advertisementRateCard?.id,userService?.getLoggedInUser()?.username,(oldAdvertisementStatus as JSON).toString(false),(advertisementRateCard as JSON).toString(false))
        }
        else {
            auditLogsService.logAction('Activated','AdvertisementRateCard',advertisementRateCard?.id,userService?.getLoggedInUser()?.username,(oldAdvertisementStatus as JSON).toString(false),(advertisementRateCard as JSON).toString(false))
        }
        request.withFormat {
            form multipartForm{
                flash.message = message
                redirect action: "Index",method: "GET"
            }
        }
    }


}
