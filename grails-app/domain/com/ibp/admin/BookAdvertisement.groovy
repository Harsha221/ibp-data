package com.ibp.admin
import com.ibp.admin.common.BaseDomain
import java.time.LocalDateTime

class BookAdvertisement extends BaseDomain{
    String title
    String bookAdvertisementId = UUID.randomUUID()
    String firstName
    String lastName
    String city
    String state
    String emailAddress
    String contactNumber
    AdvertisementRateCard advertisementRateCard
    String paymentId
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
    String invoiceId
    static mapping = {
        version false
        paymentStatusHistory sqlType: 'TEXT'
    }
    static constraints = {
    }
}







