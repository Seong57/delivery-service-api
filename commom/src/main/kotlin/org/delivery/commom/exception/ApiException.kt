package org.delivery.commom.exception

import org.delivery.commom.error.ErrorCodeIfs

class ApiException: RuntimeException, ApiExceptionIfs {

    override val errorCodeIfs: ErrorCodeIfs
    override val errorDescription: String

    constructor(errorCodeIfs: ErrorCodeIfs): super(errorCodeIfs.getDescription()){
        this.errorCodeIfs = errorCodeIfs
        this.errorDescription = errorCodeIfs.getDescription()
    }

    constructor(
        errorCodeIfs: ErrorCodeIfs,
        errorCodeDescription: String

    ): super(errorCodeIfs.getDescription()){

        this.errorCodeIfs = errorCodeIfs
        this.errorDescription = errorCodeDescription
    }

    constructor(
        errorCodeIfs: ErrorCodeIfs,
        tx: Throwable

    ): super(tx){
        this.errorCodeIfs = errorCodeIfs
        this.errorDescription = errorCodeIfs.getDescription()
    }

    constructor(
        errorCodeIfs: ErrorCodeIfs,
        tx: Throwable,
        errorCodeDescription: String
    ): super(tx){

        this.errorCodeIfs = errorCodeIfs
        this.errorDescription = errorCodeDescription
    }

}