package com.gadget.depo.product.service.mapper

import com.gadget.depo.product.domain.Advert
import com.gadget.depo.product.domain.dto.SaveAdvertRequest
import org.mapstruct.Mapper
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring")
@Component
interface AdvertMapper {
    fun toAdvert(request: SaveAdvertRequest): Advert
}
