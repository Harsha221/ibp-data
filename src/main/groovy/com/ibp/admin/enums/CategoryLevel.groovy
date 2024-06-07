package com.ibp.admin.enums

enum CategoryLevel {

    MASTER(Byte.valueOf('0'), 'Master'),
    CATEGORY(Byte.valueOf('1'), 'Category'),
    SUB_CATEGORY(Byte.valueOf('2'), 'Sub Category')

    Byte level
    String label

    CategoryLevel(Byte level, String label) {
        this.level = level
        this.label = label
    }

    static CategoryLevel parseByte(Byte level) {
        values().find {
            it.level == level
        }
    }

}
