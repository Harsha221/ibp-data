package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class NewsService {

    def list(Map params) {
        log.info("params : "+params?.toString())
        News.createCriteria().list(params) {
            if(params?.associationId) {
                Association association = Association.findById(params?.associationId)
                eq('association' , association)
            }
            if (params?.name) {
                ilike('title', "%${params?.name}%")
            }
            if (params?.searchNewsStatus) {
                if(params?.searchNewsStatus?.equals("Activated")) {
                    eq('active', true)
                } else {
                    eq('active', false)
                }
            }
            if(params?.isTopNews){
                if(params?.isTopNews?.equals("Yes")){
                    eq('isTopNews',true)
                }else {
                    eq('isTopNews',false)
                }
            }

            if(params?.searchNewsCategory){
                NewsCategory newsCategory = NewsCategory.findById(params?.searchNewsCategory)
                eq('category', newsCategory?.name)
            }
            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
    @Transactional
    News save(News news) {
        news.save(flush: true, failOnError: true)
    }
    @Transactional
    News get(Long id) {
        News.read(id)
    }

    News delete(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }
}
