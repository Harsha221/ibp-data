package com.ibp.admin.utils

import com.ibp.admin.VendorUploadData
import com.ibp.admin.enums.VendorDataStatus
import org.apache.commons.lang.StringUtils

class VendorUploadDataUtils {

    static List<Map<String, String>> FIELD_COLUMN = [
            [header: 'Sr No', field: 'srNo'],
            [header: 'Status', field: 'status'],
            [header: 'Business Name', field: 'businessName'],
            [header: 'Address', field: 'address'],
            [header: 'Area', field: 'area'],
            [header: 'City', field: 'city'],
            [header: 'District', field: 'district'],
            [header: 'State', field: 'state'],
            [header: 'PinCode', field: 'pinCode'],
            [header: 'Primary Land Number', field: 'primaryLandNumber'],
            [header: 'Secondary Land Number', field: 'secondaryLandNumber'],
            [header: 'Tertiary Land Number', field: 'tertiaryLandNumber'],
            [header: 'Username', field: 'username'],
            [header: 'Secondary Username', field: 'secondaryUsername'],
            [header: 'Primary Phone Number', field: 'primaryPhoneNumber'],
            [header: 'Secondary Phone Number', field: 'secondaryPhoneNumber'],
            [header: 'Third Phone Number', field: 'thirdPhoneNumber'],
            [header: 'Business Email', field: 'businessEmail'],
            [header: 'Secondary Email', field: 'secondaryEmail'],
            [header: 'Website Address', field: 'websiteAddress'],
            [header: 'Business Type', field: 'businessType'],
            [header: 'Category', field: 'category'],
            [header: 'Sub Category', field: 'subCategory'],
            [header: 'Product And Services', field: 'product'],
            [header: 'About Company', field: 'aboutCompany'],
            [header: 'About Customers', field: 'aboutCustomers'],
            [header: 'About Suppliers', field: 'aboutSuppliers'],
            [header: 'Year Of Establishment', field: 'yearOfEstablishment'],
            [header: 'GST', field: 'gst'],
            [header: 'Days Open', field: 'daysOpen'],
            [header: 'Latitude', field: 'latitude'],
            [header: 'Longitude', field: 'longitude'],
            [header: 'Meta Title', field: 'metaTitle'],
            [header: 'Meta Description', field: 'metaDescription', wrap: true, trim: true],
            [header: 'Meta Keywords', field: 'metaKeywords', wrap: true, trim: true],
            [header: 'Error', field: 'error'],
    ]

    static String renderFieldValue(Map column, VendorUploadData data) {
        if (StringUtils.isEmpty(data?."${column?.field}" as String) || (data?."${column?.field}" as String)?.equalsIgnoreCase("-")) {
            return ' - '
        }
        if (column?.field == 'status') {
            return VendorDataStatus.parseByte(data?.status)
        }
        if (column?.trim?.toString()?.toBoolean()) {
            if((data?."${column?.field}" as String)?.length() > 50) {
                return (data?."${column?.field}" as String)?.substring(0, 50) + '...'
            } else {
                return data?."${column?.field}"
            }
        }
        data?."${column?.field}"
    }
}
