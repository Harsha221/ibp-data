package com.ibp.admin

import com.ibp.admin.common.BaseDomain
import software.amazon.awssdk.services.sts.endpoints.internal.Value

class Clients extends BaseDomain implements Cloneable{
    String clientsId = UUID.randomUUID()
    String name
    String logoUrl
    String websiteLink
    String updatedBy
    String createdBy
    Boolean status = Boolean.FALSE

    static mapping = {
        version false
        logoUrl sqlType: 'TEXT'
        websiteLink sqlType: 'TEXT'
    }

    static  constraints = {
    }
}
