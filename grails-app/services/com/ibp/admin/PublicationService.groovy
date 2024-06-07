package com.ibp.admin

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional
class PublicationService {
    AwsService awsService
    UserService userService
    AuditLogsService auditLogsService
    def list(Map params) {
        Publication.createCriteria().list(params) {
            if (params?.title) {
                ilike('title', "%${params?.title}%")
            }

            if (params?.searchPublicationStatus) {
                if(params?.searchPublicationStatus?.equals("Activated")) {
                    eq('status', true)
                } else {
                    eq('status', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
    PublicationPhotos getMedia(Long id) {
        PublicationPhotos.read(id)
    }
    @Transactional
    Publication save(Publication publication){
        publication.save(flush: true , failOnError: true)
    }
    @Transactional
    Publication get(Long id){
        Publication.read(id)
    }
    Publication delete(Long id){
        get(id).delete(flush:true,failOnError: true)
    }

    @Transactional
    def savePublicationPhotos(Publication publication, MultipartFile multipartFile, String publicationId){
        PublicationPhotos publicationPhotos = new PublicationPhotos()
        publicationPhotos.publicationPhotosId = UUID.randomUUID()
        publicationPhotos.publication = publication
        String imagePath = awsService.uploadImageToS3(multipartFile,Constants.S3Folders.PUBLICATION,publicationId+"_"+publicationPhotos.publicationPhotosId)
        publicationPhotos.imageUrl = imagePath
        publicationPhotos?.createdBy = userService?.getLoggedInUser()?.username
        publicationPhotos?.updatedBy = userService?.getLoggedInUser()?.username

        publicationPhotos.save(flush: true, failOnError: true)
        auditLogsService.logAction('Save','PublicationPhotos',publicationPhotos?.id,userService?.getLoggedInUser()?.username,(publicationPhotos as JSON).toString(false),(publicationPhotos as JSON).toString(false))



    }
    @Transactional
    def removePublicationPhotos(PublicationPhotos publicationPhotos){
        deletePublicationPhotos(publicationPhotos?.id)
    }
    PublicationPhotos deletePublicationPhotos(Long id) {
        getMedia(id).delete(flush: true, failOnError: true)
    }


}
