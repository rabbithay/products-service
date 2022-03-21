package com.thay.exceptions

import io.grpc.Status

abstract class BaseBusinessException: RuntimeException() {
    abstract fun errorMessage(): String
    abstract fun statusCode(): Status.Code
}