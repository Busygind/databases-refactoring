package com.payment.mock.model

data class TransferMoneyRequest(
    val accountFromId: String,
    val accountToId: String,
    val amount: Long
)
