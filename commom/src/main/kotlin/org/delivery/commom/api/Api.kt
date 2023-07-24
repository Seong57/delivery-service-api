package org.delivery.commom.api

import org.delivery.commom.error.ErrorCodeIfs
import javax.validation.Valid

data class Api<T>(
    var result: Result?=null,

    @field:Valid
    var body: T?=null
) {

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

    companion object{
        @JvmStatic
        fun <T> OK(body: T?): Api<T> {
            return Api(
                result = Result.OK(),
                body = body
            )
        }
        @JvmStatic
        fun ERROR(result: Result): Api<Any> {
            return Api(
                result = result
            )
        }
        @JvmStatic
        fun ERROR(errorCodeIfs: ErrorCodeIfs): Api<Any> {
            return Api(
                result = Result.ERROR(errorCodeIfs)
            )
        }
        @JvmStatic
        fun ERROR(errorCodeIfs: ErrorCodeIfs, throwable: Throwable): Api<Any> {
            return Api(
                result = Result.ERROR(errorCodeIfs, throwable)
            )
        }
        @JvmStatic
        fun ERROR(errorCodeIfs: ErrorCodeIfs, description: String): Api<Any> {
            return Api(
                result = Result.ERROR(errorCodeIfs, description)
            )
        }
    }
}