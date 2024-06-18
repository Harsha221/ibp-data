package com.ibp.admin.security

class SecuredUrls {
    // Dashboard Module
    public static final String DASHBOARD_VIEW = '/dashboard/*'
    // Vendor Module
    public static final String VENDORS_CREATE = '/vendors/create'
    public static final String VENDORS_SAVE = '/vendors/save'
    public static final String VENDORS_EDIT = '/vendors/edit/**'
//    public static final String VENDORS_UPDATE = '/vendors/update/**'
    public static final String VENDORS_APPROVE = '/vendors/approve/**'
    public static final String VENDORS_VIEW = '/vendors/*'
    // WatiIntegration Module
    public static final String WATI_VIEW = '/watiIntegration/*'
    // Vendor Upload Module
    public static final String VENDORS_UPLOADS = '/vendors/uploads'
    public static final String VENDORS_CREATE_UPLOAD = '/vendors/createUpload'
    public static final String VENDORS_SAVE_UPLOAD = '/vendors/saveUpload'
    public static final String VENDORS_PROCESS_UPLOAD = '/vendors/process/**'
    public static final String VENDORS_VIEW_UPLOAD = '/vendors/uploadView/**'
    public static final String VENDORS_EXPORT_CSV = '/vendors/exportFailedCSV/**'
    public static final String VENDORS_UPLOAD_DATA_EDIT = '/vendors/editVendorUploadData/**'
    public static final String VENDORS_UPLOAD_DATA_CORRECTION = '/vendors/correctVendorUploadData/**'
    // Role Module
    public static final String ROLE_CREATE = '/role/create'
    public static final String ROLE_SAVE = '/role/save'
    public static final String ROLE_EDIT = '/role/edit/**'
    public static final String ROLE_UPDATE = '/role/update/**'
    public static final String ROLE_VIEW = '/role/*'
    // User Module
    public static final String USER_CREATE = '/user/create'
    public static final String USER_SAVE = '/user/save'
    public static final String USER_EDIT = '/user/edit/**'
    public static final String USER_UPDATE = '/user/update/**'
    public static final String USER_APPROVE = '/user/approve/**'
    public static final String USER_VIEW = '/user/*'
    // Product Module
    public static final String PRODUCT_CREATE = '/product/create'
    public static final String PRODUCT_SAVE = '/product/save'
    public static final String PRODUCT_EDIT = '/product/edit/**'
    public static final String PRODUCT_UPDATE = '/product/update/**'
    public static final String PRODUCT_APPROVE = '/product/approve/**'
    public static final String PRODUCT_VIEW = '/product/*'
    public static final String PRODUCT_UPLOADS = '/product/uploads/**'

    //News Subscriber Module
    public static  final  String NEWSSUBSCRIBER_CREATE = '/newsSubscriber/create'
    public static  final  String NEWSSUBSCRIBER_SAVE = '/newsSubscriber/save'
    public static  final  String NEWSSUBSCRIBER_EDIT = '/newsSubscriber/edit/**'
    public static  final  String NEWSSUBSCRIBER_UPDATE = '/newsSubscriber/update/**'
    public static  final  String NEWSSUBSCRIBER_APPROVE = '/newsSubscriber/approve/**'
    public static  final  String NEWSSUBSCRIBER_VIEW = '/newsSubscriber/*'

    //Book Advertisement Module
    public static  final  String BOOKADVERTISEMENT_CREATE = '/bookAdvertisement/create'
    public static  final  String BOOKADVERTISEMENT_SAVE = '/bookAdvertisement/save'
    public static  final  String BOOKADVERTISEMENT_EDIT = '/bookAdvertisement/edit/**'
    public static  final  String BOOKADVERTISEMENT_UPDATE = '/bookAdvertisement/update/**'
    public static  final  String BOOKADVERTISEMENT_APPROVE = '/bookAdvertisement/approve/**'
    public static  final  String BOOKADVERTISEMENT_VIEW = '/bookAdvertisement/*'

    // Category Module
    public static final String CATEGORY_CREATE = '/category/create'
    public static final String CATEGORY_SAVE = '/category/save'
    public static final String CATEGORY_EDIT = '/category/edit/**'
    public static final String CATEGORY_UPDATE = '/category/update/**'
    public static final String CATEGORY_APPROVE = '/category/approve/**'
    public static final String CATEGORY_VIEW = '/category/*'
    public static final String CATEGORY_UPLOADS = '/category/uploads/**'

    public static final String SUBCATEGORY_VIEW = '/category/subcategory/*'
    public static final String SUBCATEGORY_CREATE = '/category/createSubCategory'
    public static final String SUBCATEGORY_SAVE = '/category/saveSubCategory'
    public static final String SUBCATEGORY_EDIT = '/category/editSubCategory/**'
    public static final String SUBCATEGORY_UPDATE = '/category/updateSubCategory/**'
    public static final String SUBCATEGORY_APPROVE = '/category/approveSubCategory/**'
    public static final String SUBCATEGORY_UPLOADS = '/category/uploadsSubCategory/**'

