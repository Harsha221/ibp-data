package com.ibp.admin


import grails.gorm.transactions.Transactional

@Transactional
class AssociationService {

    def list(Map params) {
        Association.createCriteria().list(params) {
            if (params?.name) {
                ilike('name', "%${params?.name}%")
            }
            if (params?.searchAssociationStatus) {
                if(params?.searchAssociationStatus?.equals("Activated")) {
                    eq('active', true)
                } else {
                    eq('active', false)
                }
            }
            order(params?.sort ?: 'dateCreated' as String, params?.order ?: 'desc' as String)
        }
    }

    Association save(Association association) {
        association.save(flush: true, failOnError: true)
    }

    Association get(Long id) {
        Association.read(id)
    }

    Association delete(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }
}
