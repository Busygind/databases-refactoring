package com.payment.mock.model

data class ProcessTransactionResponse(
    val transactionId: String,
    val status: TransactionStatus,
)
