package com.ascarafia.bambini.data.mappers

import com.ascarafia.bambini.data.network.model.LoginResponseDto
import com.ascarafia.bambini.domain.network.model.LoginResponse

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