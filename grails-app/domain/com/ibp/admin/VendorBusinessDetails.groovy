package com.ibp.admin

import com.ibp.admin.common.BaseDomain

import java.time.LocalDate
import java.time.LocalDateTime

class VendorBusinessDetails extends BaseDomain implements Cloneable {


    String vendorDetailUUID
    String businessName
    String businessType

    String aboutCompany
    String aboutCustomers
    String aboutSuppliers

    String businessEmail
    String secondaryEmail

    String address
    String area
    String city
    String district
    String pinCode
    String state
    String latitude
    String longitude

    String primaryLandNumber
    String secondaryLandNumber
    String tertiaryLandNumber
    String primaryPhoneNumber
    String secondaryPhoneNumber
    String tertiaryPhoneNumber

    String categoryName
    String subCategoryName
    String productName

    String username
    String secondaryUsername
    String websiteAddress

    String daysOpen
    String gstNo
    LocalDate yearOfEstablishment

    String metaTitle
    String metaDescription
    String metaKeywords
    String tags
    String location

    boolean acceptLicence = false
    boolean isLeadingClient = false

    Vendors vendor

    Integer status
    Integer paid
    boolean isVerified = false
    String responseRate
    LocalDateTime memberSince
    String businessLicenceType
    Category category

    String contactPerson
    String alternateContactPerson

    String friendlyUrl
    Boolean modifiedByVendor = false    // for Vendors when they verify their data

    static constraints = {
        friendlyUrl unique: true
    }

    static mapping = {
        version false
        businessType sqlType: 'TEXT'
        primaryLandNumber length: 45
        secondaryLandNumber length: 45
        tertiaryLandNumber length: 45
        primaryPhoneNumber length: 45
        secondaryPhoneNumber length: 45
        tertiaryPhoneNumber length: 45
        gstNo length: 45
        aboutCompany sqlType: 'TEXT'
        aboutCustomers sqlType: 'TEXT'
        aboutSuppliers sqlType: 'TEXT'
        address sqlType: 'TEXT'
        metaDescription sqlType: 'TEXT'
        metaTitle sqlType: 'TEXT'
        metaKeywords sqlType: 'TEXT'
        tags sqlType: 'TEXT'
        websiteAddress sqlType: 'TEXT'
    }


}
