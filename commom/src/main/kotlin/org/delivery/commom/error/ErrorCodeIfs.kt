package org.delivery.commom.error

interface ErrorCodeIfs {

    fun getHttpStatusCode(): Int
    fun getErrorCode(): Int
    fun getDescription(): String
}