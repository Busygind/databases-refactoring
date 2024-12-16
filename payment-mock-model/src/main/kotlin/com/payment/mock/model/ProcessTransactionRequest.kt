package com.payment.mock.model

data class ProcessTransactionRequest(
    val cardNumber: String,
    val cvv: Int
)
