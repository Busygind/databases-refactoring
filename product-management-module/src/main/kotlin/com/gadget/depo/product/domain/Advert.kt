package com.gadget.depo.product.domain

import com.gadget.depo.product.dto.enums.AdvertStatus
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator

@Entity
@Table(name = "advert")
data class Advert(
    @Id
    @UuidGenerator
    val id: String? = null,

    @Column(name = "product_id", nullable = false)
    val productId: String,

    @Column(name = "seller_id", nullable = false)
    val sellerId: String,

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    val status: AdvertStatus,

    @Column(name = "price", nullable = false)
    val price: Double,

    @Column(name = "active", nullable = false)
    val active: Boolean,

    @Column(name = "product_count", nullable = false)
    val productCount: Int,

    @Column(name = "seller_address_id", nullable = false)
    val sellerAddressId: String,

    @Column(name = "description", nullable = true)
    val description: String? = null
)
