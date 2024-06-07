package com.ibp.admin.utils

class EmailConstants {
    public static final String EMAIL_TEMPLATE = "<h1>Welcome to IBP Back Office !</h1>\n" +
            "  <p>Dear _user_,</p>\n" +
            "  <p>We are delighted to welcome you to IBP Back Office. Your registration is now complete, and you can start exploring all the amazing features we offer.</p>\n" +
            "  <p>Here are your login details:</p>\n" +
            "  <ul>\n" +
            "    <li><strong>Username:</strong> _usernames_</li>\n" +
            "    <li><strong>Password:</strong> _passwords_</li>\n" +
            "  </ul>\n" +
            "  <p>Please keep your login credentials secure and do not share them with anyone. If you have any questions or need assistance, feel free to reach out to our support team.</p>\n" +
            "  <p>Thank you for choosing us!</p>\n" +
            "  <p>Best regards,<br>\n" +
            "  IBP</p>"

    public  static String EMAIL_RESETLINK = """
    <html>
    <head></head>
    <body>
    <p>Please click the following link to reset your password:</p>
    <a href="_resetLink_">Reset Password</a>
    </body>
    </html>
"""

    public static final String EMAIL_NEW_PASSWORD_TEMPLATE = "<h1>Welcome to IBP Back Office !</h1>\n" +
            "  <p>Dear _user_,</p>\n" +
            "  <p>We are delighted to welcome you to IBP Back Office. Your registration is now complete, and you can start exploring all the amazing features we offer.</p>\n" +
            "  <p>Here are your login details:</p>\n" +
            "  <ul>\n" +
            "    <li><strong>Username:</strong> _usernames_</li>\n" +
            "    <li><strong>New Password:</strong> _newpasswords_</li>\n" +
            "  </ul>\n" +
            "  <p>Please keep your login credentials secure and do not share them with anyone. If you have any questions or need assistance, feel free to reach out to our support team.</p>\n" +
            "  <p>Thank you for choosing us!</p>\n" +
            "  <p>Best regards,<br>\n" +
            "  IBP</p>"

}
