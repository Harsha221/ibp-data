package com.ibp.admin

import com.ibp.admin.commands.BlogCommand
import com.ibp.admin.utils.IbpHubUtils
import grails.converters.JSON
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class BlogController {

    def blogService
    def awsService
    UserService userService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    AuditLogsService auditLogsService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def blogList = blogService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    blogList : blogList,
                    blogCount: blogList.totalCount
            ]
            return
        }
        respond blogList, model: [blogCount: blogList.totalCount]
    }

    def show(Long id) {
        respond blogService.get(id)
    }

    def create() {
        respond new Blog(params)
    }

    def save(BlogCommand blogCommand) {
        if (blogCommand == null) {
            notFound()
            return
        }
        if (blogCommand?.hasErrors()) {
            log.info("blog command"+ blogCommand)
            render view: 'create', model: [blogCommand: blogCommand]
            return
        }
        Blog blog = new Blog()
        bindData(blog,blogCommand)
        try {
            BlogCategory blogCategory = BlogCategory.findById(Long.valueOf(params?.blogCategory))
            blog?.blogCategory = blogCategory
            blog?.category = blogCategory?.name
            if (blogCommand?.imageFile?.size > 0) {
                String thumbUrl = awsService.uploadImageToS3(blogCommand?.imageFile , 'blog' , blog?.blogId)
                blog?.thumbUrl = thumbUrl
            }
            blog?.createdBy = userService?.getLoggedInUser()?.username
            blog?.updatedBy = userService?.getLoggedInUser()?.username

            String title = blog?.title
            String url = IbpHubUtils.convertToSlug(title)
            blog?.friendlyUrl = url.concat("-"+blog?.blogId)
            blogService.save(blog)
            auditLogsService.logAction('Save','Blog',blog?.id,userService?.getLoggedInUser()?.username,(blog as JSON).toString(false),(blog as JSON).toString())
        } catch (ValidationException e) {
            respond blog.errors, view:'create'
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = 'Blog created successfully'
                redirect action: 'index'
            }
            '*' { respond blog, [status: CREATED] }
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

    def edit(Long id) {
        Blog blog = blogService.get(id)
        respond blog
    }

    def update(BlogCommand blogCommand) {

        if (blogCommand == null) {
            notFound()
            return
        }
        Blog blog= Blog.findById(params?.id)
        if (!blog) {
            notFound()
            return
        }
        def oldBlog = blog.clone()
        try {
            String thumbUrl = blog?.thumbUrl
            bindData(blog, blogCommand)
            BlogCategory blogCategory = BlogCategory.findById(Long.valueOf(params?.blogCategory))
            blog?.blogCategory = blogCategory
            blog?.category = blogCategory?.name
            if (blogCommand?.imageFile?.size >0) {
                thumbUrl = awsService.uploadImageToS3(blogCommand?.imageFile, 'blog', blog?.blogId)
                blog?.thumbUrl = thumbUrl
            } else if (blogCommand?.imageFile?.size == 0 && thumbUrl) {
                blog?.thumbUrl = thumbUrl
            }
            else {
                awsService.removeImageFromS3(thumbUrl , 'prod/blog' , blog?.blogId)
            }
            blog?.updatedBy = userService?.getLoggedInUser()?.username
            String title = blog?.title
            String url = IbpHubUtils.convertToSlug(title)
            blog?.friendlyUrl = url.concat("-"+blog?.blogId)
            blogService.save(blog)
            auditLogsService.logAction('Update','Blog',blog?.id,userService?.getLoggedInUser()?.username,(oldBlog as JSON).toString(false),(blog as JSON).toString(false))
        } catch (ValidationException ignored) {
            respond blog.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'Blog updated successfully'
                redirect action: 'index'
            }
            '*'{ respond blog, [status: OK] }
        }
    }

    def toggleStatus(Long toggleId) {
        if (toggleId == null) {
            notFound()
            return
        }
        String message
        Blog blog = Blog.findById(toggleId)
        def oldBlogStatus = blog.clone()
        if(blog?.active) {
            blog.active = false
            message = 'Blog has been Deactivated successfully'
        }
        else {
            blog.active = true
            message = 'Blog has been Activated successfully'
        }
        blogService.save(blog)

        if(blog?.active == false){
            auditLogsService.logAction('DeActivated','Blog',blog?.id,userService?.getLoggedInUser()?.username,(oldBlogStatus as JSON).toString(false),(blog as JSON).toString(false))
        }
        else {
            auditLogsService.logAction('Activated','Blog',blog?.id,userService?.getLoggedInUser()?.username,(oldBlogStatus as JSON).toString(false),(blog as JSON).toString(false))
        }
        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }
}
