package com.gadget.depo.product.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.UuidGenerator

@Entity
@Table(name = "products")
data class Products(
    @Id
    @UuidGenerator
    val id: String? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "brand", nullable = false)
    @Enumerated(EnumType.STRING)
    val brand: Brand,

    @Column(name = "size", nullable = false)
    val size: Double,

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    val color: Color?,
)
