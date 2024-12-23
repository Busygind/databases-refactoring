package com.gadget.depo.order.service

import com.gadget.depo.order.domain.Order
import com.gadget.depo.order.domain.dto.CreateOrderRequest
import com.gadget.depo.order.domain.dto.OrderStatus
import com.gadget.depo.order.extension.toOrder
import com.gadget.depo.order.repository.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class OrderService(private val orderRepository: OrderRepository) {

    @Transactional
    fun createNewOrder(createOrderRequest: CreateOrderRequest): Order =
        orderRepository.save(createOrderRequest.toOrder())

    @Transactional
    fun cancelExpiredOrders(orderExpirationInSeconds: Long): List<Order> {
        val thresholdTime = Instant.now().minusSeconds(orderExpirationInSeconds)
        return orderRepository.cancelAllOrdersOlderThan(thresholdTime)
    }

    fun countWaitingPaymentOrders(advertId: String): Int =
        orderRepository.countWaitingPaymentOrdersOnAdvert(advertId) ?: 0

    fun findById(orderId: String): Order = orderRepository.findById(orderId)
        .orElseThrow { IllegalArgumentException("Order with id $orderId doesn't exists") }

    fun updateOrderStatus(order: Order, orderStatus: OrderStatus): Order =
        orderRepository.save(order.copy(status = orderStatus))

    fun updateOrderUserAddressTo(order: Order, addressTo: String): Order =
        orderRepository.save(order.copy(userAddressTo = addressTo))
}
