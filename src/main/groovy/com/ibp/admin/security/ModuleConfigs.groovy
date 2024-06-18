package com.ibp.admin.security

import com.ibp.admin.ModulePermissions

class ModuleConfigs {

    public static List<ModulePermissions> MODULE_PERMISSIONS = [

            // Dashboard Module
            ModulePermissions.build('Dashboard', 'View', [SecuredUrls.DASHBOARD_VIEW]),

            // Vendor Module
            ModulePermissions.build('Vendor Management', 'Create', [SecuredUrls.VENDORS_CREATE, SecuredUrls.VENDORS_SAVE]),
//            ModulePermissions.build('Vendor Management', 'Edit', [SecuredUrls.VENDORS_EDIT, SecuredUrls.VENDORS_UPDATE]),
            ModulePermissions.build('Vendor Management', 'Approve', [SecuredUrls.VENDORS_APPROVE]),
            ModulePermissions.build('Vendor Management', 'View', [SecuredUrls.VENDORS_VIEW]),
            ModulePermissions.build('Vendor Management', 'Upload', [SecuredUrls.VENDORS_UPLOADS, SecuredUrls.VENDORS_CREATE_UPLOAD, SecuredUrls.VENDORS_SAVE_UPLOAD,
                                                                    SecuredUrls.VENDORS_PROCESS_UPLOAD, SecuredUrls.VENDORS_VIEW_UPLOAD, SecuredUrls.VENDORS_EXPORT_CSV,
                                                                    SecuredUrls.VENDORS_UPLOAD_DATA_EDIT, SecuredUrls.VENDORS_UPLOAD_DATA_CORRECTION]),
            // Wati Module
            ModulePermissions.build('Wati Management', 'View', [SecuredUrls.WATI_VIEW]),
            // Role Module
            ModulePermissions.build('Role Management', 'Create', [SecuredUrls.ROLE_CREATE, SecuredUrls.ROLE_SAVE]),
            ModulePermissions.build('Role Management', 'Edit', [SecuredUrls.ROLE_EDIT, SecuredUrls.ROLE_UPDATE]),
            ModulePermissions.build('Role Management', 'View', [SecuredUrls.ROLE_VIEW]),
            // User Module
            ModulePermissions.build('User Management', 'Create', [SecuredUrls.USER_CREATE, SecuredUrls.USER_SAVE]),
            ModulePermissions.build('User Management', 'Edit', [SecuredUrls.USER_EDIT, SecuredUrls.USER_UPDATE]),
            ModulePermissions.build('User Management', 'Approve', [SecuredUrls.USER_APPROVE]),
            ModulePermissions.build('User Management', 'View', [SecuredUrls.USER_VIEW]),
            // Product  Module
            ModulePermissions.build('Product Management', 'Create', [SecuredUrls.PRODUCT_CREATE, SecuredUrls.PRODUCT_SAVE]),
            ModulePermissions.build('Product Management', 'Edit', [SecuredUrls.PRODUCT_EDIT, SecuredUrls.PRODUCT_UPDATE]),
            ModulePermissions.build('Product Management', 'Approve', [SecuredUrls.PRODUCT_APPROVE]),
            ModulePermissions.build('Product Management', 'View', [SecuredUrls.PRODUCT_VIEW]),
            ModulePermissions.build('Product Management', 'Upload', [SecuredUrls.PRODUCT_UPLOADS]),
            // Affiliation Module
            ModulePermissions.build('Affiliation Management', 'Create', [SecuredUrls.AFFILIATION_CREATE, SecuredUrls.AFFILIATION_SAVE]),
            ModulePermissions.build('Affiliation Management', 'Edit', [SecuredUrls.AFFILIATION_EDIT, SecuredUrls.AFFILIATION_UPDATE]),
            ModulePermissions.build('Affiliation Management', 'Approve', [SecuredUrls.AFFILIATION_APPROVE]),
            ModulePermissions.build('Affiliation Management', 'View', [SecuredUrls.AFFILIATION_VIEW]),
            ModulePermissions.build('Affiliation Management', 'Upload', [SecuredUrls.AFFILIATION_UPLOAD]),

            // Affiliation Product Module
            ModulePermissions.build('Affiliation Product Management', 'Create', [SecuredUrls.AFFILIATION_PRODUCT_CREATE, SecuredUrls.AFFILIATION_PRODUCT_SAVE]),
            ModulePermissions.build('Affiliation Product Management', 'Edit', [SecuredUrls.AFFILIATION_PRODUCT_EDIT, SecuredUrls.AFFILIATION_PRODUCT_UPDATE]),
            ModulePermissions.build('Affiliation Product Management', 'Approve', [SecuredUrls.AFFILIATION_PRODUCT_APPROVE]),
            ModulePermissions.build('Affiliation Product Management', 'View', [SecuredUrls.AFFILIATION_PRODUCT_VIEW]),
            ModulePermissions.build('Affiliation Product Management', 'Upload', [SecuredUrls.AFFILIATION_PRODUCT_UPLOADS]),

            // Category Module
            ModulePermissions.build('Category Management', 'Create', [SecuredUrls.CATEGORY_CREATE, SecuredUrls.CATEGORY_SAVE]),
            ModulePermissions.build('Category Management', 'Edit', [SecuredUrls.CATEGORY_EDIT, SecuredUrls.CATEGORY_UPDATE]),
            ModulePermissions.build('Category Management', 'Approve', [SecuredUrls.CATEGORY_APPROVE]),
            ModulePermissions.build('Category Management', 'View', [SecuredUrls.CATEGORY_VIEW]),
            ModulePermissions.build('Category Management', 'Upload', [SecuredUrls.CATEGORY_UPLOADS]),

            // SubCategory Module
            ModulePermissions.build('SubCategory Management', 'Create', [SecuredUrls.SUBCATEGORY_CREATE, SecuredUrls.SUBCATEGORY_SAVE]),
            ModulePermissions.build('SubCategory Management', 'Edit', [SecuredUrls.SUBCATEGORY_EDIT, SecuredUrls.SUBCATEGORY_UPDATE]),
            ModulePermissions.build('SubCategory Management', 'Approve', [SecuredUrls.SUBCATEGORY_APPROVE]),
            ModulePermissions.build('SubCategory Management', 'View', [SecuredUrls.SUBCATEGORY_VIEW]),
            ModulePermissions.build('SubCategory Management', 'Upload', [SecuredUrls.SUBCATEGORY_UPLOADS]),

            // HomeBanners Module
            ModulePermissions.build('About Committees Management', 'Create', [SecuredUrls.ABOUTCOMMITTEES_CREATE, SecuredUrls.ABOUTCOMMITTEES_SAVE]),
            ModulePermissions.build('About Committees Management', 'Edit', [SecuredUrls.ABOUTCOMMITTEES_EDIT, SecuredUrls.ABOUTCOMMITTEES_UPDATE]),
            ModulePermissions.build('About Committees Management', 'Approve', [SecuredUrls.ABOUTCOMMITTEES_APPROVE]),
            ModulePermissions.build('About Committees Management', 'View', [SecuredUrls.ABOUTCOMMITTEES_VIEW]),

            // Blog  Module
            ModulePermissions.build('Blog Management', 'Create', [SecuredUrls.BLOG_CREATE, SecuredUrls.BLOG_SAVE]),
            ModulePermissions.build('Blog Management', 'Edit', [SecuredUrls.BLOG_EDIT, SecuredUrls.BLOG_UPDATE]),
            ModulePermissions.build('Blog Management', 'Approve', [SecuredUrls.BLOG_APPROVE]),
            ModulePermissions.build('Blog Management', 'View', [SecuredUrls.BLOG_VIEW]),


            // AuditLogs  Module
            ModulePermissions.build('AuditLogs Management', 'Create', [SecuredUrls.AUDITLOGS_CREATE, SecuredUrls.AUDITLOGS_SAVE]),
            ModulePermissions.build('AuditLogs Management', 'Edit', [SecuredUrls.AUDITLOGS_EDIT, SecuredUrls.AUDITLOGS_UPDATE]),
            ModulePermissions.build('AuditLogs Management', 'Approve', [SecuredUrls.AUDITLOGS_APPROVE]),
            ModulePermissions.build('AuditLogs Management', 'View', [SecuredUrls.AUDITLOGS_VIEW]),


            // BlogCategory  Module
            ModulePermissions.build('BlogCategory Management', 'Create', [SecuredUrls.BLOGCATEGORY_CREATE, SecuredUrls.BLOGCATEGORY_SAVE]),
            ModulePermissions.build('BlogCategory Management', 'Edit', [SecuredUrls.BLOGCATEGORY_EDIT, SecuredUrls.BLOGCATEGORY_UPDATE]),
            ModulePermissions.build('BlogCategory Management', 'Approve', [SecuredUrls.BLOGCATEGORY_APPROVE]),
            ModulePermissions.build('BlogCategory Management', 'View', [SecuredUrls.BLOGCATEGORY_VIEW]),


            // Publication  Module
            ModulePermissions.build('Publication Management', 'Create', [SecuredUrls.PUBLICATION_CREATE, SecuredUrls.PUBLICATION_SAVE]),
            ModulePermissions.build('Publication Management', 'Edit', [SecuredUrls.PUBLICATION_EDIT, SecuredUrls.PUBLICATION_UPDATE]),
            ModulePermissions.build('Publication Management', 'Approve', [SecuredUrls.PUBLICATION_APPROVE]),
            ModulePermissions.build('Publication Management', 'View', [SecuredUrls.PUBLICATION_VIEW]),

            // Clients  Module
            ModulePermissions.build('Clients Management', 'Create', [SecuredUrls.CLIENTS_CREATE, SecuredUrls.CLIENTS_SAVE]),
            ModulePermissions.build('Clients Management', 'Edit', [SecuredUrls.CLIENTS_EDIT, SecuredUrls.CLIENTS_UPDATE]),
            ModulePermissions.build('Clients Management', 'Approve', [SecuredUrls.CLIENTS_APPROVE]),
            ModulePermissions.build('Clients Management', 'View', [SecuredUrls.CLIENTS_VIEW]),


            // Logo  Module
            ModulePermissions.build('Logo Management', 'Create', [SecuredUrls.LOGO_CREATE, SecuredUrls.LOGO_SAVE]),
            ModulePermissions.build('Logo Management', 'Edit', [SecuredUrls.LOGO_EDIT, SecuredUrls.LOGO_UPDATE]),
            ModulePermissions.build('Logo Management', 'Approve', [SecuredUrls.LOGO_APPROVE]),
            ModulePermissions.build('Logo Management', 'View', [SecuredUrls.LOGO_VIEW]),

            // NewsCategory  Module
            ModulePermissions.build('NewsCategory Management', 'Create', [SecuredUrls.NEWSCATEGORY_CREATE, SecuredUrls.NEWSCATEGORY_SAVE]),
            ModulePermissions.build('NewsCategory Management', 'Edit', [SecuredUrls.NEWSCATEGORY_EDIT, SecuredUrls.NEWSCATEGORY_UPDATE]),
            ModulePermissions.build('NewsCategory Management', 'Approve', [SecuredUrls.NEWSCATEGORY_APPROVE]),
            ModulePermissions.build('NewsCategory Management', 'View', [SecuredUrls.NEWSCATEGORY_VIEW]),

            // News  Module
            ModulePermissions.build('News Management', 'Create', [SecuredUrls.NEWS_CREATE, SecuredUrls.NEWS_SAVE]),
            ModulePermissions.build('News Management', 'Edit', [SecuredUrls.NEWS_EDIT, SecuredUrls.NEWS_UPDATE]),
            ModulePermissions.build('News Management', 'Approve', [SecuredUrls.NEWS_APPROVE]),
            ModulePermissions.build('News Management', 'View', [SecuredUrls.NEWS_VIEW]),

            // Bulletins  Module
            ModulePermissions.build('Bulletins Management', 'Create', [SecuredUrls.BULLETINS_CREATE, SecuredUrls.BULLETINS_SAVE]),
            ModulePermissions.build('Bulletins Management', 'Edit', [SecuredUrls.BULLETINS_EDIT, SecuredUrls.BULLETINS_UPDATE]),
            ModulePermissions.build('Bulletins Management', 'Approve', [SecuredUrls.BULLETINS_APPROVE]),
            ModulePermissions.build('Bulletins Management', 'View', [SecuredUrls.BULLETINS_VIEW]),

            // HomeBanners Module
            ModulePermissions.build('Home Banners Management', 'Create', [SecuredUrls.HOMEBANNERS_CREATE, SecuredUrls.HOMEBANNERS_SAVE]),
            ModulePermissions.build('Home Banners Management', 'Edit', [SecuredUrls.HOMEBANNERS_EDIT, SecuredUrls.HOMEBANNERS_UPDATE]),
            ModulePermissions.build('Home Banners Management', 'Approve', [SecuredUrls.HOMEBANNERS_APPROVE]),
            ModulePermissions.build('Home Banners Management', 'View', [SecuredUrls.HOMEBANNERS_VIEW]),
            // Event Module
            ModulePermissions.build('Event Management', 'Create', [SecuredUrls.EVENT_CREATE, SecuredUrls.EVENT_SAVE]),
            ModulePermissions.build('Event Management', 'Edit', [SecuredUrls.EVENT_EDIT, SecuredUrls.EVENT_UPDATE]),
            ModulePermissions.build('Event Management', 'Approve', [SecuredUrls.EVENT_APPROVE]),
            ModulePermissions.build('Event Management', 'View', [SecuredUrls.EVENT_VIEW]),
            // VideoGallery Module
            ModulePermissions.build('Video Gallery Management', 'Create', [SecuredUrls.VIDEOGALLERY_CREATE, SecuredUrls.VIDEOGALLERY_SAVE]),
            ModulePermissions.build('Video Gallery Management', 'Edit', [SecuredUrls.VIDEOGALLERY_EDIT, SecuredUrls.VIDEOGALLERY_UPDATE]),
            ModulePermissions.build('Video Gallery Management', 'Approve', [SecuredUrls.VIDEOGALLERY_APPROVE]),
            ModulePermissions.build('Video Gallery Management', 'View', [SecuredUrls.VIDEOGALLERY_VIEW]),
            // Association  Module
            ModulePermissions.build('Association Management', 'Create', [SecuredUrls.ASSOCIATION_CREATE, SecuredUrls.ASSOCIATION_SAVE]),
            ModulePermissions.build('Association Management', 'Edit', [SecuredUrls.ASSOCIATION_EDIT, SecuredUrls.ASSOCIATION_UPDATE]),
            ModulePermissions.build('Association Management', 'Approve', [SecuredUrls.ASSOCIATION_APPROVE]),
            ModulePermissions.build('Association Management', 'View', [SecuredUrls.ASSOCIATION_VIEW]),
            // Advertisement  Module
            ModulePermissions.build('Advertisement Management', 'Create', [SecuredUrls.ADVERTISEMENT_CREATE, SecuredUrls.ADVERTISEMENT_SAVE]),
            ModulePermissions.build('Advertisement Management', 'Edit', [SecuredUrls.ADVERTISEMENT_EDIT, SecuredUrls.ADVERTISEMENT_UPDATE]),
            ModulePermissions.build('Advertisement Management', 'Approve', [SecuredUrls.ADVERTISEMENT_APPROVE]),
            ModulePermissions.build('Advertisement Management', 'View', [SecuredUrls.ADVERTISEMENT_VIEW]),

            // Advertisement Rate Card  Module
            ModulePermissions.build('Advertisement Rate Card Management', 'Create', [SecuredUrls.ADVERTISEMENT_RATE_CARD_CREATE, SecuredUrls.ADVERTISEMENT_RATE_CARD_SAVE]),
            ModulePermissions.build('Advertisement Rate Card Management', 'Edit', [SecuredUrls.ADVERTISEMENT_RATE_CARD_EDIT, SecuredUrls.ADVERTISEMENT_RATE_CARD_UPDATE]),
            ModulePermissions.build('Advertisement Rate Card Management', 'Approve', [SecuredUrls.ADVERTISEMENT_RATE_CARD_APPROVE]),
            ModulePermissions.build('Advertisement Rate Card Management', 'View', [SecuredUrls.ADVERTISEMENT_RATE_CARD_VIEW]),

            // Home  Module
            ModulePermissions.build('Homepage Config', 'Create', [SecuredUrls.HOMEPAGE_CONFIG_SAVE]),
            ModulePermissions.build('Homepage Config', 'Edit', [SecuredUrls.HOMEPAGE_CONFIG_UPDATE]),
            ModulePermissions.build('Homepage Config', 'View', [SecuredUrls.HOMEPAGE_CONFIG_VIEW]),
            // Config  Module
            ModulePermissions.build('Config Management', 'Create', [SecuredUrls.CONFIG_CREATE, SecuredUrls.CONFIG_SAVE]),
            ModulePermissions.build('Config Management', 'Edit', [SecuredUrls.CONFIG_EDIT, SecuredUrls.CONFIG_UPDATE]),
            ModulePermissions.build('Config Management', 'Approve', [SecuredUrls.CONFIG_ACTIVATE_DEACTIVATE]),
            ModulePermissions.build('Config Management', 'View', [SecuredUrls.CONFIG_VIEW]),

            // NewsSubscriber  Module
            ModulePermissions.build('News Subscriber Management', 'Create', [SecuredUrls.NEWSSUBSCRIBER_CREATE, SecuredUrls.NEWSSUBSCRIBER_SAVE]),
            ModulePermissions.build('News Subscriber Management', 'Edit', [SecuredUrls.NEWSSUBSCRIBER_EDIT, SecuredUrls.NEWSSUBSCRIBER_UPDATE]),
            ModulePermissions.build('News Subscriber Management', 'Approve', [SecuredUrls.NEWSSUBSCRIBER_APPROVE]),
            ModulePermissions.build('News Subscriber Management', 'View', [SecuredUrls.NEWSSUBSCRIBER_VIEW]),

            // ContactUs  Module
            ModulePermissions.build('ContactUs Management', 'Create', [SecuredUrls.CONTACTUS_CREATE, SecuredUrls.CONTACTUS_SAVE]),
            ModulePermissions.build('ContactUs Management', 'Edit', [SecuredUrls.CONTACTUS_EDIT, SecuredUrls.CONTACTUS_UPDATE]),
            ModulePermissions.build('ContactUs Management', 'Approve', [SecuredUrls.CONTACTUS_APPROVE]),
            ModulePermissions.build('ContactUs Management', 'View', [SecuredUrls.CONTACTUS_VIEW]),


            // Book Advertisement  Module
            ModulePermissions.build('Book Advertisement Management', 'Create', [SecuredUrls.BOOKADVERTISEMENT_CREATE, SecuredUrls.BOOKADVERTISEMENT_SAVE]),
            ModulePermissions.build('Book Advertisement Management', 'Edit', [SecuredUrls.BOOKADVERTISEMENT_EDIT, SecuredUrls.BOOKADVERTISEMENT_UPDATE]),
            ModulePermissions.build('Book Advertisement Management', 'Approve', [SecuredUrls.BOOKADVERTISEMENT_APPROVE]),
            ModulePermissions.build('Book Advertisement Management', 'View', [SecuredUrls.BOOKADVERTISEMENT_VIEW]),


    ]
}
