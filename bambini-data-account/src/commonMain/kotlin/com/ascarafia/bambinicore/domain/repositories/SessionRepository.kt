package com.ascarafia.bambini.domain.repositories

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.EmptyResult
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import com.ascarafia.bambinicore.domain.model.Account
import com.ascarafia.bambini.domain.network.model.LoginResponse

interface SessionRepository {
    suspend fun login(email: String, password: String): Result<LoginResponse, BambiniError>
    suspend fun register(name: String, lastName: String, email: String, password: String): Result<LoginResponse, BambiniError>
    suspend fun refreshToken(): Result<LoginResponse, BambiniError>
    suspend fun forgotPassword(email: String): Result<Unit, BambiniError>
    suspend fun changePassword(oldPassword: String, newPassword: String): Result<Unit, BambiniError>
    suspend fun resetPassword(token: String, newPassword: String): Result<Unit, BambiniError>

    suspend fun fetchAccountInfo(): Result<Account, BambiniError>
    suspend fun logOut(): EmptyResult<BambiniError>
    suspend fun deleteAccount(): EmptyResult<BambiniError>
    fun refreshTokenFailed()
}