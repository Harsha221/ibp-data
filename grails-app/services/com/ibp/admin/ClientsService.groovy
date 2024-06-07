package com.ibp.admin

import grails.gorm.transactions.Transactional


@Transactional
class ClientsService {
    def list(Map params) {
        Clients.createCriteria().list(params) {
            if (params?.name) {
                ilike('name', "%${params?.name}%")
            }

            if (params?.searchClientsStatus) {
                if(params?.searchClientsStatus?.equals("Activated")) {
                    eq('status', true)
                } else {
                    eq('status', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
    @Transactional
    Clients save(Clients clients){
        clients.save(flush: true , failOnError: true)
    }
    @Transactional
    Clients get(Long id){
        Clients.read(id)
    }
    Clients delete(Long id){
        get(id).delete(flush:true,failOnError: true)
    }


}
