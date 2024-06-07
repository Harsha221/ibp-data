package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class NewsCategoryService {
    def list(Map params) {
        NewsCategory.createCriteria().list(params) {
            if (params?.name) {
                ilike('name', "%${params?.name}%")
            }
            if(params?.associationId) {
                Association association = Association.findById(params?.associationId)
                eq('associationId' , association?.id)
            }
            if (params?.searchNewsCategoryStatus) {
                if(params?.searchNewsCategoryStatus?.equals("Activated")) {
                    eq('status', true)
                } else {
                    eq('status', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
    @Transactional
    NewsCategory save(NewsCategory newsCategory){
        newsCategory.save(flush:true,fallOnError:true)
    }
    @Transactional
    NewsCategory get(Long id){
        NewsCategory.read(id)
    }
    NewsCategory delete(Long id){
        get(id).delete(flush:true,fallOnError: true)
    }


}
