package org.delivery.commom.api

import org.delivery.commom.error.ErrorCode
import org.delivery.commom.error.ErrorCodeIfs

data class Result(
    val resultCode: Int?=null,
    val resultMessage: String?=null,
    val resultDescription: String?=null
) {


    // 이 블록 안에서는 자바의 static 과 동일하게 동작함
    companion object{
        @JvmStatic
        fun OK(): Result {
            return Result(
                resultCode = ErrorCode.OK.getHttpStatusCode(),
                resultMessage = ErrorCode.OK.getDescription(),
                resultDescription = "성공"
            )
        }
        @JvmStatic
        fun ERROR(errorCodeIfs: ErrorCodeIfs): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = "에러 발생"
            )
        }
        @JvmStatic
        fun ERROR(errorCodeIfs: ErrorCodeIfs, throwable: Throwable): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = throwable.localizedMessage
            )
        }
        @JvmStatic
        fun ERROR(errorCodeIfs: ErrorCodeIfs, description:String): Result {
            return Result(
                resultCode = errorCodeIfs.getErrorCode(),
                resultMessage = errorCodeIfs.getDescription(),
                resultDescription = description
            )
        }

    }
}