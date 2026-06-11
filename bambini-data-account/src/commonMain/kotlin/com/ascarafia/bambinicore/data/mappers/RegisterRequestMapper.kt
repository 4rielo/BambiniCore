package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.RegisterRequestDto
import com.ascarafia.bambinicore.domain.model.RegisterRequest

fun RegisterRequest.toRegisterRequestDto(): RegisterRequestDto {
    return RegisterRequestDto(
        name = name,
        lastName = lastName,
        email = email,
        password = password,
    )
}

fun RegisterRequestDto.toRegisterRequest(): RegisterRequest {
    return RegisterRequest(
        name = name,
        lastName = lastName,
        email = email,
        password = password,
    )
}