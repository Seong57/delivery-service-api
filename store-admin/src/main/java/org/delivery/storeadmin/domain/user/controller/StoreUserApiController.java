package org.delivery.storeadmin.domain.user.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.delivery.storeadmin.domain.authorization.model.UserSession;
import org.delivery.storeadmin.domain.user.controller.model.StoreUserResponse;
import org.delivery.storeadmin.domain.user.converter.StoreUserConverter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store-user")
@RequiredArgsConstructor
public class StoreUserApiController {

    private final StoreUserConverter storeUserConverter;

    @GetMapping("/me")
    public StoreUserResponse me(
        // 원래는 프로젝트에서 @UserSession 이라는 어노테이션을 만들어서 사용했지만,
        // 스프링 시큐리티에는 @AuthenticationPrincipal 이라는 어노테이션이 구현되어 있음.
        // UserDetails 를 상속받은 로그인한 사용자(UserSession)를 주입을 해줌
        @Parameter(hidden = true)
        @AuthenticationPrincipal UserSession userSession
    ){
        return storeUserConverter.toResponse(userSession);
    }
}
