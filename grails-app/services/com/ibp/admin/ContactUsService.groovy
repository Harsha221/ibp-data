package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class ContactUsService {

    def list(Map params) {
        ContactUs.createCriteria().list(params) {
            if (params?.email) {
                ilike('email', "%${params?.email}%")
            }
            if (params?.phoneNumber) {
                ilike('phoneNumber', "%${params?.phoneNumber}%")
            }
            if (params?.name) {
                ilike('name', "%${params?.name}%")
            }
            if (params?.subject) {
                ilike('subject', "%${params?.subject}%")
            }


            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
}
