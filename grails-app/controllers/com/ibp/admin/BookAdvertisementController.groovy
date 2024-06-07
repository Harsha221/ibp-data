package com.ibp.admin

class BookAdvertisementController {
    BookAdvertisementService bookAdvertisementService

    def index(Integer max){
        params.max = Math.min(max ?: 20, 100)
        def bookAdvertisementList = bookAdvertisementService.list(params)
        if(request.xhr){
            render template: 'list', model:  [
                    bookAdvertisementList  : bookAdvertisementList,
                    bookAdvertisementCount : bookAdvertisementList.totalCount
            ]
            return
        }
        [bookAdvertisementList: bookAdvertisementList, bookAdvertisementCount: bookAdvertisementList?.totalCount,params:params]
    }

}
