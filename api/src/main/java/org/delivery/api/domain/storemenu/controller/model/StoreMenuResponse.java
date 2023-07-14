package org.delivery.api.domain.storemenu.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.delivery.db.storemenu.enums.StoreMenuStatus;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@NotBlank
@AllArgsConstructor
@Builder
public class StoreMenuResponse {

    private Long id;

    private Long storeId;

    private String name;

    private BigDecimal amount;

    private StoreMenuStatus status;

    private String thumbnailUrl;

    private int likeCount;

    private int sequence;
}
