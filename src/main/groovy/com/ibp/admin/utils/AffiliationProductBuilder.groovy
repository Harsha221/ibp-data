package com.ibp.admin.utils

import com.ibp.admin.Affiliation
import com.ibp.admin.AffiliationProductCategory
import com.ibp.admin.commands.AffiliationProductUploadCommand

class AffiliationProductBuilder {

    static AffiliationProductCategory buildAffiliationProductList(AffiliationProductUploadCommand affiliationProductUploadCommand, Affiliation affiliation, String action) {
        AffiliationProductCategory productCategory
        if (action.equalsIgnoreCase('update')) {
            productCategory = AffiliationProductCategory.findByName(affiliationProductUploadCommand?.affiliationProduct)
            productCategory?.updatedBy = affiliationProductUploadCommand?.updatedBy
        }
        else {
            productCategory =  new AffiliationProductCategory(affiliationProductCategoryId: UUID.randomUUID())
            productCategory?.createdBy = affiliationProductUploadCommand?.createdBy
            productCategory?.updatedBy = affiliationProductUploadCommand?.updatedBy
        }

        productCategory.affiliation  = affiliation
        productCategory.name = affiliationProductUploadCommand?.affiliationProduct
        String title = productCategory?.name
        String url = IbpHubUtils.convertToSlug(title)
        productCategory.displayName = url
        productCategory.websiteLink = affiliationProductUploadCommand?.websiteLink
        productCategory.imagePath = affiliationProductUploadCommand?.imagePath
        productCategory
    }
}