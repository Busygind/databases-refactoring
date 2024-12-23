package com.gadget.depo.product.controller

import com.gadget.depo.product.dto.ProductsDto
import com.gadget.depo.product.dto.SaveProductsRequest
import com.gadget.depo.product.extension.toDto
import com.gadget.depo.product.service.ProductService
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
@RequestMapping("/api/v1/products")
class ProductsController(private val productService: ProductService) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<ProductsDto>> {
        return ResponseEntity.ok(productService.getAllProducts().map { it.toDto() })
    }

    @GetMapping("/{id}")
    fun getProductsById(@PathVariable id: String): ResponseEntity<ProductsDto> {
        val products = productService.getProductsById(id)
        return products?.let { ResponseEntity.ok(it.toDto()) } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createProducts(@Valid @RequestBody request: SaveProductsRequest): ResponseEntity<ProductsDto> {
        val createdProducts = productService.createProducts(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProducts.toDto())
    }

    @PutMapping("/{id}")
    fun updateProducts(
        @PathVariable id: String,
        @Valid @RequestBody updatedProducts: SaveProductsRequest
    ): ResponseEntity<ProductsDto> {
        val updatedProductsResult = productService.updateProducts(id, updatedProducts)
        return updatedProductsResult?.let { ResponseEntity.ok(it.toDto()) } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteProducts(@PathVariable id: String): ResponseEntity<Void> {
        productService.deleteProducts(id)
        return ResponseEntity.noContent().build()
    }
}
