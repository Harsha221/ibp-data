package com.ibp.admin.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Utility class which provides local date time utility methods
 */
class LocalDateTimeUtils {

    /**
     * Formats from LocalDate to String
     *
     * @param localDate LocalDate to be converted
     * @param format Format of date
     * @param locale Locale, Default is Locale.US
     * @return Formatted date string
     */
    static String formatFromLocalDate(LocalDate localDate, String format, Locale locale = Locale.US) {
        localDate.format(DateTimeFormatter.ofPattern(format, locale))
    }

    /**
     * Formats LocalDateTime to String
     *
     * @param localDateTime LocalDateTime to be converted
     * @param format Format of date time
     * @param locale Locale, Default is Locale.US
     * @return Formatted date time string
     */
    static String formatFromLocalDateTime(LocalDateTime localDateTime, String format, Locale locale = Locale.US) {
        localDateTime.format(DateTimeFormatter.ofPattern(format, locale))
    }

    /**
     * Returns LocalDateTime from LocalDate and LocalTime
     *
     * @param localDate LocalDate
     * @param localTime LocalTime, Default is 0 hour, 0 minute, 0 second
     * @return Returns LocalDateTime
     */
    static LocalDateTime getLocalDateTimeFromLocalDate(LocalDate localDate, LocalTime localTime = LocalTime.of(0, 0, 0)) {
        LocalDateTime.of(localDate, localTime)
    }

    /**
     * Returns the previous LocalDate specified by daysToSubtract
     *
     * @param daysToSubtract Number of days to subtract from current date
     * @return Returns LocalDate
     */
    static LocalDate getPreviousLocalDate(Long daysToSubtract) {
        LocalDate.now().minusDays(daysToSubtract)
    }

    /**
     * Returns LocalDate of specified date string and its format
     *
     * @param dateStr Date in string
     * @param format Format of date string
     * @param locale Locale, Default is Locale.US
     * @return Returns LocalDate
     */
    static LocalDate getLocalDateFromString(String dateStr, String format, Locale locale = Locale.US) {
        LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(format, locale))
    }
}
