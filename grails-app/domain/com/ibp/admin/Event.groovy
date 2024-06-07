package com.ibp.admin

import com.ibp.admin.common.BaseDomain

import java.time.LocalDate

class Event extends BaseDomain{
    String eventId = UUID.randomUUID()
    String bannerUrl
    LocalDate eventStartDate
    LocalDate eventEndDate
    String time
    String associationWith
    String title
    String venue
    String description
    String thumbUrl
    Association association
    Boolean status = Boolean.FALSE
    String createdBy
    String updatedBy
    String tags

    static mapping = {
        version false
        venue sqlType: 'TEXT'
        description  sqlType: 'longText'
        thumbUrl sqlType: 'TEXT'
        bannerUrl sqlType: 'TEXT'

    }


}
