package org.delivery.api.domain.storemenu.converter;

import org.delivery.commom.annotation.Converter;
import org.delivery.commom.exception.ApiException;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.commom.error.ErrorCode;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storemenu.StoreMenuEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
public class StoreMenuConverter {

    public StoreMenuEntity toEntity(
            StoreEntity storeEntity,
            StoreMenuRegisterRequest request
            ){

        return Optional.ofNullable(request)
                .map(it -> {
                    return StoreMenuEntity.builder()
                            .store(storeEntity)
                            .name(request.getName())
                            .amount(request.getAmount())
                            .thumbnailUrl(request.getThumbnailUrl())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public StoreMenuResponse toResponse(StoreMenuEntity storeMenyEntity){

        return Optional.ofNullable(storeMenyEntity)
                .map(it -> {
                    return StoreMenuResponse.builder()
                            .id(storeMenyEntity.getId())
                            .storeId(storeMenyEntity.getStore().getId())
                            .name(storeMenyEntity.getName())
                            .amount(storeMenyEntity.getAmount())
                            .thumbnailUrl(storeMenyEntity.getThumbnailUrl())
                            .status(storeMenyEntity.getStatus())
                            .likeCount(storeMenyEntity.getLikeCount())
                            .sequence(storeMenyEntity.getSequence())
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
