package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class BookAdvertisementService {
    def list(Map params) {
        BookAdvertisement.createCriteria().list(params) {
            if (params?.title) {
                ilike('title', "%${params?.title}%")
            }
            if (params?.emailAddress) {
                ilike('emailAddress', "%${params?.emailAddress}%")
            }
            if (params?.contactNumber) {
                ilike('contactNumber', "%${params?.contactNumber}%")
            }

            order(params?.sort ?: 'paymentDate', params?.order ?: 'desc')


        }
    }
}
