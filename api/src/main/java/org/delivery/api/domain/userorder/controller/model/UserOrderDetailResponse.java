package org.delivery.api.domain.userorder.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.db.userorder.enums.UserOrderStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderDetailResponse {

    //주문건
    private UserOrderResponse userOrderResponse;

    //가게 대한 정보
    private StoreResponse storeResponse;

    //어떤 내역인지
    private List<StoreMenuResponse> storeMenuResponseList;

}
