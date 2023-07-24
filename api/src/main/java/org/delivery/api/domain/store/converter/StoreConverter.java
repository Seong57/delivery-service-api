package org.delivery.api.domain.store.converter;

import org.delivery.commom.annotation.Converter;
import org.delivery.commom.exception.ApiException;
import org.delivery.api.domain.store.controller.model.StoreRegisterRequest;
import org.delivery.api.domain.store.controller.model.StoreResponse;
import org.delivery.commom.error.ErrorCode;
import org.delivery.db.store.StoreEntity;

import java.util.Optional;

@Converter
public class StoreConverter {

    public StoreEntity toEntity(StoreRegisterRequest request){

        return Optional.ofNullable(request)
                .map(it -> {
                    return StoreEntity.builder()
                            .name(request.getName())
                            .address(request.getAddress())
                            .category(request.getStoreCategory())
                            .minimumAmount(request.getMinimumAmount())
                            .minimumDeliveryAmount(request.getMinimumDeliveryAmount())
                            .thumbnailUrl(request.getThumbnailUrl())
                            .phoneNumber(request.getPhoneNumber())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));


    }

    public StoreResponse toResponse(StoreEntity store){

        return Optional.ofNullable(store)
                .map(it -> {
                    return StoreResponse.builder()
                            .id(store.getId())
                            .name(store.getName())
                            .status(store.getStatus())
                            .category(store.getCategory())
                            .address(store.getAddress())
                            .minimumAmount(store.getMinimumAmount())
                            .minimumDeliveryAmount(store.getMinimumDeliveryAmount())
                            .thumbnailUrl(store.getThumbnailUrl())
                            .phoneNumber(store.getPhoneNumber())
                            .star(store.getStar())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
}
