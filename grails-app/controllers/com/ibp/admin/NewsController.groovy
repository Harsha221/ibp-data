package com.ibp.admin

import com.ibp.admin.commands.NewsCommand
import com.ibp.admin.utils.IbpHubUtils
import grails.converters.JSON
import grails.validation.ValidationException
import software.amazon.awssdk.services.s3.endpoints.internal.Value

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK

class NewsController {

    def newsService
    def awsService
    UserService userService
    AuditLogsService auditLogsService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def newsList = newsService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    newsList : newsList,
                    newsCount: newsList.totalCount
            ]
            return
        }
        [newsList: newsList, newsCount: newsList?.totalCount, params: params]
    }

    def create() {
        respond new News(params)
    }

    def save(NewsCommand newsCommand) {
        if (newsCommand == null) {
            notFound()
            return
        }
        if (newsCommand?.hasErrors()) {
            render view: 'create', model: [newsCommand: newsCommand]
            return
        }

        News news = new News()
        bindData(news,newsCommand)
        try {
            NewsCategory newsCategory = NewsCategory.findById(Long.valueOf(params?.newsCategory))
            news?.newsCategory = newsCategory
            news?.category = newsCategory?.name
            news.isTopNews = newsCommand.isTopNews

            if (newsCommand?.imageFile?.size > 0) {
                String thumbUrl = awsService.uploadImageToS3(newsCommand?.imageFile ,Constants.S3Folders.NEWS,"thumbUrl_"+news?.newsId)
                news?.thumbUrl = thumbUrl
            }
            if (newsCommand?.imageFiles?.size > 0){
                String imageUrl = awsService.uploadImageToS3(newsCommand?.imageFiles,Constants.S3Folders.NEWS,"imageUrl_"+news?.newsId)
                news?.imageUrl = imageUrl
            }

            news?.createdBy = userService?.getLoggedInUser()?.username
            news?.updatedBy = userService?.getLoggedInUser()?.username

            String title = news?.title
            String url = IbpHubUtils.convertToSlug(title)
            news?.friendlyUrl = url.concat("-"+news?.newsId)

            newsService.save(news)
            auditLogsService.logAction('Save','News',news?.id,userService?.getLoggedInUser()?.username,(news as JSON).toString(false),(news as JSON).toString(false))


        } catch (ValidationException e) {
            respond news.errors, view:'create'
            return
        }
        request.withFormat {
            form multipartForm {
                flash.message = 'News created successfully'
                redirect action: 'index'
            }
            '*' { respond news, [status: CREATED] }
        }
    }
    def getCategoriesByAssociations(){
        def association = Association.get(params?.id)
        def categoriesAssociations = NewsCategory.findAllByAssociationId(association?.id)
        render categoriesAssociations as JSON
    }
//    def getOldCategoriesByAssociations(){
//        def categoriesAssociations = NewsCategory.findAllByAssociationIdIsNull(params?.id)
//        render categoriesAssociations as JSON
//    }


    def edit(Long id) {
        News news = newsService.get(id)
        def association = Association.get(params?.id)
        def categoriesAssociations = NewsCategory.findAllByAssociationId(params?.id)
        [news:news,association:association,categoriesAssociations:categoriesAssociations]
    }

    def update(NewsCommand newsCommand) {
        if (newsCommand == null) {
            notFound()
            return
        }
        News news= News.findById(params?.id)
        if (!news) {
            notFound()
            return
        }
        def oldNews = news.clone()
        try {
            news.isTopNews = newsCommand.isTopNews
            String thumbUrl = news?.thumbUrl
            String imageUrl = news?.imageUrl
            bindData(news, newsCommand)
            NewsCategory newsCategory = NewsCategory.findById(Long.valueOf(params?.newsCategory))
            news?.newsCategory = newsCategory
            news?.category = newsCategory?.name
            if (newsCommand?.imageFile?.size > 0) {
                thumbUrl = awsService.uploadImageToS3(newsCommand?.imageFile, 'news',"thumbUrl_"+ news?.newsId)
                news?.thumbUrl = thumbUrl
            } else if (newsCommand?.imageFile?.size == 0 && thumbUrl) {
                news?.thumbUrl = thumbUrl
            }
            else {
                awsService.removeImageFromS3(thumbUrl , 'prod/news' ,"thumbUrl_"+ news?.newsId)
            }
            if (newsCommand?.imageFiles?.size >0) {
                imageUrl = awsService.uploadImageToS3(newsCommand?.imageFiles, 'news', "imageUrl_"+news?.newsId)
                news?.imageUrl = imageUrl
            } else if (newsCommand?.imageFiles?.size == 0 && imageUrl) {
                news?.imageUrl = imageUrl
            }
            else {
                awsService.removeImageFromS3(imageUrl , 'prod/news' ,"imageUrl_"+ news?.newsId)
            }

            news?.updatedBy = userService?.getLoggedInUser()?.username

            String title = news?.title
            String url = IbpHubUtils.convertToSlug(title)
            news?.friendlyUrl = url.concat("-"+news?.newsId)

            newsService.save(news)
            auditLogsService.logAction('Update','News',news?.id,userService.getLoggedInUser()?.username,(oldNews as JSON).toString(false),(news as JSON).toString(false))
        } catch (ValidationException ignored) {
            respond news.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = 'News updated successfully'
                redirect action: 'index'
            }
            '*'{ respond news, [status: OK] }
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
        News news = News.findById(toggleId)
        def oldNewsStatus = news.clone()
        if(news?.active) {
            news.active = false
            message = 'News has been Deactivated successfully'
        }
        else {
            news.active = true
            message = 'News has been Activated successfully'
        }
        newsService.save(news)

        if(news?.active == false){
            auditLogsService.logAction('DeActivated','News',news?.id,userService?.getLoggedInUser()?.username,(oldNewsStatus as JSON).toString(false),(news as JSON).toString(false))
        }
        else {
            auditLogsService.logAction('Activated','News',news?.id,userService?.getLoggedInUser()?.username,(oldNewsStatus as JSON).toString(false),(news as JSON).toString(false))
        }

        request.withFormat {
            form multipartForm {
                flash.message = message
                redirect action:"index", method:"GET"
            }
        }
    }
}
