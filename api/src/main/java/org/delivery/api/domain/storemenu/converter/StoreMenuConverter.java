package org.delivery.api.domain.storemenu.converter;

import org.delivery.commom.annotation.Converter;
import org.delivery.commom.exception.ApiException;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.commom.error.ErrorCode;
import org.delivery.db.storemenu.StoreMenuEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
public class StoreMenuConverter {

    public StoreMenuEntity toEntity(StoreMenuRegisterRequest request){

        return Optional.ofNullable(request)
                .map(it -> {
                    return StoreMenuEntity.builder()
                            .storeId(it.getStoreId())
                            .name(it.getName())
                            .amount(it.getAmount())
                            .thumbnailUrl(it.getThumbnailUrl())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreMenuResponse toResponse(StoreMenuEntity entity){

        return Optional.ofNullable(entity)
                .map(it -> {
                    return StoreMenuResponse.builder()
                            .id(it.getId())
                            .storeId(it.getStoreId())
                            .name(it.getName())
                            .amount(it.getAmount())
                            .thumbnailUrl(it.getThumbnailUrl())
                            .status(it.getStatus())
                            .likeCount(it.getLikeCount())
                            .sequence(it.getSequence())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
    public List<StoreMenuResponse> toResponse(
            List<StoreMenuEntity> list
    ){
        return list.stream()
                .map(it -> toResponse(it))
                .collect(Collectors.toList());
    }
}
