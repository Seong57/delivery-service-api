package org.delivery.api.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.ErrorCodeIfs;

import javax.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    /*
    * {
    *   "result":{
    *   .
    *   }
    *   "body":{
    *   .
    *   }
    * }
    * 이런형태.
    */

    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK(T data){

        Api<T> api = new Api<T>();
        api.result = Result.OK();
        api.body = data;

        return api;
    }
    public static Api<Object> ERROR(Result result){

        Api<Object> api = new Api<Object>();
        api.result = result;
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs){

        Api<Object> api = new Api<>();
        api.result = Result.ERROR(errorCodeIfs);
        return api;
    }
    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, Throwable tx){

        Api<Object> api = new Api<>();
        api.result = Result.ERROR(errorCodeIfs, tx);
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeIfs errorCodeIfs, String description){

        Api<Object> api = new Api<>();
        api.result = Result.ERROR(errorCodeIfs, description);
        return api;
    }
}
