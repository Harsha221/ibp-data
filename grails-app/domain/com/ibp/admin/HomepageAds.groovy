package com.ibp.admin

class HomepageAds {

    String homepageAdsId
    String imageUrl
    HomepageConfig homepageConfig

    static mapping = {
        version false
        homepageConfig cascade: 'all-delete-orphan'
    }
    static constraints = {
        imageUrl nullable: true, blank: true
    }
}
