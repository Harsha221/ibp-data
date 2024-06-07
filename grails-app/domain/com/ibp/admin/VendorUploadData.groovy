package com.ibp.admin

import com.ibp.admin.common.BaseDomain
import com.opencsv.bean.CsvBindByName
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class VendorUploadData extends BaseDomain {

    @CsvBindByName(column = 'Sr No')
    String srNo
    @CsvBindByName(column = 'category')
    String category
    @CsvBindByName(column = 'sub_category')
    String subCategory
    @CsvBindByName(column = 'Products_and_services')
    String product
    @CsvBindByName(column = 'business_name')
    String businessName
    @CsvBindByName(column = 'address')
    String address
    @CsvBindByName(column = 'area')
    String area
    @CsvBindByName(column = 'city')
    String city
    @CsvBindByName(column = 'district')
    String district
    @CsvBindByName(column = 'state')
    String state
    @CsvBindByName(column = 'pincode')
    String pinCode
    @CsvBindByName(column = 'primary_land_number')
    String primaryLandNumber
    @CsvBindByName(column = 'secondary_land_number')
    String secondaryLandNumber
    @CsvBindByName(column = 'tertiary_land_number')
    String tertiaryLandNumber
    @CsvBindByName(column = 'primary_phone_number')
    String primaryPhoneNumber
    @CsvBindByName(column = 'secondary_phone_number')
    String secondaryPhoneNumber
    @CsvBindByName(column = '3rd_phone_number')
    String thirdPhoneNumber
    @CsvBindByName(column = 'business_email')
    String businessEmail
    @CsvBindByName(column = 'secondary_email')
    String secondaryEmail
    @CsvBindByName(column = 'website_address')
    String websiteAddress
    @CsvBindByName(column = 'username')
    String username
    @CsvBindByName(column = 'secondary_username')
    String secondaryUsername
    @CsvBindByName(column = 'business_type')
    String businessType
    @CsvBindByName(column = 'about_company')
    String aboutCompany
    @CsvBindByName(column = 'about_customers')
    String aboutCustomers
    @CsvBindByName(column = 'about_suppliers')
    String aboutSuppliers
    @CsvBindByName(column = 'year_of_establishment')
    String yearOfEstablishment
    @CsvBindByName(column = 'gst')
    String gst
    @CsvBindByName(column = 'days_open')
    String daysOpen
    @CsvBindByName(column = 'meta title')
    String metaTitle
    @CsvBindByName(column = 'meta description')
    String metaDescription
    @CsvBindByName(column = 'meta keywords')
    String metaKeywords
    @CsvBindByName(column = 'lattitude')
    String latitude
    @CsvBindByName(column = 'longitude')
    String longitude
    @CsvBindByName(column = 'directory')
    String directory
    @CsvBindByName(column = 'User ID')
    String userID
    @CsvBindByName(column = 'logo_image_url')
    String logoImageUrl
    @CsvBindByName(column = 'photo_gallery')
    String photoGallery
    @CsvBindByName(column = 'error')
    String error

    Byte status

    String createdBy
    String updatedBy

    static belongsTo = [
            vendorUpload: VendorUpload,
            vendors     : Vendors
    ]

    static constraints = {
        vendors nullable: true, blank: true
        status nullable: false, blank: false
    }

    static mapping = {
        version false
        businessType sqlType: 'TEXT'
        product sqlType:'TEXT'
        error sqlType: 'TEXT'
        metaTitle sqlType: 'TEXT'
        metaDescription sqlType: 'TEXT'
        metaKeywords sqlType: 'TEXT'
        aboutCompany sqlType: 'TEXT'
        aboutCustomers sqlType: 'TEXT'
        aboutSuppliers sqlType: 'TEXT'
        websiteAddress sqlType: 'TEXT'
    }

    static VendorUploadData getTestExportData() {
        return new VendorUploadData(
                srNo: 1,
                category: 'Test',
                subCategory: 'Test',
                product: 'Test',
                businessName: 'Test',
                address: 'Test',
                area: 'Test',
                city: 'Test',
                district: 'Test',
                state: 'Test',
                pinCode: 'Test',
                primaryLandNumber: 'Test',
                secondaryLandNumber: 'Test',
                tertiaryLandNumber: 'Test',
                primaryPhoneNumber: 'Test',
                secondaryPhoneNumber: 'Test',
                thirdPhoneNumber: 'Test',
                businessEmail: 'Test',
                secondaryEmail: 'Test',
                websiteAddress: 'Test',
                username: 'Test',
                secondaryUsername: 'Test',
                businessType: 'Test',
                aboutCompany: 'Test',
                aboutCustomers: 'Test',
                aboutSuppliers: 'Test',
                yearOfEstablishment: 'Test',
                gst: 'Test',
                daysOpen: 'Test',
                metaTitle: 'Test',
                metaDescription: 'Test',
                metaKeywords: 'Test',
                latitude: 'Test',
                longitude: 'Test'
        )
    }
}
