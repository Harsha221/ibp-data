package com.ibp.admin.enums

enum VendorBusinessStatus {

    SUBMITTED(0, 'Submitted'),
    IN_REVIEW(1, 'In Review'),
    ON_HOLD(2, 'On Hold'),
    VERIFIED(3, 'Verified')

    Integer value
    String name

    VendorBusinessStatus(Integer value, String name) {
        this.value = value
        this.name = name
    }

    static VendorBusinessStatus parseVendorBusinessStatus(Integer value) {
        values().find {
            it.value == value
        }
    }

}
