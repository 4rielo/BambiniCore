package com.ascarafia.bambini.data.mappers

import com.ascarafia.bambini.data.network.model.RegisterRequestDto
import com.ascarafia.bambini.domain.network.model.RegisterRequest

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