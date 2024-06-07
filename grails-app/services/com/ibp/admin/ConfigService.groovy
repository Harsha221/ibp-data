package com.ibp.admin

import grails.gorm.transactions.Transactional

@Transactional
class ConfigService {

    def listConfig(Map params) {
        def list = Config.createCriteria().list(params) {
            if (params?.name) {
                ilike('name', "%${params?.name}%")
            }
            if (params?.searchConfigStatus) {
                if(params?.searchConfigStatus?.equals("Active")) {
                    eq('isActive', true)
                } else {
                    eq('isActive', false)
                }
            }

            order(params?.sort ?: 'dateCreated', params?.order ?: 'desc')
        }
        list
    }

    def saveConfigData(Config config) {
        config.save(flush: true)
    }

    Config findConfigByName(String name) {
        Config.findByName(name)
    }

    Config get(Long id) {
        Config.get(id)
    }
}
