package com.gadget.depo.product.service

import com.gadget.depo.product.domain.Sales
import com.gadget.depo.product.dto.CreateSaleRequest
import com.gadget.depo.product.repository.sales.SalesRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class SalesService(
    private val salesRepository: SalesRepository,
    private val advertService: AdvertService
) {

    @Transactional(rollbackFor = [Exception::class])
    fun createSaleOnAdvert(createSaleRequest: CreateSaleRequest) {
        val advert = advertService.getAdvertById(createSaleRequest.advertId)!!

        val oldPrice = advert.price
        val newPrice = oldPrice * (100 - createSaleRequest.salePercent) / 100

        val sale = Sales(
            advertId = createSaleRequest.advertId,
            oldPrice = oldPrice, newPrice = newPrice,
            salePercent = createSaleRequest.salePercent)
        salesRepository.save(sale)

        val updatedPriceAdvert = advert.copy(price = newPrice)
        advertService.save(updatedPriceAdvert)
    }
}
