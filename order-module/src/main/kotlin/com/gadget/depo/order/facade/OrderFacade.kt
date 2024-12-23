package com.gadget.depo.order.facade

import com.gadget.depo.order.domain.Order
import com.gadget.depo.order.domain.dto.CreateOrderRequest
import com.gadget.depo.order.domain.dto.OrderStatus
import com.gadget.depo.order.domain.dto.UpdateUserAddressToRequest
import com.gadget.depo.order.exception.InabilityLockingOrderException
import com.gadget.depo.order.service.OrderService
import com.gadget.depo.product.AdvertClient
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class OrderFacade(
    private val orderService: OrderService,
    private val advertClient: AdvertClient,
) {
    fun findById(orderId: String): Order = orderService.findById(orderId)

    @Transactional
    fun createNewOrder(createOrderRequest: CreateOrderRequest): Order {
        val availableProductCount = advertClient.getProductsCountById(createOrderRequest.advertId).body
            ?: throw IllegalArgumentException("Couldn't find advert with id ${createOrderRequest.advertId}")

        if (createOrderRequest.productsCount > availableProductCount) {
            throw InabilityLockingOrderException("Maximum available product count to create order is $availableProductCount")
        }

        val lockedProductsOfAdvert = orderService.countWaitingPaymentOrders(createOrderRequest.advertId)

        if (createOrderRequest.productsCount + lockedProductsOfAdvert > availableProductCount) {
            throw InabilityLockingOrderException("There are only ${availableProductCount - lockedProductsOfAdvert} number of products available right now")
        }

        val createdOrder = orderService.createNewOrder(createOrderRequest)

        return createdOrder
    }

    @Transactional
    fun updateOrderPaymentCreate(orderId: String): Order {
        val order = orderService.findById(orderId)

        if (order.status !in listOf(OrderStatus.WAIT_PAYMENT, OrderStatus.PAYMENT_IN_PROGRESS)) {
            throw IllegalArgumentException("Order status is invalid: ${order.status}")
        }

        return orderService.updateOrderStatus(order, OrderStatus.PAYMENT_IN_PROGRESS)
    }

    @Transactional
    fun updateOrderPaymentFinish(orderId: String): Order {
        val order = orderService.findById(orderId)

        if (order.status != OrderStatus.PAYMENT_IN_PROGRESS) {
            throw IllegalArgumentException("Bank sent payment callback, but order not in PAYMENT_IN_PROGRESS state. Something very strange...")
        }

        val paidOrder = orderService.updateOrderStatus(order, OrderStatus.IN_DELIVERY)

        return paidOrder
    }

    @Transactional
    fun updateOrderUserAddressTo(orderId: String, updateUserAddressToRequest: UpdateUserAddressToRequest) : Order {
        val order = orderService.findById(orderId)

        if (order.status !in listOf(OrderStatus.WAIT_PAYMENT, OrderStatus.PAYMENT_IN_PROGRESS)) {
            throw IllegalArgumentException("Couldn't set user address cause order already payed or expired")
        }

        return orderService.updateOrderUserAddressTo(order, updateUserAddressToRequest.userAddressTo)
    }

    @Transactional
    fun cancelExpiredOrders(orderExpirationInSeconds: Long): List<Order> {
        val expiredOrders = orderService.cancelExpiredOrders(orderExpirationInSeconds)

        return expiredOrders
    }
}
