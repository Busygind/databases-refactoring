package com.gadget.depo.payment.domain

data class PaymentDto (
    val id: String? = null,
    val transactionId: String,
    val orderId: String,
    val status: PaymentStatus
)

