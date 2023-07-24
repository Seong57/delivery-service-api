package org.delivery.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.delivery.commom.exception.ApiException;
import org.delivery.commom.error.ErrorCode;
import org.delivery.commom.error.UserErrorCode;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/*
* User 도메인 로직을 처리하는 서비스
*/
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserEntity register(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(it -> {
                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());

                    return userRepository.save(userEntity);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity Null"));
    }

    public UserEntity login(String email, String password){
        //NullPoint 검증은 getUserWithThrow()메소드에서 처리하므로 바로 리턴해줌.
        var entity = getUserWithThrow(email, password);
        return entity;
    }

    public UserEntity getUserWithThrow(
        String email,
        String password
    ){
        return userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                email,
                password,
                UserStatus.REGISTERED
        ).orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

    public UserEntity getUserWithThrow(
            Long userId
    ){
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(
                userId,
                UserStatus.REGISTERED
        ).orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
