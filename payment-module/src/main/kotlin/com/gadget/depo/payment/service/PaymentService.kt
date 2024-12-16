package com.gadget.depo.payment.service

import com.gadget.depo.payment.domain.CreatePaymentRequest
import com.gadget.depo.payment.domain.CreatePaymentResponse
import com.gadget.depo.payment.domain.Payment
import com.gadget.depo.payment.domain.PaymentDto
import com.gadget.depo.payment.domain.PaymentStatus
import com.gadget.depo.payment.domain.toDto
import com.gadget.depo.payment.repository.PaymentRepository
import com.payment.mock.client.BankMockClient
import com.payment.mock.client.dto.CreateTransactionRequest
import com.payment.mock.model.ProcessTransactionResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val bankMockClient: BankMockClient,
    @Value("\${payment.service.host}") private val gatewayHost: String,
    @Value("\${payment.bank.account.id}") private val bankAccountId: String,
) {
    @Transactional
    fun createPayment(createPaymentRequest: CreatePaymentRequest): CreatePaymentResponse {
        val request = CreateTransactionRequest(
            callbackUrl = "$gatewayHost/api/v1/payment/callback",
            charge = createPaymentRequest.charge,
            accountTo = bankAccountId,
            token = createPaymentRequest.bankToken!!
        )

        val createTransactionResponse = bankMockClient.createTransaction(request).body
            ?: throw IllegalStateException("Bank is dead")

        paymentRepository.save(
            Payment(
                transactionId = createTransactionResponse.transactionId,
                orderId = createPaymentRequest.orderId,
                status = PaymentStatus.WAIT_BANK_CALLBACK
            )
        )

        return CreatePaymentResponse(createTransactionResponse.paymentUrl)
    }

    @Transactional
    fun processBankCallback(processTransactionResponse: ProcessTransactionResponse) : PaymentDto {
        val paymentStatus = PaymentStatus.valueOf(processTransactionResponse.status.name)

        val payment =
            paymentRepository.updatePaymentStatus(processTransactionResponse.transactionId, paymentStatus.name)[0]

        return payment.toDto()
    }
}
