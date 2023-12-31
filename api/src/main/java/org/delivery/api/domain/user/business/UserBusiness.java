package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.delivery.commom.annotation.Business;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.UserEntity;

@RequiredArgsConstructor
@Business
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;
    /*
    * 사용자에 대한 가입 처리 로직
    * 1. request -> entity
    * 2. entity -> save
    * 3. save Entity -> response
    * 4. response return
    */

    public UserResponse register(UserRegisterRequest request) {

        var entity = userConverter.toEntity(request);
        UserEntity savedEntity = userService.register(entity);
        return userConverter.toResponse(savedEntity);

        /*return Optional.ofNullable(request)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "request null"))
        */
    }

    /*
    * 로그인 처리 로직
    * 1. email, password 로 사용자 체크
    * 2. user Entity 로그인 확인
    * 3. token 생성
    * 4. token response
    */
    public TokenResponse login(UserLoginRequest request) {

        UserEntity userEntity = userService.login(request.getEmail(), request.getPassword());
        //사용자가 없을 경우 orElseThrow()로 인해 USER_NOT_FOUND가 발생

        // TODO 사용자가 있으면 토큰 생성
        var userId = userEntity.getId();

        var tokenResponse = tokenBusiness.issueToken(userEntity);


        return tokenResponse;
    }

    public UserResponse me(User user) {
        var userEntity = userService.getUserWithThrow(user.getId());
        var userResponse = userConverter.toResponse(userEntity);

        return userResponse;
    }
}
