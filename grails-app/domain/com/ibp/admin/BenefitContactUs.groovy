package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class BenefitContactUs extends BaseDomain{
     String benefitContactUsUuid = UUID.randomUUID()
     String name
     String subject
     String email
     String mobileNo
     String message

     static mapping = {
          version false
          message sqlType: 'TEXT'
     }
}
