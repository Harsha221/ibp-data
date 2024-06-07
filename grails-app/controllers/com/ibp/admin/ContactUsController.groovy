package com.ibp.admin

class ContactUsController {
    ContactUsService contactUsService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def contactUsList = contactUsService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    contactUsList : contactUsList,
                    contactUsCount: contactUsList.totalCount
            ]
            return
        }
        [contactUsList : contactUsList, contactUsCount: contactUsList.totalCount, params: params]
    }
}
