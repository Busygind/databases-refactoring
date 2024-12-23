package com.gadget.depo.product.service

import com.gadget.depo.product.domain.Advert
import com.gadget.depo.product.dto.SaveAdvertRequest
import com.gadget.depo.product.repository.storage.AdvertRepository
import com.gadget.depo.product.service.mapper.AdvertMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdvertService(private val advertRepository: AdvertRepository, private val advertMapper: AdvertMapper) {
    @Transactional
    fun save(advert: Advert) : Advert {
        return advertRepository.save(advert)
    }

    fun getAllAdverts(): List<Advert> {
        return advertRepository.findAll()
    }

    fun getAdvertById(id: String): Advert? {
        return advertRepository.findById(id).orElse(null)
    }

    fun getAdvertsBySellerId(sellerId: String): List<Advert> {
        return advertRepository.findBySellerId(sellerId)
    }

    @Transactional
    fun createAdvert(request: SaveAdvertRequest): Advert {
        val advert = advertMapper.toAdvert(request)

        return advertRepository.save(advert)
    }

    @Transactional
    fun updateAdvert(id: String, updateRequest: SaveAdvertRequest): Advert? {
        val existingAdvert = advertRepository.findById(id)
        if (existingAdvert.isEmpty) return null
        val updatedAdvert = advertMapper.toAdvert(updateRequest)
        return advertRepository.save(updatedAdvert)
    }

    @Transactional
    fun deleteAdvert(id: String) {
        advertRepository.deleteById(id)
    }
}