package com.ibp.admin

class NavigationUtils {

    static String showTitle(String controller, String action) {
        'IBP Admin | ' + [
                '/home'          : 'Dashboard',
                '/vendors/index' : 'Vendors',
                '/watiIntegration/index' : 'wati',
                '/vendors/businessList' : 'Business List',
                '/vendors/create': 'Create Vendor',
                '/vendors/save': 'Create Vendor',
                '/vendors/edit'  : 'Edit Vendor',
                '/vendors/update': 'Edit Vendor',
                '/vendors/uploads': 'Vendor Uploads',
                '/vendors/createUpload': 'Create Upload',
                '/vendors/saveUpload': 'Create Upload',
                '/vendors/uploadView': 'Vendor Upload Data',
                '/vendors/exportFailedCSV': 'Vendor Export CSV',
                '/vendors/editVendorUploadData': 'Edit Vendor Upload Data',
                '/vendors/correctVendorUploadData': 'Correct Vendor Upload Data',
                '/role/index' : 'Role Management',
                '/role/create': 'Create Role',
                '/role/save': 'Create Role',
                '/role/edit'  : 'Edit Role',
                '/role/update': 'Edit Role',
                '/user/index' : 'User Management',
                '/user/create': 'Create User',
                '/user/save': 'Create User',
                '/user/edit'  : 'Edit User',
                '/user/update': 'Edit User',
                '/category/index' : 'Category Management',
                '/category/create': 'Create Category',
                '/category/save': 'Create Category',
                '/category/edit'  : 'Edit Category',
                '/category/update': 'Edit Category',
                '/category/createUpload' : 'Upload Category',
                '/category/subcategory' : 'SubCategory Management',
                '/category/createSubCategory': 'Create SubCategory',
                '/subcategory/save': 'Create SubCategory',
                '/category/editSubCategory'  : 'Edit SubCategory',
                '/subcategory/createUpload' : 'Upload SubCategory',
                '/product/index' : 'Product Management',
                '/product/create': 'Create Product',
                '/product/save': 'Create Product',
                '/product/edit'  : 'Edit Product',
                '/product/update': 'Edit Product',
                '/product/createUpload' : 'Upload Product',
                '/affiliation/index' : 'Affiliation Management',
                '/affiliation/create': 'Create Affiliation',
                '/affiliation/save': 'Create Affiliation',
                '/affiliation/edit'  : 'Edit Affiliation',
                '/affiliation/update': 'Edit Affiliation',
                '/affiliation/createUpload' : 'Upload Affiliation',
                '/affiliation/affiliationProduct' : 'Affiliation Product  Management',
                '/affiliation/createProductCategory': 'Create Affiliation Product',
                '/affiliation/saveProductCategory': 'Create Affiliation Product',
                '/affiliation/editProductCategory'  : 'Edit Affiliation Product',
                '/affiliation/updateProductCategory': 'Edit Affiliation Product',
                '/affiliation/creatAffiliationProductUpload' : 'Upload Affiliation Product',
                '/blog/index' : 'Blog Management',
                '/blog/create': 'Create Blog',
                '/blog/save': 'Create Blog',
                '/blog/edit'  : 'Edit Blog',
                '/blog/update': 'Edit Blog',
                '/blogCategory/index' : 'Blog Category Management',
                '/blogCategory/create': 'Create Blog Category',
                '/blogCategory/save': 'Create Blog Category',
                '/blogCategory/edit': 'Edit Blog Category',
                '/blogCategory/update': 'Edit Blog Category',
                '/clients/index' : 'Clients Management',
                '/clients/create': 'Create Clients',
                '/clients/save': 'Create Clients',
                '/clients/edit'  : 'Edit Clients',
                '/clients/update': 'Edit Clients',
                '/logo/index' : 'Logo Management',
                '/logo/create': 'Create Logo',
                '/logo/save': 'Create Logo',
                '/logo/edit'  : 'Edit Logo',
                '/logo/update': 'Edit Logo',
                '/publication/index' : 'Publication Management',
                '/publication/create': 'Create Publication',
                '/publication/save': 'Create Publication',
                '/publication/edit'  : 'Edit Publication',
                '/publication/update': 'Edit Publication',
                '/association/index' : 'Association Management',
                '/association/create': 'Create Association',
                '/association/save': 'Create Association',
                '/association/edit'  : 'Edit Association',
                '/association/update': 'Edit Association',
                '/advertisement/index' : 'Advertisement Management',
                '/advertisement/create': 'Create Advertisement',
                '/advertisement/save': 'Create Advertisement',
                '/advertisement/edit'  : 'Edit Advertisement',
                '/advertisement/update': 'Edit Advertisement',
                '/auditLogs/index' : 'AuditLogs Management',
                '/advertisementRateCard/index' : 'Advertisement Rate Card Management',
                '/advertisementRateCard/create': 'Create Advertisement Rate Card',
                '/advertisementRateCard/save': 'Create Advertisement Rate Card',
                '/advertisementRateCard/edit'  : 'Edit Advertisement Rate Card',
                '/advertisementRateCard/update': 'Edit Advertisement Rate Card',
                '/aboutCommittees/index' : 'About Committees',
                '/aboutCommittees/create': 'Create About Committees',
                '/aboutCommittees/save': 'Create About Committees',
                '/aboutCommittees/edit': 'Edit About Committees',
                '/aboutCommittees/update': 'Edit About Committees',
                '/newsCategory/index' : 'News Category Management',
                '/newsCategory/create': 'Create News Category',
                '/newsCategory/save': 'Create News Category',
                '/newsCategory/edit': 'Edit News Category',
                '/newsCategory/update': 'Edit News Category',
                '/news/index' : 'News Management',
                '/news/create': 'Create News',
                '/news/save': 'Create News',
                '/news/edit': 'Edit News',
                '/news/update': 'Edit News',
                '/event/index' : 'Event Management',
                '/event/create': 'Create Event',
                '/event/save': 'Create Event',
                '/event/edit': 'Edit Event',
                '/event/update': 'Edit Event',
                '/videoGallery/index' : 'Video Gallery Management',
                '/videoGallery/create': 'Create Video Gallery',
                '/videoGallery/save': 'Create Video Gallery',
                '/videoGallery/edit': 'Edit Video Gallery',
                '/videoGallery/update': 'Edit Video Gallery',
                '/bulletins/index' : 'Bulletins Management',
                '/bulletins/create': 'Create Bulletins',
                '/bulletins/save': 'Create Bulletins',
                '/bulletins/edit': 'Edit Bulletins',
                '/bulletins/update': 'Edit Bulletins',
                '/homeBanners/index' : 'Home Banners Management',
                '/homeBanners/create': 'Create Home Banners',
                '/homeBanners/save': 'Create Home Banners',
                '/homeBanners/edit': 'Edit Home Banners',
                '/homeBanners/update': 'Edit Home Banners',
                '/config/index' : 'Config Management',
                '/config/create' : 'Create Config',
                '/config/save' : 'Create Config',
                '/config/edit': 'Edit Config',
                '/config/update': 'Edit Config',
                '/newsSubscriber/index' : 'NewsSubscriber Management',
                '/homepageConfig/index' : 'Homepage Config',
                '/contactUs/index' : 'ContactUs Management',
                '/bookAdvertisement/index' : 'Book Advertisement Management',
                '/dashboard/index' : 'Dashboard',
                '/login/denied': 'Access Denied'
        ]["/${controller}${action ? '/' + action : ''}"] ?: "Welcome to IBP Admin"
    }

