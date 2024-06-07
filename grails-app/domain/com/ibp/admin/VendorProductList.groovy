package com.ibp.admin

import com.ibp.admin.common.BaseDomain

class VendorProductList extends BaseDomain implements Cloneable{

    String vendorProductListId
    String categoryName
    String subCategoryName
    String productName
    Category category
    SubCategory subCategory
    Product product
    VendorBusinessDetails vendorBusiness

    static constraints = {
    }

    static mapping = {
        version false
    }
}
