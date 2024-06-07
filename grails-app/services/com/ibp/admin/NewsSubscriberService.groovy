package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class NewsSubscriberService {

    def list(Map params) {
        NewsSubscriber.createCriteria().list(params) {
            if (params?.email) {
                ilike('email', "%${params?.email}%")
            }
            if (params?.searchNewsSubscriberStatus) {
                if(params?.searchNewsSubscriberStatus?.equals("Activated")) {
                    eq('status', true)
                } else {
                    eq('status', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
}
