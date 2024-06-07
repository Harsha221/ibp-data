package com.ibp.admin.enums

enum MediaType {

    LOGO(1),
    IMAGE_VIDEO(2),
    CERTIFICATE(3),
    BUSINESS_LICENCE(4),
    PAN_CARD(5),
    AADHAAR_CARD(6)

    Integer value

    private MediaType(Integer type) {
        this.value = type
    }

    int getValue() {
        return value
    }

    static list () {
        [LOGO, IMAGE_VIDEO, CERTIFICATE, BUSINESS_LICENCE, PAN_CARD, AADHAAR_CARD]
    }

    static MediaType parseMediaType(Integer value) {
        values().find {
            it.value == value
        }
    }

}