    static String showPageTitle(String controller, String action) {
        [
            '/home'          : 'Dashboard',
            '/vendors/index' : 'Vendors',
            '/watiIntegration/index' : 'wati',
            '/vendors/create': 'Create Vendor',
            '/vendors/save': 'Create Vendor',
            '/vendors/edit'  : 'Edit Vendor',
            '/vendors/update': 'Edit Vendor',
            '/vendors/uploads': 'Vendor Uploads',
            '/vendors/createUpload': 'Create Upload',
            '/vendors/saveUpload': 'Create Upload',
            '/vendors/uploadView': 'Vendor Upload Data',
            '/vendors/exportFailedCSV': 'Vendor Export CSV',
            '/vendors/businessList' : 'Business List',
            '/vendors/editVendorUploadData': 'Edit Vendor Upload Data',
            '/vendors/correctVendorUploadData': 'Correct Vendor Upload Data',
            '/role/index' : 'Role Management',
            '/role/create': 'Create Role',
            '/role/save': 'Create Role',
            '/role/edit'  : 'Edit Role',
            '/role/update': 'Edit Role',
            '/user/index' : 'User Management',
            '/user/create': 'Create User',
            '/user/save': 'Create User',
            '/user/edit'  : 'Edit User',
            '/user/update': 'Edit User',
            '/category/index' : 'Category Management',
            '/category/create': 'Create Category',
            '/category/save': 'Create Category',
            '/category/edit'  : 'Edit Category',
            '/category/update': 'Edit Category',
            '/category/createUpload' : 'Upload Category',
            '/category/subcategory' : 'SubCategory Management',
            '/category/createSubCategory': 'Create SubCategory',
            '/category/saveSubCategory': 'Create SubCategory',
            '/category/editSubCategory'  : 'Edit SubCategory',
            '/category/updateSubCategory': 'Edit SubCategory',
            '/category/createSubCategoryUpload': 'Upload SubCategory',
            '/product/index' : 'Product Management',
            '/product/create': 'Create Product',
            '/product/save': 'Create Product',
            '/product/edit'  : 'Edit Product',
            '/product/update': 'Edit Product',
            '/product/createUpload' : 'Upload Product',
            '/affiliation/index' : 'Affiliation Management',
            '/affiliation/create': 'Create Affiliation',
            '/affiliation/save': 'Create Affiliation',
            '/affiliation/edit'  : 'Edit Affiliation',
            '/affiliation/update': 'Edit Affiliation',
            '/affiliation/createUpload' : 'Upload Affiliation',
            '/affiliation/affiliationProduct' : 'Affiliation Product Management',
            '/affiliation/createProductCategory': 'Create Affiliation Product',
            '/affiliation/saveProductCategory': 'Create Affiliation Product',
            '/affiliation/editProductCategory'  : 'Edit Affiliation Product',
            '/affiliation/updateProductCategory': 'Edit Affiliation Product',
            '/affiliation/creatAffiliationProductUpload' : 'Upload Affiliation Product',
            '/blog/index' : 'Blog Management',
            '/blog/create': 'Create Blog',
            '/blog/save': 'Create Blog',
            '/blog/edit'  : 'Edit Blog',
            '/blog/update': 'Edit Blog',
            '/blogCategory/index' : 'Blog Category Management',
            '/blogCategory/create': 'Create Blog Category',
            '/blogCategory/save': 'Create Blog Category',
            '/blogCategory/edit': 'Edit Blog Category',
            '/blogCategory/update': 'Edit Blog Category',
            '/clients/index' : 'Clients Management',
            '/clients/create': 'Create Clients',
            '/clients/save': 'Create Clients',
            '/clients/edit'  : 'Edit Clients',
            '/clients/update': 'Edit Clients',
            '/logo/index' : 'Logo Management',
            '/logo/create': 'Create Logo',
            '/logo/save': 'Create Logo',
            '/logo/edit'  : 'Edit Logo',
            '/logo/update': 'Edit Logo',
            '/publication/index' : 'Publication Management',
            '/publication/create': 'Create Publication',
            '/publication/save': 'Create Publication',
            '/publication/edit'  : 'Edit Publication',
            '/publication/update': 'Edit Publication',
            '/newsCategory/index' : 'News Category Management',
            '/newsCategory/create': 'Create News Category',
            '/newsCategory/save': 'Create News Category',
            '/newsCategory/edit': 'Edit News Category',
            '/newsCategory/update': 'Edit News Category',
            '/news/index' : 'News Management',
            '/news/create': 'Create News',
            '/news/save': 'Create News',
            '/news/edit'  : 'Edit News',
            '/news/update': 'Edit News',
            '/event/index' : 'Event Management',
            '/event/create': 'Create Event',
            '/event/save': 'Create Event',
            '/event/edit': 'Edit Event',
            '/event/update': 'Edit Event',
            '/videoGallery/index' : 'Video Gallery Management',
            '/videoGallery/create': 'Create Video Gallery',
            '/videoGallery/save': 'Create Video Gallery',
            '/videoGallery/edit'  : 'Edit Video Gallery',
            '/videoGallery/update': 'Edit Video Gallery',
            '/homeBanners/index' : 'Home Banners Management',
            '/homeBanners/create': 'Create Home Banners',
            '/homeBanners/save': 'Create Home Banners',
            '/homeBanners/edit': 'Edit Home Banners',
            '/homeBanners/update': 'Edit Home Banners',
            '/bulletins/index' : 'Bulletins Management',
            '/bulletins/create': 'Create Bulletins',
            '/bulletins/save': 'Create Bulletins',
            '/bulletins/edit': 'Edit Bulletins',
            '/bulletins/update': 'Edit Bulletins',
            '/aboutCommittees/index' : 'About Committees',
            '/aboutCommittees/create': 'Create About Committees',
            '/aboutCommittees/save': 'Create About Committees',
            '/aboutCommittees/edit': 'Edit About Committees',
            '/aboutCommittees/update': 'Edit About Committees',
            '/association/index' : 'Association Management',
            '/association/create': 'Create Association',
            '/association/save': 'Create Association',
            '/association/edit'  : 'Edit Association',
            '/association/update': 'Edit Association',
            '/advertisement/index' : 'Advertisement Management',
            '/advertisement/create': 'Create Advertisement',
            '/advertisement/save': 'Create Advertisement',
            '/advertisement/edit'  : 'Edit Advertisement',
            '/advertisement/update': 'Edit Advertisement',
            '/auditLogs/index' : 'AuditLogs Management',
            '/advertisementRateCard/index' : 'Advertisement Rate Card Management',
            '/advertisementRateCard/create': 'Create Advertisement Rate Card',
            '/advertisementRateCard/save': 'Create Advertisement Rate Card',
            '/advertisementRateCard/edit'  : 'Edit Advertisement Rate Card',
            '/advertisementRateCard/update': 'Edit Advertisement Rate Card',
            '/config/index' : 'Config Management',
            '/config/create': 'Create Config',
            '/config/save': 'Save Config',
            '/config/edit'  : 'Edit Config',
            '/config/update': 'Update Config',
            '/homepageConfig/index' : 'Homepage Config',
            '/newsSubscriber/index' : 'NewsSubscriber Management',
            '/contactUs/index' : 'ContactUs Management',
            '/bookAdvertisement/index' : 'Book Advertisement Management',
            '/dashboard/index' : 'Dashboard',
            '/login/denied': 'Access Denied'
        ]["/${controller}${action ? '/' + action : ''}"] ?: "[/${controller ?: ''}${action ? '/' + action : ''}]"
    }

