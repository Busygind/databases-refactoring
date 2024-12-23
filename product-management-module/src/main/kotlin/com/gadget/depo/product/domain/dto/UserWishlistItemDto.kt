package com.gadget.depo.product.domain.dto

import com.gadget.depo.product.domain.UserWishlistItem

data class UserWishlistItemDto(
    val userId: String,
    val productId: String,
)

fun UserWishlistItemDto.toWishlist() = UserWishlistItem(
    userId = this.userId,
    productId = this.productId
)
