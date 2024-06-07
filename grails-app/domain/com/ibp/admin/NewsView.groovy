package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class NewsView extends BaseDomain {

    String newsViewId
    News news

    static constraints = {
    }

    static mapping = {
        version false

    }
}
