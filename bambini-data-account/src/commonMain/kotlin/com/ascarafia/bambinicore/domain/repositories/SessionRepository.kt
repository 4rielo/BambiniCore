package com.ascarafia.bambinicore.domain.repositories

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.EmptyResult
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import com.ascarafia.bambinicore.domain.model.Account
import com.ascarafia.bambinicore.domain.model.LoginResponse

interface SessionRepository {
    suspend fun login(email: String, password: String): Result<LoginResponse, BambiniError>
    suspend fun register(name: String, lastName: String, email: String, password: String): Result<Unit, BambiniError>
    suspend fun refreshToken(): Result<LoginResponse, BambiniError>
    suspend fun forgotPassword(email: String): Result<Unit, BambiniError>
    suspend fun changePassword(userId: String, oldPassword: String, newPassword: String): Result<Unit, BambiniError>
    suspend fun resetPassword(token: String, newPassword: String): Result<Unit, BambiniError>

    suspend fun fetchAccountInfo(): Result<Account, BambiniError>
    suspend fun logOut(): EmptyResult<BambiniError>
    suspend fun deleteAccount(email: String, userId: String): EmptyResult<BambiniError>
    suspend fun confirmDeleteAccount(token: String): EmptyResult<BambiniError>
    suspend fun verifyEmail(token: String): EmptyResult<BambiniError>
    suspend fun getTermsAndConditions(): Result<String, BambiniError>
    suspend fun getPrivacyPolicy(): Result<String, BambiniError>
    fun refreshTokenFailed()
}