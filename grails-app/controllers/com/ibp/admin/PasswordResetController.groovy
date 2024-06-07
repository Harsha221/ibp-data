package com.ibp.admin

class PasswordResetController {
    AmazonSESService amazonSESService
    UserService userService


    def forgotPassword() {
        log.info("Forgot password action invoked.................................")
        render view: 'forgetPassword'
    }

    def sendResetLink() {
        def email = params?.email

        def userProfile = UserProfile?.findByEmail(email)

        if (userProfile) {
            log.info("user is valid.............................................")
            if(userProfile?.userProfileId) {
                // Send reset password link logic here
                amazonSESService.sendConfirmationLink(userProfile.email,userProfile.userProfileId)
                userProfile?.isReset = Boolean.TRUE
                userService.save(userProfile)
                flash.message = "Password reset link sent successfully to $email"
            } else {
                log.info("user is Invalid.............................................")
                flash.error = "User Profile Id does not exist:"
            }
        } else {
            log.info("user is Invalid.............................................")
            flash.error = "Invalid email address: $email"
        }
        redirect(controller: 'passwordReset', action: 'forgotPassword')
    }

    def resetPassword() {
        def email = params.email
        def userProfileId = params?.userProfileId
        def userProfile = UserProfile?.findByEmailAndUserProfileId(email, userProfileId)
        log.info("user profile id :"+userProfileId)
        flash.error = ""
        if (userProfile && userProfile.isReset) {
            render(view: 'resetPassword', model: [email: email, userProfileId: userProfile.userProfileId])
        } else {
            flash.error = "Invalid email address: $email or user profile id $userProfileId"
            render(view: 'resetPassword', model: [email: email, userProfileId: userProfile?.userProfileId])
            //      redirect(controller: 'passwordReset', action: 'forgotPassword')
        }
    }

    def saveNewPassword() {
        def email = params?.email
        def newPassword = params?.newPassword
        def confirmPassword = params?.confirmPassword

        if (newPassword == confirmPassword) {
            def userProfile = UserProfile.findByEmail(email)
            if (userProfile) {
                def fname = userProfile?.firstName
                def lname = userProfile?.lastName
                def user = userProfile?.user
                if (user) {
                    user?.password = newPassword
                    userService.save(user)
                    amazonSESService.sendResetPasswordEmail(userProfile.email,userProfile?.user?.username,newPassword,fname,lname)
                    userProfile?.isReset = Boolean.FALSE
                    userService.save(userProfile)
                    flash.messages = "Password updated successfully for $email"

                    redirect uri: "/login/auth"

                } else {
                    flash.error = "User not found for email: $email"
                    redirect(controller: 'passwordReset', action: 'forgotPassword')

                }
            } else {
                flash.error = "User profile not found for email: $email"
                redirect(controller: 'passwordReset', action: 'forgotPassword')
            }
        } else {
            flash.error = "Passwords do not match"
            render(view: 'resetPassword', model: [email: email])

        }
    }


}
