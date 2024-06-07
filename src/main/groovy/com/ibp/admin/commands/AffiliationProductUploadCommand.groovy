package com.ibp.admin.commands

import com.opencsv.bean.CsvBindByName
import grails.validation.Validateable

class AffiliationProductUploadCommand implements Validateable {

    @CsvBindByName (column = 'Sr No')
    String srNo

    @CsvBindByName (column = 'Affiliation')
    String affiliation

    @CsvBindByName (column = 'Affiliation Product Name')
    String affiliationProduct

    @CsvBindByName (column = 'Website Link')
    String websiteLink

    @CsvBindByName (column = 'Image Path')
    String imagePath

    String createdBy
    String updatedBy

    static  constraints = {
        affiliation nullable: false, blank: false
        affiliationProduct nullable: false, blank: false
        websiteLink nullable: false, blank: false
        imagePath nullable: false, blank: false
    }
}
