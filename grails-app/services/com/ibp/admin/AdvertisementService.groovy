package com.ibp.admin

import com.ibp.admin.enums.VendorBusinessStatus
import grails.gorm.transactions.Transactional

@Transactional
class AdvertisementService {

    def list(Map params) {
        System.out.println("search called..."+params)
        Advertisement.createCriteria().list(params) {
            if(params?.associationId) {
                Association association = Association.findById(params?.associationId)
                eq('association' , association)
            }

            if (params?.name) {
                ilike('title', "%${params?.name}%")
            }
            if (params?.mediaSelection) {
                eq('adsType', params?.mediaSelection)
            }
            if (params?.searchVendorStatus) {
                if(params?.searchVendorStatus?.equals("Activated")) {
                    eq('status', true)
                } else {
                    eq('status', false)
                }
            }
            order(params?.sort ?: 'dateCreated' , params?.order ?: 'desc')
        }
    }

    Advertisement save(Advertisement advertisement) {
        advertisement.save(flush: true, failOnError: true)
    }

    Advertisement get(Long id) {
        Advertisement.read(id)
    }

    Advertisement delete(Long id) {
        get(id).delete(flush: true, failOnError: true)
    }


}
