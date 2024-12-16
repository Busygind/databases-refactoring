package com.gadget.depo.payment.domain

data class CreatePaymentRequest(
    val orderId: String,
    val addressId: String,
    val charge: Long,
    var bankToken: String? = null,
)
