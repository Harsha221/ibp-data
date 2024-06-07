package com.ibp.admin.utils


import com.ibp.admin.Category
import com.ibp.admin.commands.CategoryUploadDataCommand

class CategoryBuilder {

    static Category buildCategoryList(CategoryUploadDataCommand categoryUploadDataCommand, String action) {
        Category category
        if (action.equalsIgnoreCase('update')) {
            category = Category.findByName(categoryUploadDataCommand?.category)
            category.updatedBy = categoryUploadDataCommand?.updatedBy
        }
        else {
            category =  new Category(categoryId: UUID.randomUUID())
            category.createdBy = categoryUploadDataCommand?.createdBy
            category.updatedBy = categoryUploadDataCommand?.updatedBy
        }

        category.name = categoryUploadDataCommand?.category
        String title = category?.name
        String url = IbpHubUtils.convertToSlug(title)
        category.displayName = url
        category.description = categoryUploadDataCommand?.description
        category.metaDescription = categoryUploadDataCommand?.metaDescription
        category.metaTitle = categoryUploadDataCommand?.metaTitle
        category.metaKeyword = categoryUploadDataCommand?.metaKeyword
        category.active = true

        category
    }
}