package com.gadget.depo.product.domain.dto

import com.gadget.depo.product.domain.AdvertStatus

data class AdvertDto(
    val id: String,
    val productId: String,
    val sellerId: String,
    val status: AdvertStatus,
    val price: Double,
    val active: Boolean,
    val productCount: Int,
    val sellerAddressId: String,
    val description: String? = null
)

