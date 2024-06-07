package com.ibp.admin

import com.amazonaws.AmazonServiceException
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder
import com.amazonaws.services.simpleemail.model.Body
import com.amazonaws.services.simpleemail.model.Content
import com.amazonaws.services.simpleemail.model.Destination
import com.amazonaws.services.simpleemail.model.Message
import com.amazonaws.services.simpleemail.model.SendEmailRequest
import com.ibp.admin.utils.EmailConstants
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Value
import io.micronaut.context.annotation.Value

@Transactional
class AmazonSESService {
    AmazonSimpleEmailService amazonSESClient
    GrailsApplication grailsApplication


//    @org.springframework.beans.factory.annotation.Value('${host}')
//    private String internal_app_url;

//    @org.springframework.beans.factory.annotation.Value('${internal.app.url.local}')
//    private String internal_app_url_local;

    def sendEmail() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(grailsApplication.config.getProperty(Constants.S3Properties.AWS_ACCESS_KEY), grailsApplication.config.getProperty(Constants.S3Properties.AWS_SECRET_KEY))
        this.amazonSESClient = AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion('ap-south-1')
                .build()
    }

    void sendConfirmationEmail(String emailAddress, String username, String password,String fname,String lname ) {
        sendEmail() // Ensure that amazonSESClient is initialized
        try {
            String source = 'ibphubs@gmail.com'
            log.info("usernames....................1"+ username)
            String message = EmailConstants.EMAIL_TEMPLATE
            message = message.replaceAll('_user_',fname+" "+lname)
            message = message.replaceAll("_usernames_",username)
            log.info("usernames....................2"+ username)
            message = message.replaceAll("_passwords_",password)

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses([emailAddress]))
                    .withMessage(new Message()
                            .withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(message)))
                            .withSubject(new Content().withCharset("UTF-8").withData("User Registration"))
                    )
                    .withSource(source) // Sender email address

            amazonSESClient.sendEmail(request)
            println("Confirmation email sent successfully.")
        } catch (AmazonServiceException e) {
            println("Error sending confirmation email: " + e.getMessage())
        }
    }

    void sendConfirmationLink(String emailAdd,String userProfileId){
        sendEmail()
        try {
            String source = 'ibphubs@gmail.com'
//            def email = params?.email

            String internal_app_url = grailsApplication.config.getProperty(Constants.IbpConfigMap.IBP_BASE_URL_LINK)
//            String resetLink = "${internal_app_url_prod}/passwordReset/resetPassword?email=${emailAdd}&userProfileId=${userProfileId}"
            String resetLink = "${internal_app_url}/passwordReset/resetPassword?email=${emailAdd}&userProfileId=${userProfileId}"

//            log.info("usernames....................1"+ username)
            String message = EmailConstants.EMAIL_RESETLINK
            message = message.replaceAll("_resetLink_", resetLink)
//            message = message.replaceAll('_user_',fname+" "+lname)
//            message = message.replaceAll("_usernames_",username)
//            log.info("usernames....................2"+ username)
//            message = message.replaceAll("_passwords_",password)

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses([emailAdd]))
                    .withMessage(new Message()
                            .withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(message)))
                            .withSubject(new Content().withCharset("UTF-8").withData("Password Reset Link"))
                    )
                    .withSource(source) // Sender email address

            amazonSESClient.sendEmail(request)
            println("Reset password link sent successfully.")
        } catch (AmazonServiceException e) {
            println("Error while sending reset password link: " + e.getMessage())
        }

    }

    void sendResetPasswordEmail(String emailAddress, String username, String newPassword,String fname,String lname ) {
        sendEmail() // Ensure that amazonSESClient is initialized
        try {
            String source = 'ibphubs@gmail.com'
            log.info("usernames....................1"+ username)
            String message = EmailConstants.EMAIL_NEW_PASSWORD_TEMPLATE
            message = message.replaceAll('_user_',fname+" "+lname)
            message = message.replaceAll("_usernames_",username)
            log.info("usernames....................2"+ username)
            message = message.replaceAll("_newpasswords_",newPassword)

            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(new Destination().withToAddresses([emailAddress]))
                    .withMessage(new Message()
                            .withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(message)))
                            .withSubject(new Content().withCharset("UTF-8").withData("User New Password"))
                    )
                    .withSource(source) // Sender email address

            amazonSESClient.sendEmail(request)
            println("New password  sent successfully.")
        } catch (AmazonServiceException e) {
            println("Error sending while sending password: " + e.getMessage())
        }
    }

}
