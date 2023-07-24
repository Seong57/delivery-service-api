package org.delivery.commom.exception

import org.delivery.commom.error.ErrorCodeIfs

interface ApiExceptionIfs {
    val errorCodeIfs: ErrorCodeIfs?
    val errorDescription: String?
}