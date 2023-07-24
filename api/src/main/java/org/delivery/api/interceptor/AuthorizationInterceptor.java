package org.delivery.api.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.commom.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.commom.error.ErrorCode;
import org.delivery.commom.error.TokenErrorCode;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor url : {}", request.getRequestURL());

        //WEB, chrome 의 경우 GET, POST OPTIONS = pass
        if(HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;
        }

        //js, html, png resource 를 요청하는 경우 = pass
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        //TODO header 검증
        var accessToken = request.getHeader("authorization-token");
        if(accessToken == null){
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }

        var userId = tokenBusiness.validationToken(accessToken);

        if(userId != null) {
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);
            return true;
        }

        //인증 실패
        throw new ApiException(ErrorCode.BAD_REQUEST, "인증 실패");
    }
}
