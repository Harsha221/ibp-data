package com.ibp.admin

class HomepageConfig {

    String hashTags

    static hasMany = [homePageAds: HomepageAds]

    static mapping = {
        version false
        hashTags sqlType: 'TEXT'
        homePageAds cascade: 'all-delete-orphan'
    }
    static constraints = {
    }
}
