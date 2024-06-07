package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class AuditLogs extends BaseDomain {
    String action
    String entityType
    Long entityId
    String user
    Date timestamp
    String oldValue
    String newValue

    static mapping = {

        oldValue sqlType: 'longTEXT'
        newValue sqlType: 'longTEXT'
        version false
    }
}

