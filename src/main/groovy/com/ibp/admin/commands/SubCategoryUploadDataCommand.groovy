package com.ibp.admin.commands

import com.opencsv.bean.CsvBindByName
import grails.validation.Validateable

class SubCategoryUploadDataCommand implements Validateable {

    @CsvBindByName (column = 'Category')
    String category

    @CsvBindByName (column = 'SubCategory Name')
    String subCategory

    @CsvBindByName (column = 'SubCategory Description')
    String description

    String createdBy
    String updatedBy

    static  constraints = {
        category nullable: false, blank: false
        subCategory nullable: false, blank: false
        description nullable: true, blank: true
    }
}
