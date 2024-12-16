package com.gadget.depo.product.repository.sales

import com.gadget.depo.product.domain.Sales
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SalesRepository : JpaRepository<Sales, String> {
}
