package com.ibp.admin

class Constants {
    public static final String ROLE_SUPER_ADMIN = 'ROLE_SUPER_ADMIN'
    public static final int VENDOR_PROCESS_BATCH_SIZE = 1000
    public static final int PRODUCT_PROCESS_BATCH_SIZE = 5
    public static final String SLASH = '/'
    public static final String DOT = '.'
    public static final String BLANK = ''

    static class DateFormat {
        public static final String DD_MM_YYYY_TIME = 'dd/MM/yyyy HH:mm:ss'
        public static final String DD_MMM_YYYY_TIME = 'dd MMM yyyy HH:mm:ss'
    }

    static class S3Properties {
        public static final String FOLDER_NAME = 'aws.s3.ibphub.folder-name'
        public static final String AWS_ACCESS_KEY = 'aws.s3.ibphub.access-key'
        public static final String AWS_SECRET_KEY = 'aws.s3.ibphub.secret-key'
        public static final String BUCKET = 'aws.s3.ibphub.bucket-name'
        public static final String S3_BASE_URL = 'aws.s3.ibphub.base-url'
    }

    static class S3Folders {
        public static final String HOME_PAGE_ADS = 'homepage-ads'
        public static final String CATEGORY = 'category'
        public static final String ADVERTISEMENT = 'advertisement'
        public static final String ADVERTISEMENT_RATE_CARD = 'advertisementRateCard'
        public static final String CATEGORY_ICON = 'category/icon'
        public static final String VENDOR = 'vendor'
        public static final String NEWS = 'news'
        public static final String HOMEBANNERS = 'homeBanners'
        public static final String COMMITTEEMEMBERS = 'committeeMembers'
        public static final String EVENT = 'event'
        public static final String BULLETINS = 'bulletins'
        public static final String NEWSCATEGORY = 'newsCategory'
        public static final String BLOGCATEGORY = 'blogCategory'
        public static final String CLIENTS = 'clients'
        public static final String LOGO = 'logo'
        public static final String PUBLICATION = 'publication'
    }

    static class IbpConfigMap {
        public static final String VENDOR_PROCESS_BATCH_SIZE = 'vendor.process.batch.size'
        public static final String PRODUCT_PROCESS_BATCH_SIZE = 'product.process.batch.size'
        public static final String CATEGORY_PROCESS_BATCH_SIZE = 'category.process.batch.size'
        public static final String SUBCATEGORY_PROCESS_BATCH_SIZE = 'subcategory.process.batch.size'
        public static final String AFFILIATION_PROCESS_BATCH_SIZE = 'affiliation.process.batch.size'
        public static final String IBP_PHONE_OTP_VALUE = 'ibp.phone.otp.value'
        public static final String IBP_PASSWORD_EXPIRE_MINUTES = 'ibp.otp.expire.minutes'
        public static final String IBP_BUSINESS_SEARCH_MILES = 'ibp.business.search.miles'
        public static final String IBP_BUSINESS_RESPONSE_RATE_DAYS = 'ibp.business.response-rate.days'
        public static final String IBP_SMS_IDEA_SENDER_MOBILE = 'ibp.sms-idea.sender.mobile'
        public static final String IBP_SMS_IDEA_PASSWORD = 'ibp.sms-idea.password'
        public static final String IBP_SMS_IDEA_API_KEY = 'ibp.sms-idea.api.key'
        public static final String IBP_SMS_IDEA_SENDER_ID = 'ibp.sms-idea.sender.id'
        public static final String IBP_SMS_IDEA_MESSAGE_CONTENT = 'ibp.sms-idea.message.content'
        public static final String IBP_SMS_IDEA_TEMPLATE_ID = 'ibp.sms-idea.template.id'
        public static final String IBP_SMS_IDEA_API_URL = 'ibp.sms-idea.api.url'
        public static final String IBP_SES_FROM_EMAIL = 'ibp.ses.from.email'
        public static final String IBP_SES_EMAIL_OTP_SUBJECT = 'ibp.ses.email.otp.subject'
        public static final String IBP_BASE_URL_LINK = 'host.url'

    }

    public static final Map<String, String> IbpConfigs = [
        "${IbpConfigMap.VENDOR_PROCESS_BATCH_SIZE}": '1000',
        "${IbpConfigMap.PRODUCT_PROCESS_BATCH_SIZE}": '1000',
        "${IbpConfigMap.CATEGORY_PROCESS_BATCH_SIZE}": '1000',
        "${IbpConfigMap.SUBCATEGORY_PROCESS_BATCH_SIZE}": '1000',
        "${IbpConfigMap.AFFILIATION_PROCESS_BATCH_SIZE}": '1000',
        "${IbpConfigMap.IBP_PHONE_OTP_VALUE}": '123456',
        "${IbpConfigMap.IBP_PASSWORD_EXPIRE_MINUTES}": '10',
        "${IbpConfigMap.IBP_BUSINESS_SEARCH_MILES}": '20',
        "${IbpConfigMap.IBP_BUSINESS_RESPONSE_RATE_DAYS}": '3',
        "${IbpConfigMap.IBP_SMS_IDEA_SENDER_MOBILE}": '9825217616',
        "${IbpConfigMap.IBP_SMS_IDEA_PASSWORD}": 'Ibp@123',
        "${IbpConfigMap.IBP_SMS_IDEA_API_KEY}": 'ce96809ad1ef4b4784aa374ae772156a',
        "${IbpConfigMap.IBP_SMS_IDEA_SENDER_ID}": 'TSTMSG',
        "${IbpConfigMap.IBP_SMS_IDEA_MESSAGE_CONTENT}": '{OTP} is OTP for your login at IBP HUB, This code expires in {EXPIRE_MINUTES} minutes. Never share OTP with anyone.',
        "${IbpConfigMap.IBP_SMS_IDEA_TEMPLATE_ID}": 'IBPHUB',
        "${IbpConfigMap.IBP_SMS_IDEA_API_URL}": 'https://smsidea.co.in',
        "${IbpConfigMap.IBP_SES_FROM_EMAIL}": 'ibp@technous.in',
        "${IbpConfigMap.IBP_SES_EMAIL_OTP_SUBJECT}": 'OTP'
    ]

}
