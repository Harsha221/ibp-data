package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class BulletinsService {
    def list(Map params) {
        Bulletins.createCriteria().list(params) {
            if (params?.name) {
                ilike('title', "%${params?.name}%")
            }
            if(params?.associationId) {
                Association association = Association.findById(params?.associationId)
                eq('association' , association)
            }
            if (params?.searchBulletinsStatus) {
                if(params?.searchBulletinsStatus?.equals("Activated")) {
                    eq('status', true)
                } else {
                    eq('status', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
    @Transactional
    Bulletins save(Bulletins bulletins){
        bulletins.save(flush:true,fallOnError:true)
    }
    @Transactional
    Bulletins get(Long id){
        Bulletins.read(id)
    }
    Bulletins delete(Long id){
        get(id).delete(flush: true,fallOnError: true)
    }
}
