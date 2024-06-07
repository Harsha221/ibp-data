package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class VideoGallery extends BaseDomain{
     String videoGalleryId = UUID.randomUUID()
     String title
     String youtubeVideoUrl
     Boolean status = Boolean.FALSE
     Association association
     String tags
     String createdBy
     String updatedBy

     static mapping = {
          version false
          youtubeVideoUrl sqlType: 'TEXT'
          tags sqlType: 'TEXT'
     }
}
