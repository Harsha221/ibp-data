package com.ibp.admin.utils

import com.ibp.admin.*
import com.ibp.admin.commands.ProductUploadDataCommand

class ProductBuilder {

    static Product buildProductList(ProductUploadDataCommand productUploadDataCommand ,Category category, SubCategory subCategory, String action) {
        Product product
        if (action.equalsIgnoreCase('update')) {
            product = Product.findByName(productUploadDataCommand?.product)
            product.updatedBy = productUploadDataCommand?.updatedBy
        }
        else {
            product =  new Product(productId: UUID.randomUUID())
            product.createdBy = productUploadDataCommand?.createdBy
            product.updatedBy = productUploadDataCommand?.updatedBy
        }
        product.category = category
        product.subCategory = subCategory
        product.name = productUploadDataCommand?.product
        String title = product?.name
        String url = IbpHubUtils.convertToSlug(title)
        product.displayName = url
        product.description = productUploadDataCommand?.description
        product.metaDescription = productUploadDataCommand?.metaDescription
        product.metaTitle = productUploadDataCommand?.metaTitle
        product.metaKeyword = productUploadDataCommand?.metaKeyword
        product.active = true
        product
    }
}