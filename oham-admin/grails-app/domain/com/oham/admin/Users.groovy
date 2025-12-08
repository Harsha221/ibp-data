package com.oham.admin

import com.oham.admin.common.BaseDomain
import grails.gorm.annotation.Entity

@Entity
class Users extends BaseDomain implements Cloneable {

    String fullName
    String email
    String secondaryEmail
    String phone
    String secondaryPhone
    String otpHash

    UserStatus status = UserStatus.active
    UserType userType

    String gstNumber
    String panNumber
    String aadhaarNumber
    String businessLicenseNumber
    String certificationDetails
    String reraRegistrationNumber

    Integer yearsInRealEstate   // UNSIGNED INT -> Integer in GORM

    String socialLinksJson

    Date lastLoginAt

    static constraints = {
        fullName nullable: false, blank: false
        email nullable: false, blank: false, unique: true

        secondaryEmail nullable: true
        phone nullable: true
        secondaryPhone nullable: true

        otpHash nullable: false

        status nullable: false
        userType nullable: false

        gstNumber nullable: true
        panNumber nullable: true
        aadhaarNumber nullable: true
        businessLicenseNumber nullable: true
        certificationDetails nullable: true
        reraRegistrationNumber nullable: true

        yearsInRealEstate nullable: true
        socialLinksJson nullable: true

        lastLoginAt nullable: true
    }

    static mapping = {
        table "users"
        id column: "id"

        version false   // your table does NOT have version field

        fullName column: "full_name"
        secondaryEmail column: "secondary_email"
        secondaryPhone column: "secondary_phone"
        gstNumber column: "gst_number"
        panNumber column: "pan_number"
        aadhaarNumber column: "aadhaar_number"
        businessLicenseNumber column: "business_license_number"
        certificationDetails column: "certification_details"
        reraRegistrationNumber column: "rera_registration_number"
        yearsInRealEstate column: "years_in_real_estate"
        socialLinksJson column: "social_links_json"
        lastLoginAt column: "last_login_at"

        // Indexes (optional but recommended)
        phone index: "idx_users_phone"
        userType column: "user_type", sqlType: "enum('customer','vendor','agent','admin','telecaller','approver','designer','contractor')"
        status column: "status", sqlType: "enum('active','inactive','locked')"
    }
}

enum UserStatus {
    active, inactive, locked
}

enum UserType {
    customer, vendor, agent, admin, telecaller, approver, designer, contractor
}
