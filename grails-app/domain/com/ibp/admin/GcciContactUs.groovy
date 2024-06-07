package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class GcciContactUs extends BaseDomain{
     String gcciContactUsUuid = UUID.randomUUID()
     String name
     String email
     String mobileNo
     String message

     static mapping = {
          version false
          message sqlType: 'TEXT'
     }
}
