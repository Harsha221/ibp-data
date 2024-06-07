package com.ibp.admin.enums

enum ContactType {

    PRIMARY_LANDLINE(Byte.valueOf('0'), 'Primary Landline'),
    SECONDARY_LANDLINE(Byte.valueOf('1'), 'Secondary Landline'),
    TERTIARY_LANDLINE(Byte.valueOf('2'), 'Tertiary Landline')

    Byte level
    String label

    ContactType(Byte level, String label) {
        this.level = level
        this.label = label
    }

    static ContactType parseByte(Byte level) {
        values().find {
            it.level == level
        }
    }

}
