package com.ibp.admin.enums

enum UserStatus {

    DE_ACTIVE(0, 'De-Active'),
    ACTIVE(1, 'Active'),
    DELETED(2, 'Deleted'),
    ORNAMENT(3, 'Ornament'),
    SUBMITTED(4, 'Submitted'),
    REVIEW(5, 'Review')

    Integer value
    String name

    UserStatus(Integer value, String name) {
        this.value = value
        this.name = name
    }

    static UserStatus parseUserStatus(Integer value) {
        values().find {
            it.value == value
        }
    }

}