    // Affiliation Module
    public static final String AFFILIATION_VIEW = '/affiliation/*'
    public static final String AFFILIATION_CREATE = '/affiliation/create'
    public static final String AFFILIATION_SAVE = '/affiliation/save'
    public static final String AFFILIATION_EDIT = '/affiliation/edit/**'
    public static final String AFFILIATION_UPDATE = '/affiliation/update/**'
    public static final String AFFILIATION_APPROVE = '/affiliation/approve/**'
    public static final String AFFILIATION_UPLOAD = '/affiliation/upload/**'

    // Affiliation Product Category Module
    public static final String AFFILIATION_PRODUCT_VIEW = '/affiliation/product/*'
    public static final String AFFILIATION_PRODUCT_CREATE = '/affiliation/createProductCategory'
    public static final String AFFILIATION_PRODUCT_SAVE = '/affiliation/saveProductCategory'
    public static final String AFFILIATION_PRODUCT_EDIT = '/affiliation/editProductCategory/**'
    public static final String AFFILIATION_PRODUCT_UPDATE = '/affiliation/updateProductCategory/**'
    public static final String AFFILIATION_PRODUCT_APPROVE = '/affiliation/approveProductCategory/**'
    public static final String AFFILIATION_PRODUCT_UPLOADS = '/affiliation/uploadsProductCategory/**'


    //About Committees Module
    public static final String ABOUTCOMMITTEES_CREATE = '/aboutCommittees/create'
    public static final String ABOUTCOMMITTEES_SAVE = '/aboutCommittees/save'
    public static final String ABOUTCOMMITTEES_EDIT = '/aboutCommittees/edit/**'
    public static final String ABOUTCOMMITTEES_UPDATE = '/aboutCommittees/update/**'
    public static final String ABOUTCOMMITTEES_APPROVE = '/aboutCommittees/approve/**'
    public static final String ABOUTCOMMITTEES_VIEW = '/aboutCommittees/*'


    //AuditLogs Module
    public static final String AUDITLOGS_CREATE = '/auditLogs/create'
    public static final String AUDITLOGS_SAVE = '/auditLogs/save'
    public static final String AUDITLOGS_EDIT = '/auditLogs/edit/**'
    public static final String AUDITLOGS_UPDATE = '/auditLogs/update/**'
    public static final String AUDITLOGS_APPROVE = '/auditLogs/approve/**'
    public static final String AUDITLOGS_VIEW = '/auditLogs/*'

    //Blog Module
    public static final String BLOG_CREATE = '/blog/create'
    public static final String BLOG_SAVE = '/blog/save'
    public static final String BLOG_EDIT = '/blog/edit/**'
    public static final String BLOG_UPDATE = '/blog/update/**'
    public static final String BLOG_APPROVE = '/blog/approve/**'
    public static final String BLOG_VIEW = '/blog/*'

    //BlogCategory Module
    public static final String BLOGCATEGORY_CREATE = '/blogCategory/create'
    public static final String BLOGCATEGORY_SAVE = '/blogCategory/save'
    public static final String BLOGCATEGORY_EDIT = '/blogCategory/edit/**'
    public static final String BLOGCATEGORY_UPDATE = '/blogCategory/update/**'
    public static final String BLOGCATEGORY_APPROVE = '/blogCategory/approve/**'
    public static final String BLOGCATEGORY_VIEW = '/blogCategory/*'


    //Publication Module
    public static final String PUBLICATION_CREATE = '/publication/create'
    public static final String PUBLICATION_SAVE = '/publication/save'
    public static final String PUBLICATION_EDIT = '/publication/edit/**'
    public static final String PUBLICATION_UPDATE = '/publication/update/**'
    public static final String PUBLICATION_APPROVE = '/publication/approve/**'
    public static final String PUBLICATION_VIEW = '/publication/*'

    //Clients Module
    public static final String CLIENTS_CREATE = '/clients/create'
    public static final String CLIENTS_SAVE = '/clients/save'
    public static final String CLIENTS_EDIT = '/clients/edit/**'
    public static final String CLIENTS_UPDATE = '/clients/update/**'
    public static final String CLIENTS_APPROVE = '/clients/approve/**'
    public static final String CLIENTS_VIEW = '/clients/*'

    //Logo Module
    public static final String LOGO_CREATE = '/logo/create'
    public static final String LOGO_SAVE = '/logo/save'
    public static final String LOGO_EDIT = '/logo/edit/**'
    public static final String LOGO_UPDATE = '/logo/update/**'
    public static final String LOGO_APPROVE = '/logo/approve/**'
    public static final String LOGO_VIEW = '/logo/*'


    //NewsCategory Module
    public static final String NEWSCATEGORY_CREATE = '/newsCategory/create'
    public static final String NEWSCATEGORY_SAVE = '/newsCategory/save'
    public static final String NEWSCATEGORY_EDIT = '/newsCategory/edit/**'
    public static final String NEWSCATEGORY_UPDATE = '/newsCategory/update/**'
    public static final String NEWSCATEGORY_APPROVE = '/newsCategory/approve/**'
    public static final String NEWSCATEGORY_VIEW = '/newsCategory/*'

