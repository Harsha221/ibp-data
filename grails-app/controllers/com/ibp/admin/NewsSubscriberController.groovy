package com.ibp.admin

class NewsSubscriberController {

    NewsSubscriberService newsSubscriberService

    def index(Integer max) {
        params.max = Math.min(max ?: 20, 100)
        def newsSubscriberList = newsSubscriberService.list(params)
        if (request.xhr) {
            render template: 'list', model: [
                    newsSubscriberList : newsSubscriberList,
                    newsSubscriberCount: newsSubscriberList.totalCount
            ]
            return
        }
        [newsSubscriberList : newsSubscriberList, newsSubscriberCount: newsSubscriberList.totalCount, params: params]
    }

}
