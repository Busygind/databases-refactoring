package com.gadget.depo.product.extension

import com.gadget.depo.product.domain.Advert
import com.gadget.depo.product.domain.dto.AdvertDto

fun Advert.toDto(): AdvertDto = AdvertDto(
    id = this.id!!,
    productId = this.productId,
    sellerId = this.sellerId,
    status = this.status,
    price = this.price,
    active = this.active,
    productCount = this.productCount,
    sellerAddressId = this.sellerAddressId,
    description = this.description
)
