package com.gadget.depo.product.repository.storage

import com.gadget.depo.product.domain.UserWishlistItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WishlistRepository : JpaRepository<UserWishlistItem, String> {
    fun getUserWishlistByUserId(userId: String): List<UserWishlistItem>

    fun getWishlistItemById(id: String): UserWishlistItem?
}
