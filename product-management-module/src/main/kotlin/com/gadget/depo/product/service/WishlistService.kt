package com.gadget.depo.product.service

import com.gadget.depo.product.domain.UserWishlistItem
import com.gadget.depo.product.repository.storage.WishlistRepository
import org.springframework.stereotype.Service

@Service
class UserWishlistService(private val wishlistRepository: WishlistRepository) {
    fun getWishlistByUserId(userId: String): List<UserWishlistItem> = wishlistRepository.getUserWishlistByUserId(userId)

    fun getWishlistItemByItemId(id: String): UserWishlistItem? = wishlistRepository.getWishlistItemById(id)

    fun addWishlistItem(userWishlistItem: UserWishlistItem) {
        wishlistRepository.save(userWishlistItem)
    }
}
