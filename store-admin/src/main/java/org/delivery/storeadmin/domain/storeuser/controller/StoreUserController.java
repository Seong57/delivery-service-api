package org.delivery.storeadmin.domain.storeuser.controller;

import lombok.RequiredArgsConstructor;
import org.delivery.storeadmin.domain.storeuser.business.StoreUserBusiness;
import org.delivery.storeadmin.domain.storeuser.controller.model.StoreUserRegisterRequest;
import org.delivery.storeadmin.domain.storeuser.controller.model.StoreUserResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("open-api/store-user")
@RequiredArgsConstructor
public class StoreUserController {

    private final StoreUserBusiness storeUserBusiness;

    @PostMapping("")
    public StoreUserResponse register(
        @Valid
        @RequestBody
        StoreUserRegisterRequest request
    ){
        var response = storeUserBusiness.register(request);
        return response;
    }
}
