package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class EventPhotos extends BaseDomain{
    String eventPhotosId = UUID.randomUUID()
    String imageUrl
    Event event
    Association association
    String createdBy
    String updatedBy

    static mapping = {
        version false
        imageUrl sqlType: 'TEXT'
    }


}
