package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class BlogViews extends BaseDomain {

    String blogViewId
    Blog blog

    static constraints = {
    }

    static mapping = {
        version false

    }
}
