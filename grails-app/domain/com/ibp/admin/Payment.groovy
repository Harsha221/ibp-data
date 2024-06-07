package com.ibp.admin

import com.ibp.admin.common.BaseDomain

import java.time.LocalDateTime

class Payment extends BaseDomain {

    String paymentId
    VendorBusinessDetails vendorBusiness
    String receiptId
    String orderId
    String razorPaymentId
    String razorPaySignature
    BigDecimal amount
    String currency
    String paymentType
    String paymentStatus
    String paymentStatusHistory
    LocalDateTime paymentDate
    LocalDateTime expirationDate

    static constraints = {
    }
    static mapping = {
        version false
        paymentStatusHistory sqlType: 'TEXT'
    }
}
