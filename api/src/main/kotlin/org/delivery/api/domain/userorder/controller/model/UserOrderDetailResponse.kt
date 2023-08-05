package org.delivery.api.domain.userorder.controller.model

import org.delivery.api.domain.store.controller.model.StoreResponse
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse


data class UserOrderDetailResponse (
    //주문건
    val userOrderResponse: UserOrderResponse? = null,

    //가게 대한 정보
    val storeResponse: StoreResponse? = null,

    //어떤 내역인지
    val storeMenuResponseList: List<StoreMenuResponse>? = null,
)
