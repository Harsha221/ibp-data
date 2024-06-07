package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class AdvertisementRateCardService {

    def list(Map params) {
        AdvertisementRateCard.createCriteria().list(params) {
            if (params?.title) {
                ilike('title', "%${params?.title}%")
            }

            if (params?.searchAdvertisementRateCardStatus) {
                if(params?.searchAdvertisementRateCardStatus?.equals("Activated")) {
                    eq('status', true)
                } else {
                    eq('status', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
    }
    @Transactional
    AdvertisementRateCard save(AdvertisementRateCard advertisementRateCard){
        advertisementRateCard.save(flush: true , failOnError: true)
    }
    @Transactional
    AdvertisementRateCard get(Long id){
        AdvertisementRateCard.read(id)
    }
    AdvertisementRateCard delete(Long id){
        get(id).delete(flush:true,failOnError: true)
    }



}
