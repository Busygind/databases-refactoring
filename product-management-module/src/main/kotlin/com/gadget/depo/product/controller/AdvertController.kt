package com.gadget.depo.product.controller

import com.gadget.depo.product.domain.dto.AdvertDto
import com.gadget.depo.product.domain.dto.SaveAdvertRequest
import com.gadget.depo.product.extension.toDto
import com.gadget.depo.product.service.AdvertService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/advert")
class AdvertController(private val advertService: AdvertService) {

    @GetMapping
    fun getAllAdverts(): ResponseEntity<List<AdvertDto>> {
        val adverts = advertService.getAllAdverts().map { it.toDto() }
        return ResponseEntity.ok(adverts)
    }

    @GetMapping("/{id}")
    fun getAdvertById(@PathVariable id: String): ResponseEntity<AdvertDto> {
        val advert = advertService.getAdvertById(id)
        return advert?.let { ResponseEntity.ok(it.toDto()) } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/{id}/products/count")
    fun getProductsCountById(@PathVariable id: String): ResponseEntity<Int> {
        val advert = advertService.getAdvertById(id)
        return advert?.let { ResponseEntity.ok(it.productCount) } ?: ResponseEntity.notFound().build()
    }

    @GetMapping("/seller/{sellerId}")
    fun getAdvertsBySellerId(@PathVariable sellerId: String): ResponseEntity<List<AdvertDto>> {
        val adverts = advertService.getAdvertsBySellerId(sellerId).map { it.toDto() }
        return ResponseEntity.ok(adverts)
    }

    @PostMapping
    fun createAdvert(@Valid @RequestBody request: SaveAdvertRequest): ResponseEntity<AdvertDto> {
        val createdAdvert = advertService.createAdvert(request).toDto()
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdvert)
    }

    @PutMapping("/{id}")
    fun updateAdvert(
        @PathVariable id: String,
        @Valid @RequestBody updateRequest: SaveAdvertRequest
    ): ResponseEntity<AdvertDto> {
        val updatedAdvert = advertService.updateAdvert(id, updateRequest)?.toDto()
        return updatedAdvert?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteAdvert(@PathVariable id: String): ResponseEntity<Void> {
        advertService.deleteAdvert(id)
        return ResponseEntity.noContent().build()
    }
}
