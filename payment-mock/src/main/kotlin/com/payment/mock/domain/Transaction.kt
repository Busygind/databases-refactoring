package com.payment.mock.domain

import com.payment.mock.model.TransactionStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator

@Entity
@Table(name = "transaction")
data class Transaction(
    @Id
    @UuidGenerator
    val id: String? = null,
    val charge: Long,
    @Column(name = "account_to")
    val accountTo: String,
    val callbackUrl: String,
    val token: String,
    @Enumerated(EnumType.STRING)
    val status: TransactionStatus,
)
