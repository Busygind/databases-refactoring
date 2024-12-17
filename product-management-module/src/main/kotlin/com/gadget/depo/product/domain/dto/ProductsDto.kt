package com.gadget.depo.product.domain.dto

import com.gadget.depo.product.domain.Brand
import com.gadget.depo.product.domain.Color

data class ProductsDto(
    val id: String,
    val name: String,
    val brand: Brand,
    val size: Double,
    val color: Color?,
)
