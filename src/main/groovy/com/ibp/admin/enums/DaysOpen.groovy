package com.ibp.admin.enums

enum DaysOpen {

    MONDAY('monday', 'Monday'),
    TUESDAY('tuesday', 'Tuesday'),
    WEDNESDAY('wednesday', 'Wednesday'),
    THURSDAY('thursday', 'Thursday'),
    FRIDAY('friday', 'Friday'),
    SATURDAY('saturday', 'Saturday'),
    SUNDAY('sunday', 'Sunday')

    String value
    String name

    DaysOpen(String value, String name) {
        this.value = value
        this.name = name
    }

    static list() {
        [MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY]
    }

    static DaysOpen parseDaysOpen(String value) {
        values().find {
            it.value == value
        }
    }

}
