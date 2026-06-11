package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.LoginResponseDto
import com.ascarafia.bambinicore.domain.model.LoginResponse

fun LoginResponse.toLoginResponseDto(): LoginResponseDto {
    return LoginResponseDto(
        token = token,
        refreshToken = refreshToken,
    )
}

fun LoginResponseDto.toLoginResponse(): LoginResponse {
    return LoginResponse(
        token = token.orEmpty(),
        refreshToken = refreshToken.orEmpty(),
    )
}