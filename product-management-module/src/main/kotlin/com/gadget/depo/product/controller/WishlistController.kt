package com.gadget.depo.product.controller

import com.gadget.depo.product.domain.UserWishlistItem
import com.gadget.depo.product.domain.dto.UserWishlistItemDto
import com.gadget.depo.product.domain.dto.toWishlist
import com.gadget.depo.product.service.UserWishlistService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/wishlist")
class WishlistController(private val userWishlistService: UserWishlistService) {
    @GetMapping("/{userId}")
    fun getWishlistByUserId(@PathVariable("userId") userId: String): List<UserWishlistItem> {
        return userWishlistService.getWishlistByUserId(userId)
    }

    @GetMapping("/item/{id}")
    fun getWishlistItemById(@PathVariable("id") id: String): UserWishlistItem? {
        return userWishlistService.getWishlistItemByItemId(id)
    }

    @PostMapping("/item")
    fun addWishlistItem(@RequestBody userWishlistItemDto: UserWishlistItemDto) {
        userWishlistService.addWishlistItem(userWishlistItemDto.toWishlist())
    }
}