    //News Module
    public static final String NEWS_CREATE = '/news/create'
    public static final String NEWS_SAVE = '/news/save'
    public static final String NEWS_EDIT = '/news/edit/**'
    public static final String NEWS_UPDATE = '/news/update/**'
    public static final String NEWS_APPROVE = '/news/approve/**'
    public static final String NEWS_VIEW = '/news/*'

    //HomeBanner Module
    public static final String HOMEBANNERS_CREATE = '/homeBanners/create'
    public static final String HOMEBANNERS_SAVE = '/homeBanners/save'
    public static final String HOMEBANNERS_EDIT = '/homeBanners/edit/**'
    public static final String HOMEBANNERS_UPDATE = '/homeBanners/update/**'
    public static final String HOMEBANNERS_APPROVE = '/homeBanners/approve/**'
    public static final String HOMEBANNERS_VIEW = '/homeBanners/*'

    //Bulletins Module
    public static final String BULLETINS_CREATE = '/bulletins/create'
    public static final String BULLETINS_SAVE = '/bulletins/save'
    public static final String BULLETINS_EDIT = '/bulletins/edit/**'
    public static final String BULLETINS_UPDATE = '/bulletins/update/**'
    public static final String BULLETINS_APPROVE = '/bulletins/approve/**'
    public static final String BULLETINS_VIEW = '/bulletins/*'

    //VideoGallery Module
    public static final String VIDEOGALLERY_CREATE = '/videoGallery/create'
    public static final String VIDEOGALLERY_SAVE = '/videoGallery/save'
    public static final String VIDEOGALLERY_EDIT = '/videoGallery/edit/**'
    public static final String VIDEOGALLERY_UPDATE = '/videoGallery/update/**'
    public static final String VIDEOGALLERY_APPROVE = '/videoGallery/approve/**'
    public static final String VIDEOGALLERY_VIEW = '/videoGallery/*'

    //Event Module
    public static final String EVENT_CREATE = '/event/create'
    public static final String EVENT_SAVE = '/event/save'
    public static final String EVENT_EDIT = '/event/edit/**'
    public static final String EVENT_UPDATE = '/event/update/**'
    public static final String EVENT_APPROVE = '/event/approve/**'
    public static final String EVENT_VIEW = '/event/*'

    //Association Module
    public static final String ASSOCIATION_CREATE = '/association/create'
    public static final String ASSOCIATION_SAVE = '/association/save'
    public static final String ASSOCIATION_EDIT = '/association/edit/**'
    public static final String ASSOCIATION_UPDATE = '/association/update/**'
    public static final String ASSOCIATION_APPROVE = '/association/approve/**'
    public static final String ASSOCIATION_VIEW = '/association/*'

    //Advertisement Module
    public static final String ADVERTISEMENT_CREATE = '/advertisement/create'
    public static final String ADVERTISEMENT_SAVE = '/advertisement/save'
    public static final String ADVERTISEMENT_EDIT = '/advertisement/edit/**'
    public static final String ADVERTISEMENT_UPDATE = '/advertisement/update/**'
    public static final String ADVERTISEMENT_APPROVE = '/advertisement/approve/**'
    public static final String ADVERTISEMENT_VIEW = '/advertisement/*'

    //Advertisement Rate Card Module
    public static final String ADVERTISEMENT_RATE_CARD_CREATE = '/advertisementRateCard/create'
    public static final String ADVERTISEMENT_RATE_CARD_SAVE = '/advertisementRateCard/save'
    public static final String ADVERTISEMENT_RATE_CARD_EDIT = '/advertisementRateCard/edit/**'
    public static final String ADVERTISEMENT_RATE_CARD_UPDATE = '/advertisementRateCard/update/**'
    public static final String ADVERTISEMENT_RATE_CARD_APPROVE = '/advertisementRateCard/approve/**'
    public static final String ADVERTISEMENT_RATE_CARD_VIEW = '/advertisementRateCard/*'

    //Home Page Config Module
    public static final String HOMEPAGE_CONFIG_SAVE = '/homepageConfig/save'
    public static final String HOMEPAGE_CONFIG_UPDATE = '/homepageConfig/save/**'
    public static final String HOMEPAGE_CONFIG_VIEW = '/homepageConfig/*'
    //Config Module
    public static final String CONFIG_CREATE = '/config/create'
    public static final String CONFIG_SAVE = '/config/save'
    public static final String CONFIG_EDIT = '/config/edit/**'
    public static final String CONFIG_UPDATE = '/config/update/**'
    public static final String CONFIG_ACTIVATE_DEACTIVATE = '/config/activateDeactivate/**'
    public static final String CONFIG_VIEW = '/config/*'

    //Contact Us Module
    public static  final  String CONTACTUS_CREATE = '/contactUs/create'
    public static  final  String CONTACTUS_SAVE = '/contactUs/save'
    public static  final  String CONTACTUS_EDIT = '/contactUs/edit/**'
    public static  final  String CONTACTUS_UPDATE = '/contactUs/update/**'
    public static  final  String CONTACTUS_APPROVE = '/contactUs/approve/**'
    public static  final  String CONTACTUS_VIEW = '/contactUs/*'
}
