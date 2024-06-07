package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class LogoService {

    def list(Map params) {
        Logo.createCriteria().list(params) {
            if (params?.name) {
                ilike('name', "%${params?.name}%")
            }

            if (params?.searchLogoStatus) {
                if(params?.searchLogoStatus?.equals("Activated")) {
                    eq('status', true)
                } else {
                    eq('status', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
    @Transactional
    Logo save(Logo logo){
        logo.save(flush: true , failOnError: true)
    }
    @Transactional
    Logo get(Long id){
        Logo.read(id)
    }
    Logo delete(Long id){
        get(id).delete(flush:true,failOnError: true)
    }

}
