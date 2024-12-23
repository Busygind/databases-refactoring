package com.gadget.depo.order.extension

import com.gadget.depo.order.domain.Order
import com.gadget.depo.order.domain.dto.CreateOrderRequest
import com.gadget.depo.order.domain.dto.OrderDto
import com.gadget.depo.order.domain.dto.OrderStatus

fun Order.toDto(): OrderDto = OrderDto(
    id = this.id!!,
    buyerId = this.buyerId,
    advertId = this.advertId,
    status = this.status,
    sneakerCount = this.sneakerCount,
    userAddressTo = this.userAddressTo
)

fun CreateOrderRequest.toOrder(orderStatus: OrderStatus = OrderStatus.WAIT_PAYMENT) = Order(
    buyerId = this.buyerId,
    advertId = this.advertId,
    status = orderStatus,
    sneakerCount = this.productsCount
)
