package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class BlogCategoryService {
    def list(Map params) {
        BlogCategory.createCriteria().list(params) {
            if (params?.name) {
                ilike('name', "%${params?.name}%")
            }

            if (params?.searchBlogCategoryStatus) {
                if(params?.searchBlogCategoryStatus?.equals("Activated")) {
                    eq('status', true)
                } else {
                    eq('status', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
    @Transactional
    BlogCategory save(BlogCategory blogCategory){
        blogCategory.save(flush:true,fallOnError:true)
    }
    @Transactional
    BlogCategory get(Long id){
        BlogCategory.read(id)
    }
    BlogCategory delete(Long id){
        get(id).delete(flush:true,fallOnError: true)
    }
}
