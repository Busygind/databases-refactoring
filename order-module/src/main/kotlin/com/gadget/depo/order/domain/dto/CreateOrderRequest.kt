package com.gadget.depo.order.domain.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateOrderRequest (

    @field:NotBlank(message = "Price must not be blank")
    val buyerId: String,

    @field:NotBlank(message = "Price must not be null")
    val advertId: String,

    @field:NotNull(message = "productsCount must not be null")
    @field:Max(value = 100, message = "productsCount must be less than 100")
    @field:Min(value = 1, message = "productsCount must be more than 0")
    val productsCount: Int,

)
