package org.delivery.api.domain.user.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.commom.annotation.Converter;
import org.delivery.commom.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.commom.error.ErrorCode;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest request){

        /*
        * request가 null인 경우 Optional.empty() 객체가 생성됨.
        * */

        return Optional.ofNullable(request)
                .map(it -> {

                    //to Entity
                    return UserEntity.builder()
                            .name(request.getName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .address(request.getAddress())
                            .build()
                            ;

                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null"));
    }

    public UserResponse toResponse(UserEntity userEntity){

        return Optional.ofNullable(userEntity)
                .map(it -> {
                    //to response
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .email(userEntity.getEmail())
                            .address(userEntity.getAddress())
                            .status(userEntity.getStatus())
                            .registeredAt(userEntity.getRegisteredAt())
                            .unregisteredAt(userEntity.getUnregisteredAt())
                            .lastLoginAt(userEntity.getLastLoginAt())
                            .build();

                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));
    }
}
