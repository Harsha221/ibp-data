package com.oham.admin.common

import com.oham.admin.utils.LocalDateTimeUtils

import java.time.LocalDateTime

abstract class BaseDomain {

    public static final String DD_MM_YYYY_TIME = 'dd/MM/yyyy HH:mm:ss'

    LocalDateTime dateCreated
    LocalDateTime lastUpdated

    String getDateCreatedText() {
        LocalDateTimeUtils.formatFromLocalDateTime(this.dateCreated, DD_MM_YYYY_TIME)
    }

    String getLastUpdatedText() {
        LocalDateTimeUtils.formatFromLocalDateTime(this.lastUpdated, DD_MM_YYYY_TIME)
    }
}