    static List<Map> showBreadcrumb(String controller, String action) {
        [
            '/home'          : [],
            '/vendors/index' : [
                [title: 'Vendors', controller: 'vendors', action: 'index']
            ],
            '/watiIntegration/index' : [
                [title: 'wati', controller: 'watiIntegration', action: 'index']
            ],
            '/vendors/create': [
                    [title: 'VendorsBusinessList', controller: 'vendors', action: 'businessList'],
                    [title: 'Create Vendor', controller: 'vendors', action: 'create']
            ],
            '/vendors/save': [
                    [title: 'Create Vendor', controller: 'vendors', action: 'create']
            ],
            '/vendors/edit'  : [
                    [title: 'VendorsBusinessList', controller: 'vendors', action: 'businessList'],
                    [title: 'Edit Vendor', controller: 'vendors', action: 'edit', id: true]
            ],
            '/vendors/businessList'  : [
                    [title: 'VendorsBusinessList', controller: 'vendors', action: 'businessList']
            ],
            '/vendors/update': [
                [title: 'Edit Vendor', controller: 'vendors', action: 'update', id: true]
            ],
            '/vendors/uploads' : [
                    [title: 'Vendor Uploads', controller: 'vendors', action: 'uploads']
            ],
            '/vendors/createUpload': [
                    [title: 'Vendor Uploads', controller: 'vendors', action: 'uploads'],
                    [title: 'Create Upload', controller: 'vendors', action: 'createUpload']
            ],
            '/vendors/saveUpload': [
                    [title: 'Vendor Uploads', controller: 'vendors', action: 'uploads'],
                    [title: 'Upload', controller: 'vendors', action: 'createUpload']
            ],
            '/vendors/uploadView': [
                    [title: 'Vendor Uploads', controller: 'vendors', action: 'uploads'],
                    [title: 'Upload Data', controller: 'vendors', action: 'uploadView']
            ],
            '/vendors/exportFailedCSV': [
                    [title: 'Export Failed CSV Data', controller: 'vendors', action: 'exportFailedCSV']
            ],
            '/vendors/editVendorUploadData': [
                    [title: 'Edit Vendor Upload Data', controller: 'vendors', action: 'editVendorUploadData']
            ],
            '/role/index' : [
                    [title: 'Role Management', controller: 'role', action: 'index']
            ],
            '/role/create': [
                    [title: 'Role Management', controller: 'role', action: 'index'],
                    [title: 'Create Role', controller: 'role', action: 'create']
            ],
            '/role/save': [
                    [title: 'Role Management', controller: 'role', action: 'index'],
                    [title: 'Create Role', controller: 'role', action: 'create']
            ],
            '/role/edit'  : [
                    [title: 'Role Management', controller: 'role', action: 'index'],
                    [title: 'Edit Role', controller: 'role', action: 'edit', id: true]
            ],
            '/role/update': [
                    [title: 'Role Management', controller: 'role', action: 'index'],
                    [title: 'Edit Role', controller: 'role', action: 'update', id: true]
            ],
            '/user/index' : [
                    [title: 'User Management', controller: 'user', action: 'index']
            ],
            '/user/create': [
                    [title: 'User Management', controller: 'user', action: 'index'],
                    [title: 'Create User', controller: 'user', action: 'create']
            ],
            '/user/save': [
                    [title: 'User Management', controller: 'user', action: 'index'],
                    [title: 'Create User', controller: 'user', action: 'create']
            ],
            '/user/edit'  : [
                    [title: 'User Management', controller: 'user', action: 'index'],
                    [title: 'Edit User', controller: 'user', action: 'edit', id: true]
            ],
            '/user/update': [
                    [title: 'User Management', controller: 'user', action: 'index'],
                    [title: 'Edit User', controller: 'user', action: 'update', id: true]
            ],
            '/category/index' : [
                    [title: 'Category Management', controller: 'category', action: 'index']

            ],
            '/category/create': [
                    [title: 'Category Management', controller: 'category', action: 'index'],
                    [title: 'Create Category', controller: 'category', action: 'create']

            ],
            '/category/save': [
                    [title: 'Category Management', controller: 'category', action: 'index'],
                    [title: 'Create Category', controller: 'category', action: 'create']
            ],
            '/category/edit'  : [
                    [title: 'Category Management', controller: 'category', action: 'index'],
                    [title: 'Edit Category', controller: 'category', action: 'edit', id: true]
            ],
            '/category/update': [
                    [title: 'Category Management', controller: 'category', action: 'index'],
                    [title: 'Edit Category', controller: 'category', action: 'update', id: true]
            ],
            '/category/createUpload': [
                    [title: 'Category Management', controller: 'category', action: 'index'],
                    [title: 'Category Uploads', controller: 'category', action: 'createUpload']
            ],
            '/category/createSubCategoryUpload': [
                    [title: 'SubCategory Management', controller: 'category', action: 'subcategory'],
                    [title: 'SubCategory Uploads', controller: 'category', action: 'createSubCategoryUpload']
            ],


            '/category/subcategory' : [
                    [title: 'SubCategory Management', controller: 'category', action: 'subcategory']
            ],
            '/category/createSubCategory': [
                    [title: 'SubCategory Management', controller: 'category', action: 'subcategory'],
                    [title: 'Create SubCategory', controller: 'category', action: 'createSubCategory']
            ],
            '/category/saveSubCategory': [
                    [title: 'SubCategory Management', controller: 'category', action: 'subcategory'],
                    [title: 'Create SubCategory', controller: 'category', action: 'createSubCategory']
            ],
            '/category/editSubCategory'  : [
                    [title: 'SubCategory Management', controller: 'category', action: 'subcategory'],
                    [title: 'Edit SubCategory', controller: 'category', action: 'editSubCategory', id: true]
            ],
            '/category/updateSubCategory': [
                    [title: 'SubCategory Management', controller: 'category', action: 'subcategory'],
                    [title: 'Edit SubCategory', controller: 'category', action: 'updateSubCategory', id: true]
            ],
            '/newsCategory/index' : [
                    [title: 'News Category Management', controller: 'newsCategory', action: 'index']
            ],
            '/newsCategory/create': [
                    [title: 'News Category Management', controller: 'newsCategory', action: 'index'],
                    [title: 'Create News Category', controller: 'newsCategory', action: 'create']
            ],
            '/newsCategory/save': [
                    [title: 'News Category Management', controller: 'newsCategory', action: 'index'],
                    [title: 'Create News Category', controller: 'newsCategory', action: 'save']
            ],
            '/newsCategory/edit'  : [
                    [title: 'News Category Management', controller: 'newsCategory', action: 'index'],
                    [title: 'Edit News Category', controller: 'newsCategory', action: 'edit', id: true]
            ],
            '/newsCategory/update': [
                    [title: 'News Category Management', controller: 'newsCategory', action: 'index'],
                    [title: 'Edit News Category', controller: 'newsCategory', action: 'update', id: true]
            ],
            '/news/index' : [
                    [title: 'News Management', controller: 'news', action: 'index']
            ],
            '/news/create': [
                    [title: 'News Management', controller: 'news', action: 'index'],
                    [title: 'Create News', controller: 'news', action: 'create']
            ],
            '/news/save': [
                    [title: 'News Management', controller: 'news', action: 'index'],
                    [title: 'Create News', controller: 'news', action: 'save']
            ],
            '/news/edit'  : [
                    [title: 'News Management', controller: 'news', action: 'index'],
                    [title: 'Edit News', controller: 'news', action: 'edit', id: true]
            ],
            '/news/update': [
                    [title: 'News Management', controller: 'news', action: 'index'],
                    [title: 'Edit News', controller: 'news', action: 'update', id: true]
            ],
            '/affiliation/index' : [
                    [title: 'Affiliation Management', controller: 'affiliation', action: 'index']
            ],
            '/affiliation/affiliationProduct' : [
                    [title: 'Affiliation Product  Management', controller: 'affiliation', action: 'affiliationProduct']
            ],
            '/affiliation/createProductCategory' : [
                    [title: 'Affiliation Product Management', controller: 'affiliation', action: 'affiliationProduct'],
                    [title: 'Create Affiliation Product  ', controller: 'affiliation', action: 'createProductCategory']
            ],
            '/affiliation/saveProductCategory' : [
                    [title: 'Affiliation Product  Management', controller: 'affiliation', action: 'affiliationProduct'],
                    [title: 'Create Affiliation Product  ', controller: 'affiliation', action: 'saveProductCategory']
            ],
            '/affiliation/editProductCategory' : [
                    [title: 'Affiliation Product  Management', controller: 'affiliation', action: 'affiliationProduct'],
                    [title: 'Edit Affiliation Product  ', controller: 'affiliation', action: 'editProductCategory',id:true]
            ],
            '/affiliation/updateProductCategory': [
                    [title: 'Affiliation Product  Management', controller: 'affiliation', action: 'affiliationProduct'],
                    [title: 'Edit Affiliation Product  ', controller: 'affiliation', action: 'updateProductCategory',id:true]
            ],
            '/affiliation/creatAffiliationProductUpload' : [
                    [title: 'Affiliation Product  Management', controller: 'affiliation', action: 'affiliationProduct'],
                    [title: 'Affiliation Uploads', controller: 'affiliation', action: 'creatAffiliationProductUpload']
            ],
            '/affiliation/create': [
                    [title: 'Affiliation Management', controller: 'affiliation', action: 'index'],
                    [title: 'Create Affiliation', controller: 'affiliation', action: 'create']
            ],
            '/affiliation/save': [
                    [title: 'Affiliation Management', controller: 'affiliation', action: 'index'],
                    [title: 'Create Affiliation', controller: 'affiliation', action: 'save']
            ],
            '/affiliation/edit'  : [
                    [title: 'Affiliation Management', controller: 'affiliation', action: 'index'],
                    [title: 'Edit Affiliation', controller: 'affiliation', action: 'edit', id: true]
            ],
            '/affiliation/update': [
                    [title: 'Affiliation Management', controller: 'affiliation', action: 'index'],
                    [title: 'Edit Affiliation', controller: 'affiliation', action: 'update', id: true]
            ],
            '/affiliation/createUpload' : [
                    [title: 'Affiliation Management', controller: 'affiliation', action: 'index'],
                    [title: 'Upload Affiliation', controller: 'affiliation', action: 'createUpload']
            ],

            '/aboutCommittees/index' : [
                    [title: 'About Committees', controller: 'homeBanners', action: 'index']
            ],
            '/aboutCommittees/create': [
                    [title: 'Create About Committees', controller: 'homeBanners', action: 'create']
            ],
            '/aboutCommittees/save': [
                    [title: 'Save About Committees', controller: 'homeBanners', action: 'save']
            ],
            '/aboutCommittees/edit'  : [
                    [title: 'Edit About Committees', controller: 'homeBanners', action: 'edit', id: true]
            ],
            '/aboutCommittees/update': [
                    [title: 'Edit About Committees', controller: 'homeBanners', action: 'update', id: true]
            ],
            'bulletins/index' : [
                    [title: 'Bulletins Management', controller: 'bulletins', action: 'index']
            ],
            '/bulletins/create': [
                    [title: 'Create Bulletins', controller: 'bulletins', action: 'create']
            ],
            '/bulletins/save': [
                    [title: 'Create Bulletins', controller: 'bulletins', action: 'save']
            ],
            '/bulletins/edit'  : [
                    [title: 'Edit Bulletins', controller: 'bulletins', action: 'edit', id: true]
            ],
            '/bulletins/update': [
                    [title: 'Edit Bulletins', controller: 'bulletins', action: 'update', id: true]
            ],
            'event/index' : [
                    [title: 'Event Management', controller: 'event', action: 'index']
            ],
            '/event/create': [
                    [title: 'Create Event', controller: 'event', action: 'create']
            ],
            '/event/save': [
                    [title: 'Create Event', controller: 'event', action: 'save']
            ],
            '/event/edit'  : [
                    [title: 'Edit Event', controller: 'event', action: 'edit', id: true]
            ],
            '/event/update': [
                    [title: 'Edit Event', controller: 'event', action: 'update', id: true]
            ],
            'videoGallery/index' : [
                    [title: 'Video Gallery Management', controller: 'videoGallery', action: 'index']
            ],
            '/videoGallery/create': [
                    [title: 'Create Video Gallery', controller: 'videoGallery', action: 'create']
            ],
            '/videoGallery/save': [
                    [title: 'Create Video Gallery', controller: 'videoGallery', action: 'save']
            ],
            '/videoGallery/edit'  : [
                    [title: 'Edit Video Gallery', controller: 'videoGallery', action: 'edit', id: true]
            ],
            '/videoGallery/update': [
                    [title: 'Edit Video Gallery', controller: 'videoGallery', action: 'update', id: true]
            ],
            'homeBanners/index' : [
                    [title: 'Home Banners Management', controller: 'homeBanners', action: 'index']
            ],
            '/homeBanners/create': [
                    [title: 'Create Home Banners', controller: 'homeBanners', action: 'create']
            ],
            '/homeBanners/save': [
                    [title: 'Create Home Banners', controller: 'homeBanners', action: 'save']
            ],
            '/homeBanners/edit'  : [
                    [title: 'Edit Home Banners', controller: 'homeBanners', action: 'edit', id: true]
            ],
            '/homeBanners/update': [
                    [title: 'Edit Home Banners', controller: 'homeBanners', action: 'update', id: true]
            ],
            '/product/index' : [
                    [title: 'Product Management', controller: 'product', action: 'index']
            ],
            '/product/create': [
                    [title: 'Product Management', controller: 'product', action: 'index'],
                    [title: 'Create Product', controller: 'product', action: 'create']
            ],
            '/product/save': [
                    [title: 'Product Management', controller: 'product', action: 'index'],
                    [title: 'Create Product', controller: 'product', action: 'save']
            ],
            '/product/edit'  : [
                    [title: 'Product Management', controller: 'product', action: 'index'],
                    [title: 'Edit Product', controller: 'product', action: 'edit', id: true]
            ],
            '/product/update': [
                    [title: 'Product Management', controller: 'product', action: 'index'],
                    [title: 'Edit Product', controller: 'product', action: 'update', id: true]
            ],
            '/product/createUpload' : [
                    [title: 'Product Management', controller: 'product', action: 'index'],
                    [title: 'Upload Product', controller: 'product', action: 'createUpload']
            ],
            '/blog/index' : [
                    [title: 'Blog Management', controller: 'blog', action: 'index']
            ],
            '/blog/create': [
                    [title: 'Blog Management', controller: 'blog', action: 'index'],
                    [title: 'Create Blog', controller: 'blog', action: 'create']
            ],
            '/blog/save': [
                    [title: 'Blog Management', controller: 'blog', action: 'index'],
                    [title: 'Create Blog', controller: 'blog', action: 'save']
            ],
            '/blog/edit'  : [
                    [title: 'Blog Management', controller: 'blog', action: 'index'],
                    [title: 'Edit Blog', controller: 'blog', action: 'edit', id: true]
            ],
            '/blog/update': [
                    [title: 'Blog Management', controller: 'blog', action: 'index'],
                    [title: 'Update Blog', controller: 'blog', action: 'update', id: true]
            ],
            '/blogCategory/index' : [
                    [title: 'Blog Category Management', controller: 'blogCategory', action: 'index']
            ],
            '/blogCategory/create': [
                    [title: 'Blog Category Management', controller: 'blogCategory', action: 'index'],
                    [title: 'Create Blog Category', controller: 'blogCategory', action: 'create']
            ],
            '/blogCategory/save': [
                    [title: 'Blog Category Management', controller: 'blogCategory', action: 'index'],
                    [title: 'Create Blog Category', controller: 'blogCategory', action: 'save']
            ],
            '/blogCategory/edit'  : [
                    [title: 'Blog Category Management', controller: 'blogCategory', action: 'index'],
                    [title: 'Edit Blog Category', controller: 'blogCategory', action: 'edit', id: true]
            ],
            '/blogCategory/update': [
                    [title: 'Blog Category Management', controller: 'blogCategory', action: 'index'],
                    [title: 'Edit Blog Category', controller: 'blogCategory', action: 'update', id: true]
            ],
            '/publication/index' : [
                    [title: 'Publication Management', controller: 'publication', action: 'index']
            ],
            '/publication/create': [
                    [title: 'Publication Management', controller: 'publication', action: 'index'],
                    [title: 'Create Publication', controller: 'publication', action: 'create']
            ],
            '/publication/save': [
                    [title: 'Publication Management', controller: 'publication', action: 'index'],
                    [title: 'Create Publication', controller: 'publication', action: 'save']
            ],
            '/publication/edit'  : [
                    [title: 'Publication Management', controller: 'publication', action: 'index'],
                    [title: 'Edit Publication', controller: 'publication', action: 'edit', id: true]
            ],
            '/publication/update': [
                    [title: 'Publication Management', controller: 'publication', action: 'index'],
                    [title: 'Update Publication', controller: 'publication', action: 'update', id: true]
            ],
            '/clients/index' : [
                    [title: 'Clients Management', controller: 'clients', action: 'index']
            ],
            '/clients/create': [
                    [title: 'Clients Management', controller: 'clients', action: 'index'],
                    [title: 'Create Clients', controller: 'clients', action: 'create']
            ],
            '/clients/save': [
                    [title: 'Clients Management', controller: 'clients', action: 'index'],
                    [title: 'Create Clients', controller: 'clients', action: 'save']
            ],
            '/clients/edit'  : [
                    [title: 'Clients Management', controller: 'clients', action: 'index'],
                    [title: 'Edit Clients', controller: 'clients', action: 'edit', id: true]
            ],
            '/clients/update': [
                    [title: 'Clients Management', controller: 'clients', action: 'index'],
                    [title: 'Update Clients', controller: 'clients', action: 'update', id: true]
            ],
            '/logo/index' : [
                    [title: 'Logo Management', controller: 'logo', action: 'index']
            ],
            '/logo/create': [
                    [title: 'Logo Management', controller: 'logo', action: 'index'],
                    [title: 'Create Logo', controller: 'logo', action: 'create']
            ],
            '/logo/save': [
                    [title: 'Logo Management', controller: 'logo', action: 'index'],
                    [title: 'Create Logo', controller: 'logo', action: 'save']
            ],
            '/logo/edit'  : [
                    [title: 'Logo Management', controller: 'logo', action: 'index'],
                    [title: 'Edit Logo', controller: 'logo', action: 'edit', id: true]
            ],
            '/logo/update': [
                    [title: 'Logo Management', controller: 'logo', action: 'index'],
                    [title: 'Update Logo', controller: 'logo', action: 'update', id: true]
            ],
            '/association/index' : [
                    [title: 'Association Management', controller: 'association', action: 'index']
            ],
            '/association/create': [
                    [title: 'Association Management', controller: 'association', action: 'index'],
                    [title: 'Create Association', controller: 'association', action: 'create']
            ],
            '/association/save': [
                    [title: 'Association Management', controller: 'association', action: 'index'],
                    [title: 'Create Association', controller: 'association', action: 'save']
            ],
            '/association/edit'  : [
                    [title: 'Association Management', controller: 'association', action: 'index'],
                    [title: 'Edit Association', controller: 'association', action: 'edit', id: true]
            ],
            '/association/update': [
                    [title: 'Association Management', controller: 'association', action: 'index'],
                    [title: 'Edit Association', controller: 'association', action: 'update', id: true]
            ],
            '/auditLogs/index' : [
                    [title: 'AuditLogs Management', controller: 'auditLogs', action: 'index']
            ],
            '/advertisement/index' : [
                    [title: 'Advertisement Management', controller: 'advertisement', action: 'index']
            ],
            '/advertisement/create': [
                    [title: 'Advertisement Management', controller: 'advertisement', action: 'index'],
                    [title: 'Create Advertisement', controller: 'advertisement', action: 'create']
            ],
            '/advertisement/save': [
                    [title: 'Advertisement Management', controller: 'advertisement', action: 'index'],
                    [title: 'Create Advertisement', controller: 'advertisement', action: 'save']
            ],
            '/advertisement/edit'  : [
                    [title: 'Advertisement Management', controller: 'advertisement', action: 'index'],
                    [title: 'Edit Advertisement', controller: 'advertisement', action: 'edit', id: true]
            ],
            '/advertisement/update': [
                    [title: 'Advertisement Management', controller: 'advertisement', action: 'index'],
                    [title: 'Edit Advertisement', controller: 'advertisement', action: 'update', id: true]
            ],
            '/advertisementRateCard/index' : [
                    [title: 'Advertisement Management', controller: 'advertisementRateCard', action: 'index']
            ],
            '/advertisementRateCard/create': [
                    [title: 'Advertisement Rate Card Management', controller: 'advertisementRateCard', action: 'index'],
                    [title: 'Create Advertisement Rate Card', controller: 'advertisementRateCard', action: 'create']
            ],
            '/advertisementRateCard/save': [
                    [title: 'Advertisement Rate Card Management', controller: 'advertisementRateCard', action: 'index'],
                    [title: 'Create Advertisement Rate Card', controller: 'advertisementRateCard', action: 'save']
            ],
            '/advertisementRateCard/edit'  : [
                    [title: 'Advertisement Rate Card Management', controller: 'advertisementRateCard', action: 'index'],
                    [title: 'Edit Advertisement Rate Card', controller: 'advertisementRateCard', action: 'edit', id: true]
            ],
            '/advertisementRateCard/update': [
                    [title: 'Advertisement Rate Card Management', controller: 'advertisementRateCard', action: 'index'],
                    [title: 'Edit Advertisement Rate Card', controller: 'advertisementRateCard', action: 'update', id: true]
            ],
            '/config/index' : [
                    [title: 'Config Management', controller: 'config', action: 'index']
            ],
            '/config/create': [
                    [title: 'Config Management', controller: 'config', action: 'index'],
                    [title: 'Create Config', controller: 'config', action: 'create']
            ],
            '/config/save': [
                    [title: 'Config Management', controller: 'config', action: 'index'],
                    [title: 'Save Config', controller: 'config', action: 'save']
            ],
            '/config/edit'  : [
                    [title: 'Config Management', controller: 'config', action: 'index'],
                    [title: 'Edit Config', controller: 'config', action: 'edit', id: true]
            ],
            '/config/update': [
                    [title: 'Config Management', controller: 'config', action: 'index'],
                    [title: 'Update Config', controller: 'config', action: 'update', id: true]
            ],
            '/homepageConfig/index' : [
                    [title: 'Homepage Config', controller: 'homepageConfig', action: 'index']
            ],
            '/homepageConfig/save': [
                    [title: 'Homepage Config', controller: 'homepageConfig', action: 'index'],
                    [title: 'Homepage Config', controller: 'homepageConfig', action: 'save']
            ],
            '/homepageConfig/update': [
                    [title: 'Homepage Config', controller: 'homepageConfig', action: 'index'],
                    [title: 'Homepage Config', controller: 'homepageConfig', action: 'update']
            ],
            '/newsSubscriber/index' : [
                    [title: 'News Subscriber Management', controller: 'newsSubscriber', action: 'index']
            ],
            '/contactUs/index' : [
                    [title: 'ContactUs Management', controller: 'contactUs', action: 'index']
            ],
            '/bookAdvertisement/index' : [
                    [title: 'Book Advertisement Management', controller: 'bookAdvertisement', action: 'index']
            ],
        ]["/${controller}${action ? '/' + action : ''}"] ?: []
    }
}
