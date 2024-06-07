package com.ibp.admin.enums

enum VendorDataStatus {

    SUCCESS(Byte.valueOf('1'), 'Success'),
    FAILED(Byte.valueOf('0'), 'Failed'),

    public Byte status
    public String label

    VendorDataStatus(Byte status, String label) {
        this.status = status
        this.label = label
    }

    static VendorDataStatus parseByte(Byte status) {
        values().find {
            it.status == status
        }
    }

}
