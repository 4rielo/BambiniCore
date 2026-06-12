package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Account
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import com.ascarafia.bambinicore.domain.model.LoginResponse
import com.ascarafia.bambinicore.domain.model.RegisterRequest

interface AuthDataSource {

    suspend fun login(email: String, password: String): Result<LoginResponse, BambiniError>
    suspend fun register(registerBody: RegisterRequest): Result<Unit, BambiniError>
    suspend fun refreshToken(refreshToken: String): Result<LoginResponse, BambiniError>
    suspend fun forgotPassword(email: String): Result<Unit, BambiniError>
    suspend fun changePassword(oldPassword: String, newPassword: String): Result<Unit, BambiniError>
    suspend fun resetPassword(token: String, newPassword: String): Result<Unit, BambiniError>
    suspend fun logout(): Result<Unit, BambiniError>
    suspend fun accountInfo(): Result<Account, BambiniError>
    suspend fun deleteAccount(): Result<Unit, BambiniError>
}