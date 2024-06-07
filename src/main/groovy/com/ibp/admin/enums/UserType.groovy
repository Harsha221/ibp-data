package com.ibp.admin.enums

enum UserType {

    CUSTOMER(0, 'Customer'),
    VENDOR(1, 'Vendor')

    Integer value
    String name

    UserType(Integer value, String name) {
        this.value = value
        this.name = name
    }

    static UserType parseUserType(Integer value) {
        values().find {
            it.value == value
        }
    }

}
