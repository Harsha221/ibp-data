package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class PublicationPhotos extends BaseDomain implements Cloneable {
    String publicationPhotosId = UUID.randomUUID()
    String imageUrl
    Publication publication
    String createdBy
    String updatedBy

    static mapping = {
        version false
        imageUrl sqlType: 'TEXT'
    }
}
