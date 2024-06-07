package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Bulletins extends BaseDomain{
    String bulletinsId = UUID.randomUUID()
    String title
    Association association
    String thumbUrl
    String youtubeVideoUrl
    String updatedBy
    String createdBy
    String tags
    Boolean status = Boolean.FALSE

    static mapping = {
        version false
        thumbUrl sqlType: 'TEXT'
        tags sqlType: 'TEXT'
    }

}
