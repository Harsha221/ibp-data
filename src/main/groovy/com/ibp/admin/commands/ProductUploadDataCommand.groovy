package com.ibp.admin.commands

import com.opencsv.bean.CsvBindByName
import grails.validation.Validateable

class ProductUploadDataCommand implements Validateable {


    @CsvBindByName (column = 'Category')
    String category

    @CsvBindByName (column = 'Details Category')
    String subCategory

    @CsvBindByName (column = 'Details Category ID')
    Long subCategoryId

    @CsvBindByName (column = 'Product Name')
    String product

    @CsvBindByName (column = 'Product Description')
    String description

    @CsvBindByName (column = 'Meta Title')
    String metaTitle

    @CsvBindByName (column = 'Meta Description')
    String metaDescription

    @CsvBindByName (column = 'Meta Keyword')
    String metaKeyword

    String createdBy
    String updatedBy

    static  constraints = {

        category nullable: false, blank: false
        subCategory nullable: false, blank: false
        subCategoryId nullable: true, blank: true
        product nullable: false, blank: false
        description nullable: true, blank: true
    }
}
