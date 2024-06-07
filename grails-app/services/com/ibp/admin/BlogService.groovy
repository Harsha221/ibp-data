package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class BlogService {

    def list(Map params) {
        Blog.createCriteria().list(params) {
            if (params?.name) {
                ilike('title', "%${params?.name}%")
            }
            if (params?.searchBlogStatus) {
                if(params?.searchBlogStatus?.equals("Activated")) {
                    eq('active', true)
                } else {
                    eq('active', false)
                }
            }
            if(params?.searchBlogCategory){
                BlogCategory blogCategory = BlogCategory.findById(params?.searchBlogCategory)
                eq('category', blogCategory?.name)
            }
            order(params?.sort ?: 'dateCreated' as String, params?.order ?: 'desc' as String)
        }
    }


    Blog save(Blog blog) {
        blog.save(flush: true, failOnError: true)
    }

    Blog get(Long id) {
        Blog.read(id)
    }

    Blog delete(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }

}
