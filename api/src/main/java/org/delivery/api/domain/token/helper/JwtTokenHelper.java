package org.delivery.api.domain.token.helper;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.delivery.commom.exception.ApiException;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDto;
import org.delivery.commom.error.TokenErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper implements TokenHelperIfs {

    @Value("${token.secret.key}")
    private String secretKey;
    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;

    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public TokenDto issueAccessToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour);

        var expiredAt = Date.from(
                expiredLocalDateTime
                    .atZone(ZoneId.systemDefault())
                    .toInstant());

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {
        LocalDateTime expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour);

        java.util.Date expiredAt = Date.from(
                expiredLocalDateTime
                        .atZone(ZoneId.systemDefault())
                        .toInstant());

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        String jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();


        try{
            var result = parser.parseClaimsJws(token);
            return new HashMap<String, Object>(result.getBody());

        }catch (Exception e){
            if(e instanceof SignatureException){
                //토큰이 유효하지 않을 때 발생
                throw new ApiException(TokenErrorCode.INVALID_TOKEN, e);

            } else if (e instanceof ExpiredJwtException) {
                //만료된 토큰일 때 발생
                throw new ApiException(TokenErrorCode.EXPIRED_TOKEN, e);

            }else{
                //그 외의 에러
                throw new ApiException(TokenErrorCode.TOKEN_EXCEPTION, e);
            }
        }
    }
}
