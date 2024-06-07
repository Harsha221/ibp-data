package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class VideoGalleryService {
    def list(Map params) {
        VideoGallery.createCriteria().list(params) {
            if(params?.associationId) {
                Association association = Association.findById(params?.associationId)
                eq('association' , association)
            }
            if (params?.name) {
                ilike('title', "%${params?.name}%")
            }
            if (params?.searchVideoGalleryStatus) {
                if(params?.searchVideoGalleryStatus?.equals("Activated")) {
                    eq('status', true)
                } else {
                    eq('status', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
    @Transactional
    VideoGallery save(VideoGallery videoGallery){
        videoGallery.save(flush:true,failOnError:true)
    }
    @Transactional
    VideoGallery get(Long id) {
        VideoGallery.read(id)
    }
    VideoGallery delete(Long id){
        get(id).delete(flush: true, failOnError: true)
    }

}
