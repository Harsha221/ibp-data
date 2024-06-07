package com.ibp.admin.common

import com.ibp.admin.Constants
import com.ibp.admin.utils.LocalDateTimeUtils

import java.time.LocalDateTime

abstract class BaseDomain {

    LocalDateTime dateCreated
    LocalDateTime lastUpdated

    String getDateCreatedText() {
        LocalDateTimeUtils.formatFromLocalDateTime(this.dateCreated, Constants.DateFormat.DD_MM_YYYY_TIME)
    }

    String getLastUpdatedText() {
        LocalDateTimeUtils.formatFromLocalDateTime(this.lastUpdated, Constants.DateFormat.DD_MM_YYYY_TIME)
    }
}
