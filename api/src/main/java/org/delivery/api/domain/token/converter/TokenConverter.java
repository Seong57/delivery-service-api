package org.delivery.api.domain.token.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.commom.annotation.Converter;
import org.delivery.commom.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.model.TokenDto;
import org.delivery.commom.error.ErrorCode;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class TokenConverter {

    public TokenResponse tokenResponse(
            TokenDto accessToken,
            TokenDto refreshToken
    ){
        //accessToken, refreshToken 이 null 이면 예외를 발생시킴
        Objects.requireNonNull(accessToken, () -> {throw new ApiException(ErrorCode.NULL_POINT);});
        Objects.requireNonNull(refreshToken, () -> {throw new ApiException(ErrorCode.NULL_POINT);});

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
