package com.ibp.admin.enums

enum VendorUploadStatus {

    PENDING(Byte.valueOf('0'), 'Pending'),
    PROCESSING(Byte.valueOf('1'), 'Processing'),
    PROCESSED(Byte.valueOf('2'), 'Processed'),
    FAILED(Byte.valueOf('3'), 'Failed')

    public Byte status
    public String label

    VendorUploadStatus(Byte status, String label) {
        this.status = status
        this.label = label
    }

    static VendorUploadStatus parseByte(Byte status) {
        values().find {
            it.status == status
        }
    }

}
