package com.gadget.depo.payment.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator

@Entity
@Table(name = "payment")
data class Payment(
    @Id
    @UuidGenerator
    val id: String? = null,
    val transactionId: String,
    val orderId: String,
    @Enumerated(EnumType.STRING)
    val status: PaymentStatus
)

fun Payment.toDto() = PaymentDto(
    id = id,
    transactionId = transactionId,
    orderId = orderId,
    status = status
)
