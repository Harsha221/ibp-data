package com.ibp.admin.utils

import com.ibp.admin.Affiliation
import com.ibp.admin.commands.AffiliationUploadDataCommand

class AffiliationBuilder {

    static Affiliation buildAffiliationProductList(AffiliationUploadDataCommand affiliationUploadDataCommand, String action) {
        Affiliation affiliation
        if (action.equalsIgnoreCase('update')) {
            affiliation = Affiliation.findByName(affiliationUploadDataCommand?.affiliation)
            affiliation?.updatedBy = affiliationUploadDataCommand?.updatedBy
        }
        else {
            affiliation =  new Affiliation(affiliationId: UUID.randomUUID())
            affiliation?.createdBy = affiliationUploadDataCommand?.createdBy
            affiliation?.updatedBy = affiliationUploadDataCommand?.updatedBy
        }
        affiliation.name = affiliationUploadDataCommand?.affiliation
        String title = affiliation?.name
        String url = IbpHubUtils.convertToSlug(title)
        affiliation.displayName = url
        affiliation.websiteLink = affiliationUploadDataCommand?.websiteLink
        affiliation.imagePath = affiliationUploadDataCommand?.imagePath
        affiliation
    }
}