package com.gadget.depo.order.domain.dto

data class OrderDto (
    var id: String,
    var buyerId: String,
    var advertId: String,
    var status: OrderStatus,
    val sneakerCount: Int,
    val userAddressTo: String? = null
)
