package com.ibp.admin

import com.ibp.admin.commands.NewsCategoryCommand
import grails.converters.JSON

import javax.xml.bind.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class NewsCategoryController {
    def newsCategoryService
    def awsService
    UserService userService
    AuditLogsService auditLogsService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def newsCategoryList = newsCategoryService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    newsCategoryList : newsCategoryList,
                    newsCategoryCount: newsCategoryList.totalCount
            ]
            return
        }
        [newsCategoryList : newsCategoryList, newsCategoryCount: newsCategoryList?.totalCount, params: params]
    }

    def create(){
        respond new NewsCategory(params)
    }

    def save(NewsCategoryCommand newsCategoryCommand){
        if(newsCategoryCommand == null){
            notFound()
            return
        }
        if(newsCategoryCommand?.hasErrors()){
            render view:'create' , model:[newsCategoryCommand:newsCategoryCommand]
            return
        }
        NewsCategory newsCategory = new NewsCategory()
        bindData(newsCategory,newsCategoryCommand)
        try{
            newsCategory?.associationId = newsCategoryCommand?.association?.id
            newsCategory?.displayName = newsCategoryCommand?.name?.toLowerCase()
            newsCategory?.status = newsCategoryCommand?.active
            if(newsCategoryCommand?.imageFile?.size>0){
                String thumbUrl = awsService.uploadImageToS3(newsCategoryCommand?.imageFile,Constants.S3Folders.NEWSCATEGORY,"thumbUrl_"+newsCategory?.newsCategoryId)
                newsCategory?.thumbUrl = thumbUrl
            }
            newsCategory?.createdBy = userService?.getLoggedInUser()?.username
            newsCategory?.updatedBy = userService?.getLoggedInUser()?.username
            newsCategoryService.save(newsCategory)
            auditLogsService.logAction('Save','NewsCategory',newsCategory?.id,userService?.getLoggedInUser()?.username,(newsCategory as JSON).toString(false),(newsCategory as JSON).toString(false))
        } catch (ValidationException e){
            respond newsCategory.errors,view:'create'
            return
        }
        request.withFormat {
            form multipartForm{
                flash.message = 'News Category Created successfully'
                redirect action: 'index'
            }
            '*'{ respond newsCategory, [status: CREATED] }
        }
    }

    def edit(Long id){
        NewsCategory newsCategory = newsCategoryService.get(id)
        respond newsCategory
    }

    def update(NewsCategoryCommand newsCategoryCommand){
        if(newsCategoryCommand == null){
            notFound()
            return
        }
        NewsCategory newsCategory = NewsCategory.findById(params?.id)
        if(!newsCategory){
            notFound()
            return
        }
        def oldNewsCategory = newsCategory.clone()
        try{
            newsCategory?.associationId = newsCategoryCommand?.association?.id
            newsCategory?.status = newsCategoryCommand?.active
            newsCategory?.displayName = newsCategoryCommand?.name?.toLowerCase()
            String thumbUrl = newsCategory?.thumbUrl
            bindData(newsCategory,newsCategoryCommand)
            if(newsCategoryCommand?.imageFile?.size>0){
                thumbUrl = awsService.uploadImageToS3(newsCategoryCommand?.imageFile,'newsCategory',"thumbUrl_"+newsCategory?.newsCategoryId)
                newsCategory?.thumbUrl = thumbUrl
            }
            else  if(newsCategoryCommand?.imageFile?.size ==0 && thumbUrl){
                newsCategory?.thumbUrl = thumbUrl
            }
            else {
                awsService.removeImageFromS3(thumbUrl,'prod/newsCategory',"thumbUrl_"+newsCategory?.newsCategoryId)
            }
            newsCategory?.updatedBy = userService?.getLoggedInUser()?.username
            newsCategoryService.save(newsCategory)
            auditLogsService.logAction('update','NewsCategory',newsCategory?.id,userService?.getLoggedInUser()?.username,(oldNewsCategory as JSON).toString(false),(newsCategory as JSON).toString(false))

        }
        catch (ValidationException ignored){
            respond newsCategory.errors,view:'edit'
            return
        }
        request.withFormat {
            from multipartForm{
                flash.message = 'News Category updated successfully '
                redirect action: 'index'
            }
            '*'{ respond newsCategory, [status: OK] }
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
        NewsCategory newsCategory = NewsCategory.findById(toggleId)
        def oldNewsCatForStatus = newsCategory.clone()
        if(newsCategory?.status){
            newsCategory.status = false
            message = 'News Category has been Deactivated successfully'
        }
        else{
            newsCategory.status = true
            message = 'News Category has been Activated successfully'
        }
        newsCategoryService.save(newsCategory)
        if(newsCategory?.status == false){
            auditLogsService.logAction('DeActivated','NewsCategory',newsCategory?.id,userService?.getLoggedInUser()?.username,(oldNewsCatForStatus as JSON).toString(false),(newsCategory as JSON).toString(false))
        }
        else {
            auditLogsService.logAction('Activated', 'NewsCategory', newsCategory?.id, userService?.getLoggedInUser()?.username, (oldNewsCatForStatus as JSON).toString(false), (newsCategory as JSON).toString(false))
        }
        request.withFormat {
            form multipartForm{
                flash.message = message
                redirect action: "Index",method: "GET"
            }
        }
    }
}
