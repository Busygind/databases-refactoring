package com.gadget.depo.product.dto

import com.gadget.depo.product.dto.enums.Brand
import com.gadget.depo.product.dto.enums.Color

data class ProductsDto(
    val id: String,
    val name: String,
    val brand: Brand,
    val size: Double,
    val color: Color?,
)
