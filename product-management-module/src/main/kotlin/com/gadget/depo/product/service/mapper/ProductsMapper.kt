package com.gadget.depo.product.service.mapper

import com.gadget.depo.product.domain.Products
import com.gadget.depo.product.domain.dto.SaveProductsRequest

import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
@Component
interface ProductsMapper {
    fun toProducts(updateRequest: SaveProductsRequest): Products
}
