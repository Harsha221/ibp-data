package com.ibp.admin.commands

import com.opencsv.bean.CsvBindByName
import grails.validation.Validateable

class CategoryUploadDataCommand implements Validateable {

    @CsvBindByName (column = 'Category Name')
    String category

    @CsvBindByName (column = 'Category Description')
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
        description nullable: false, blank: false
    }
}
