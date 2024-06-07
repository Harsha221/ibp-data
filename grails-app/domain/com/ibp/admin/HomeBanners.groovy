package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class HomeBanners extends BaseDomain{
     String homeBannersId = UUID.randomUUID()
     String title
     Association association
     String thumbUrl
     String imageUrl
     String tags
     Boolean active = Boolean.FALSE
     String createdBy
     String updatedBy

     static mapping = {
          version false
          thumbUrl sqlType: 'TEXT'
          imageUrl sqlType: 'TEXT'
          tags sqlType: 'TEXT'
     }
}
