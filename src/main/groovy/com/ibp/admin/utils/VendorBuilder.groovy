package com.ibp.admin.utils

import com.ibp.admin.Association
import com.ibp.admin.Category
import com.ibp.admin.Product
import com.ibp.admin.SubCategory
import com.ibp.admin.UserClient
import com.ibp.admin.VendorAssociationList
import com.ibp.admin.VendorBusinessDetails
import com.ibp.admin.VendorProductList
import com.ibp.admin.VendorUploadData
import com.ibp.admin.Vendors
import com.ibp.admin.enums.PaymentStatus
import com.ibp.admin.enums.UserStatus

class VendorBuilder {

    static Vendors buildFromCsvRecord(VendorUploadData data, UserClient userClient) {
        Vendors vendors = new Vendors(
                vendorId: UUID.randomUUID(),
                email: data?.businessEmail,
                mobileNo: data?.primaryPhoneNumber,
                userClient: userClient,
                createdBy: data?.createdBy,
                updatedBy: data?.updatedBy

        )
        vendors
    }

    static VendorBusinessDetails buildVBDFromCsvRecord(VendorUploadData data, Vendors vendors,
                                                       Category categoryInstance, SubCategory subCategory, String[] productArray, boolean forUpdate) {
        VendorBusinessDetails vendorBusinessDetails
        if (forUpdate) {
            vendorBusinessDetails = VendorBusinessDetails?.findByVendor(vendors)
        } else {
            vendorBusinessDetails = new VendorBusinessDetails()
            vendorBusinessDetails.vendorDetailUUID = UUID.randomUUID()
        }
        vendorBusinessDetails.with {
            vendor = vendors
            businessName = data?.businessName ?: it?.businessName
            businessType = data?.businessType ?: it?.businessType
            aboutCustomers = data?.aboutCustomers ?: it?.aboutCustomers
            aboutSuppliers = data?.aboutSuppliers ?: it?.aboutSuppliers
            aboutCompany = data?.aboutCompany ?: it?.aboutCompany
            address = data?.address ?: it?.address
            area = data?.area ?: it?.area
            city = data?.city ?: it?.city
            district = data?.district ?: it?.district
            pinCode = data?.pinCode ?: it?.pinCode
            state = data?.state ?: it?.state
            latitude = data?.latitude ?: it?.latitude
            longitude = data?.longitude ?: it?.longitude
            location = data?.latitude && data?.longitude ? data?.latitude + "," + data?.longitude : it?.latitude + "," + it?.longitude
            primaryLandNumber = data?.primaryPhoneNumber ?: it?.primaryLandNumber
            secondaryLandNumber = data?.secondaryLandNumber ?: it?.secondaryLandNumber
            tertiaryLandNumber = data?.tertiaryLandNumber ?: it?.tertiaryLandNumber
            primaryPhoneNumber = data?.primaryPhoneNumber ?: it?.primaryPhoneNumber
            secondaryPhoneNumber = data?.secondaryPhoneNumber ?: it?.secondaryPhoneNumber
            tertiaryPhoneNumber = data?.thirdPhoneNumber ?: it?.tertiaryPhoneNumber
            businessEmail = data?.businessEmail ?: it?.businessEmail
            secondaryEmail = data?.secondaryEmail ?: it?.secondaryEmail
            websiteAddress = data?.websiteAddress ?: it?.websiteAddress
            username = data?.username ?: it?.username
            secondaryUsername = data?.secondaryUsername ?: it?.secondaryUsername
            daysOpen = data?.daysOpen ?: it?.daysOpen
            gstNo = data?.gst ?: it?.gstNo
            status = VendorBusinessStatus.SUBMITTED.value
            paid = PaymentStatus.UN_PAID.value
//            yearOfEstablishment = data?.yearOfEstablishment ?: it?.yearOfEstablishment
            metaTitle = data?.metaTitle ?: it?.metaTitle
            metaDescription = data?.metaDescription ?: it?.metaDescription
            metaKeywords = data?.metaKeywords ?: it?.metaKeywords
            category = categoryInstance
            categoryName = categoryInstance?.name
            subCategoryName = subCategory?.name
            productName = String.join(",", productArray)
//            friendlyUrl = data?.businessName
            String vendorBusinessName = data?.businessName
            String url = IbpHubUtils.convertToSlug(vendorBusinessName)
            vendorBusinessDetails?.friendlyUrl = url.concat("-"+vendorBusinessDetails?.vendorDetailUUID)
        }
        vendorBusinessDetails
    }

    static UserClient buildUserClientFromCsvRecord(VendorUploadData data) {
        UserClient userClient = new UserClient(
                userClientId: UUID.randomUUID(),
                username: data?.username,
                businessName: data?.businessName,
                email: data?.businessEmail,
                phoneNumber: data?.primaryPhoneNumber,
                status: UserStatus.ACTIVE.value,
                type: UserType.VENDOR.value
        )
        userClient
    }

    static VendorProductList buildVendorProductList(Category category, Product product,
                                                    VendorBusinessDetails vendorBusinessDetails) {
        VendorProductList vendorProductList = new VendorProductList(vendorProductListId: UUID.randomUUID(), vendorBusiness: vendorBusinessDetails)
        vendorProductList.category = category
        vendorProductList.categoryName = category?.name
        vendorProductList.subCategory = product?.subCategory
        vendorProductList.subCategoryName = product?.subCategory?.name
        vendorProductList.product = product
        vendorProductList.productName = product?.name
        vendorProductList
    }

    static VendorAssociationList buildVendorAssociationList(Association association, VendorBusinessDetails vendorBusinessDetails) {
        VendorAssociationList vendorAssociationList = new VendorAssociationList()
        vendorAssociationList.association = association
        vendorAssociationList.associationName = association?.name
        vendorAssociationList.vendorBusiness = vendorBusinessDetails
        vendorAssociationList
    }
}

import com.ibp.admin.enums.UserType
import com.ibp.admin.enums.VendorBusinessStatus
