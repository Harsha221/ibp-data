package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class AdvertisementRateCard extends BaseDomain implements Cloneable{
    String advertisementRateCardId = UUID.randomUUID()
    String title
    String size
    String description
    Boolean isMultiplePages = Boolean.FALSE
    Integer amount
    Integer amountPage1
    Integer amountPage2
    Integer amountPage3
    Integer amountPage4
    Integer amountPage5
    String imageUrl
    String updatedBy
    String createdBy
    Boolean status = Boolean.FALSE

    static mapping = {
        version false
        imageUrl sqlType: 'TEXT'
        description  sqlType: 'longText'
    }

    static constraints = {

    }

}
