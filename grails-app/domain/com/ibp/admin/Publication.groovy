package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class Publication extends BaseDomain implements Cloneable{
    String publicationId = UUID.randomUUID()
    String title
    String thumbUrl
    String amazonLink
    String flipkartLink
    String updatedBy
    String createdBy
    Boolean status = Boolean.FALSE

    static mapping = {
        version false
        thumbUrl sqlType: 'TEXT'
        amazonLink sqlType: 'TEXT'
        flipkartLink sqlType: 'TEXT'
    }
    static  constraints = {
    }

}
