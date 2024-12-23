package com.gadget.depo.product.repository.storage

import com.gadget.depo.product.domain.Products
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductsRepository : JpaRepository<Products, String>
