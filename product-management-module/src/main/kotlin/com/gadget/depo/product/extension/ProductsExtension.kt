package com.gadget.depo.product.extension

import com.gadget.depo.product.domain.Products
import com.gadget.depo.product.domain.dto.ProductsDto

fun Products.toDto(): ProductsDto = ProductsDto(
    id = this.id!!,
    name = this.name,
    brand = this.brand,
    size = this.size,
    color = this.color,
)
