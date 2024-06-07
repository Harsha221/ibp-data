package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class HomeBannersService {

    def list(Map params) {
        HomeBanners.createCriteria().list(params) {
            if(params?.associationId) {
                Association association = Association.findById(params?.associationId)
                eq('association' , association)
            }
            if (params?.name) {
                ilike('title', "%${params?.name}%")
            }
            if (params?.searchHomeBannersStatus) {
                if(params?.searchHomeBannersStatus?.equals("Activated")) {
                    eq('active', true)
                } else {
                    eq('active', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
    @Transactional
    HomeBanners save(HomeBanners homeBanners){
        homeBanners.save(flush:true,failOnError:true)
    }
    @Transactional
    HomeBanners get(Long id) {
        HomeBanners.read(id)
    }
    HomeBanners delete(Long id){
        get(id).delete(flush: true, failOnError: true)
    }

}
