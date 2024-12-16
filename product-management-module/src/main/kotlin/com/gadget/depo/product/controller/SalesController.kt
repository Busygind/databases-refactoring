package com.gadget.depo.product.controller

import com.gadget.depo.product.domain.dto.CreateSaleRequest
import com.gadget.depo.product.service.SalesService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sales")
class SalesController(
    private val salesService: SalesService,
) {
    @PostMapping
    fun createSale(@RequestBody createSaleRequest: CreateSaleRequest) {
        salesService.createSaleOnAdvert(createSaleRequest)
    }
}
