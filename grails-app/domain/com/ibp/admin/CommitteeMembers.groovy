package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class CommitteeMembers extends BaseDomain{
     String committeeMembersId = UUID.randomUUID()
     String name
     String designation
     String companyName
     String address
     String year
     Association association
     String mobileNo
     String email
     String type
     Integer typeId
     Boolean status = Boolean.FALSE
     Integer level
     String thumbUrl
     String tags
     String createdBy
     String updatedBy

     static mapping = {
          version false
          address sqlType: 'TEXT'
          thumbUrl sqlType: 'TEXT'
          tags sqlType: 'TEXT'
     }
}
