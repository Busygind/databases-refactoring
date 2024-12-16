package com.gadget.depo.payment.controller

import com.gadget.depo.payment.domain.CreatePaymentRequest
import com.gadget.depo.payment.domain.CreatePaymentResponse
import com.gadget.depo.payment.domain.PaymentDto
import com.gadget.depo.payment.service.PaymentService
import com.payment.mock.model.ProcessTransactionResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/payment")
class PaymentController(private val paymentService: PaymentService) {
    @PostMapping
    fun createPayment(@RequestBody createPaymentRequest: CreatePaymentRequest): ResponseEntity<CreatePaymentResponse> =
        ResponseEntity.ok(paymentService.createPayment(createPaymentRequest))

    @PostMapping("/callback")
    fun processBankCallback(@RequestBody processTransactionResponse: ProcessTransactionResponse): ResponseEntity<PaymentDto> =
        ResponseEntity.ok(paymentService.processBankCallback(processTransactionResponse))
}
