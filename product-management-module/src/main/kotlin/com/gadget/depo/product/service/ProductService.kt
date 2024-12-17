package com.gadget.depo.product.service

import com.gadget.depo.product.domain.Products
import com.gadget.depo.product.domain.dto.SaveProductsRequest
import com.gadget.depo.product.repository.storage.ProductsRepository
import com.gadget.depo.product.service.mapper.ProductsMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(private val productsRepository: ProductsRepository, private val productsMapper: ProductsMapper) {

    fun getAllProducts(): List<Products> {
        return productsRepository.findAll()
    }

    fun getProductsById(id: String): Products? {
        return productsRepository.findById(id).orElse(null)
    }

    @Transactional
    fun createProducts(request: SaveProductsRequest): Products {
        val products = productsMapper.toProducts(request)
        return productsRepository.save(products)
    }

    @Transactional
    fun updateProducts(id: String, updateRequest: SaveProductsRequest): Products? {
        val existingProducts = productsRepository.findById(id)
        if (existingProducts.isEmpty) return null
        val newProducts = productsMapper.toProducts(updateRequest)
        return productsRepository.save(newProducts)
    }

    @Transactional
    fun deleteProducts(id: String) {
        productsRepository.deleteById(id)
    }
}
