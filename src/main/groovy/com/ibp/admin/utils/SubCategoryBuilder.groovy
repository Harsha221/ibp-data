package com.ibp.admin.utils


import com.ibp.admin.Category
import com.ibp.admin.SubCategory
import com.ibp.admin.commands.SubCategoryUploadDataCommand

class SubCategoryBuilder {

    static SubCategory buildSubCategoryList(SubCategoryUploadDataCommand subCategoryUploadDataCommand, Category category, String action) {
        SubCategory subCategory
        if (action.equalsIgnoreCase('update')) {
            subCategory = SubCategory.findByName(subCategoryUploadDataCommand?.subCategory)
            subCategory?.updatedBy = subCategoryUploadDataCommand?.updatedBy
        }
        else {
            subCategory =  new SubCategory(subCategoryId: UUID.randomUUID())
            subCategory?.createdBy = subCategoryUploadDataCommand?.createdBy
            subCategory?.updatedBy = subCategoryUploadDataCommand?.updatedBy
        }

        subCategory.category  = category
        subCategory.name = subCategoryUploadDataCommand?.subCategory
        subCategory.description = subCategoryUploadDataCommand?.description
        subCategory.active = true
        String title = subCategory?.name
        String url = IbpHubUtils.convertToSlug(title)
        subCategory?.displayName = url
        subCategory
    }
}