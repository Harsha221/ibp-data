package com.ibp.admin.utils

import com.ibp.admin.Constants
import io.micronaut.http.uri.UriBuilder
import java.text.SimpleDateFormat

class IbpHubUtils {

    static URI buildURI(String baseUrl, String path = null,
                        Map<String, String[]> queryParams = [:],
                        String fragment = null) {
        UriBuilder builder = UriBuilder.of(baseUrl).path(path).fragment(fragment)
        queryParams?.each { param -> builder.queryParam(param.key, param.value) }
        builder.build()
    }

    static String convert_DD_MM_YYYY_to_DD_MMM_YYYY(String date) {
        SimpleDateFormat timestamp = new SimpleDateFormat(Constants.DateFormat.DD_MM_YYYY_TIME, Locale.US)
        getFormattedDate(timestamp.parse(date), Constants.DateFormat.DD_MMM_YYYY_TIME)
    }

    static String getFormattedDate(Date date, String format = null) {
        SimpleDateFormat timestamp = new SimpleDateFormat(format ?: Constants.DateFormat.DD_MMM_YYYY_TIME, Locale.US)
        timestamp.format(date ?: new Date())
    }
    static String convertToSlug(String title) {
        // Replace spaces with hyphens
        String slug = title.trim().replaceAll("\\s+", "-");
        // Replace '&' with 'and'
        slug = slug.replaceAll("&", "and");
        // Remove special characters
        slug = slug.replaceAll("[^a-zA-Z0-9-]", "-");
        // Convert to lowercase
        slug = slug.toLowerCase();
        return slug;
    }

}
