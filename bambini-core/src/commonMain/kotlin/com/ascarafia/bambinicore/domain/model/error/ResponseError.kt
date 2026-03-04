package com.ascarafia.bambinicore.domain.model.error

data class ResponseError(
    val responseCode: Int?,
    val responseMessage: String?,
): BambiniError
