package com.ibp.admin

import com.ibp.admin.commands.BlogCategoryCommand
import grails.converters.JSON

import javax.xml.bind.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class BlogCategoryController {

    def blogCategoryService
    def awsService
    UserService userService
    AuditLogsService auditLogsService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def blogCategoryList = blogCategoryService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    blogCategoryList : blogCategoryList,
                    blogCategoryCount: blogCategoryList.totalCount
            ]
            return
        }
        [blogCategoryList: blogCategoryList, blogCategoryCount: blogCategoryList?.totalCount, params: params]
    }

    def create(){
        respond new BlogCategory(params)
    }

    def save(BlogCategoryCommand blogCategoryCommand){
        if(blogCategoryCommand == null){
            notFound()
            return
        }
        if(blogCategoryCommand?.hasErrors()){
            render view:'create' , model:[blogCategoryCommand:blogCategoryCommand]
            return
        }
        BlogCategory blogCategory = new BlogCategory()
        bindData(blogCategory,blogCategoryCommand)
        try{
            blogCategory?.displayName = blogCategoryCommand?.name?.toLowerCase()
            blogCategory?.status = blogCategoryCommand?.active
            if(blogCategoryCommand?.imageFile?.size>0){
                String thumbUrl = awsService.uploadImageToS3(blogCategoryCommand?.imageFile,Constants.S3Folders.BLOGCATEGORY,"thumbUrl_"+blogCategory?.blogCategoryId)
                blogCategory?.thumbUrl = thumbUrl
            }
            blogCategory?.createdBy = userService?.getLoggedInUser()?.username
            blogCategory?.updatedBy = userService?.getLoggedInUser()?.username
            blogCategoryService.save(blogCategory)
            auditLogsService.logAction('Save','BlogCategory',blogCategory?.id,userService?.getLoggedInUser()?.username,(blogCategory as JSON).toString(false),(blogCategory as JSON).toString(false))

        } catch (ValidationException e){
            respond blogCategory.errors,view:'create'
            return
        }
        request.withFormat {
            form multipartForm{
                flash.message = 'Blog Category Created successfully'
                redirect action: 'index'
            }
            '*'{ respond blogCategory, [status: CREATED] }
        }
    }

    def edit(Long id){
        BlogCategory blogCategory = blogCategoryService.get(id)
        respond blogCategory
    }

    def update(BlogCategoryCommand blogCategoryCommand){
        if(blogCategoryCommand == null){
            notFound()
            return
        }
        BlogCategory blogCategory = BlogCategory.findById(params?.id)
        if(!blogCategory){
            notFound()
            return
        }
        def oldBlogCategory = blogCategory.clone()
        try{

            blogCategory?.status = blogCategoryCommand?.active
            blogCategory?.displayName = blogCategoryCommand?.name?.toLowerCase()
            String thumbUrl = blogCategory?.thumbUrl
            bindData(blogCategory,blogCategoryCommand)
            if(blogCategoryCommand?.imageFile?.size>0){
                thumbUrl = awsService.uploadImageToS3(blogCategoryCommand?.imageFile,'blogCategory',"thumbUrl_"+blogCategory?.blogCategoryId)
                blogCategory?.thumbUrl = thumbUrl
            }
            else  if(blogCategoryCommand?.imageFile?.size ==0 && thumbUrl){
                blogCategory?.thumbUrl = thumbUrl
            }
            else {
                awsService.removeImageFromS3(thumbUrl,'prod/blogCategory',"thumbUrl_"+blogCategory?.blogCategoryId)
            }
            blogCategory?.updatedBy = userService?.getLoggedInUser()?.username
            blogCategoryService.save(blogCategory)
            auditLogsService.logAction('Update','BlogCategory',blogCategory?.id,userService?.getLoggedInUser()?.username,(oldBlogCategory as JSON).toString(false),(blogCategory as JSON).toString(false))

        }
        catch (ValidationException ignored){
            respond blogCategory.errors,view:'edit'
            return
        }
        request.withFormat {
            from multipartForm{
                flash.message = 'Blog Category updated successfully '
                redirect action: 'index'
            }
            '*'{ respond blogCategory, [status: OK] }
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
        BlogCategory blogCategory = BlogCategory.findById(toggleId)
        def oldBlogCategoryStatus = blogCategory.clone()
        if(blogCategory?.status){
            blogCategory.status = false
            message = 'Blog Category has been Deactivated successfully'
        }
        else{
            blogCategory.status = true
            message = 'Blog Category has been Activated successfully'
        }
        blogCategoryService.save(blogCategory)
        if(blogCategory?.status == false){
            auditLogsService.logAction('DeActivated','BlogCategory',blogCategory?.id,userService?.getLoggedInUser()?.username,(oldBlogCategoryStatus as JSON).toString(false),(blogCategory as JSON).toString(false))
        }
        else {
            auditLogsService.logAction('Activated','BlogCategory',blogCategory?.id,userService?.getLoggedInUser()?.username,(oldBlogCategoryStatus as JSON).toString(false),(blogCategory as JSON).toString(false))
        }
        request.withFormat {
            form multipartForm{
                flash.message = message
                redirect action: "Index",method: "GET"
            }
        }
    }
}
