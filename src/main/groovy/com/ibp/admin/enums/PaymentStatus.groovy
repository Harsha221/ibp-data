package com.ibp.admin.enums

enum PaymentStatus {

    UN_PAID(0, 'Unpaid'),
    PAID(1, 'Paid')

    Integer value
    String name

    PaymentStatus(Integer value, String name) {
        this.value = value
        this.name = name
    }

    static PaymentStatus parsePaymentStatus(Integer value) {
        values().find {
            it.value == value
        }
    }

}
