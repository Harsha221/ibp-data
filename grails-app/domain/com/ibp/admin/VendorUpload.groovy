package com.ibp.admin

import com.ibp.admin.common.BaseDomain
import com.ibp.admin.enums.VendorUploadStatus
import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes = ['fileName', 'contentType', 'size'])
@ToString(includes = ['fileName', 'contentType', 'size'], includeNames = true, includePackage = false)
class VendorUpload extends BaseDomain {

    String vendorUploadId
    String uploadAction       //  Create / Update
    String fileName
    String contentType
    String filePath
    String description
    Long size
    Byte status = VendorUploadStatus.PENDING.status
    String error
    Long totalCount = 0
    Long successCount = 0
    Long failedCount = 0

    static belongsTo = [
            uploadedBy: User
    ]

    static constraints = {
        fileName nullable: false, blank: false
        contentType nullable: false, blank: false
        filePath nullable: false, blank: false
        size nullable: false, blank: false
        status nullable: false, blank: false
        uploadedBy nullable: false, blank: false
        description nullable: true, blank: true
        uploadAction nullable: false, blank: false
    }

    static mapping = {
        version false
        description sqlType: 'text'
        error sqlType: 'text'
        uploadedBy lazy: false
    }
}
