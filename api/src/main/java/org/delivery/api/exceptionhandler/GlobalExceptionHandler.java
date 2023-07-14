package org.delivery.api.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE)   //가장 마지막에 실행 적용, 숫자가 작을 수록 우선도 높음
public class GlobalExceptionHandler {

    //매개변수의 exception 은 스프링이 주입을 해줌.
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(Exception exception){
        log.error("", exception);

        return ResponseEntity
                .status(500)
                .body(
                    Api.ERROR(ErrorCode.SERVER_ERROR)
                );
    }
}
