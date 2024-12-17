package com.gadget.depo.product.repository.storage

import com.gadget.depo.product.domain.Advert
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdvertRepository : JpaRepository<Advert, String> {
    fun findBySellerId(sellerId: String): List<Advert>
}
