package com.gadget.depo.payment.configuration

import com.payment.mock.client.BankMockClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {
    @Bean
    fun paymentMockClient(@Value("\${bank.mock.url}") paymentMockUrl: String) = BankMockClient(paymentMockUrl)
}
