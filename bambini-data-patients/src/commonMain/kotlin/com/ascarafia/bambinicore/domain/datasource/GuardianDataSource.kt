package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Guardian
import com.ascarafia.bambinicore.domain.model.error.BambiniError

interface GuardianDataSource {
    suspend fun getGuardians(patientId: String, accountId: String): Result<List<Guardian>, BambiniError>
    suspend fun updateGuardian(patientId: String, accountId: String, guardian: Guardian): Result<Unit, BambiniError>
    suspend fun deleteGuardian(patientId: String, accountId: String, guardianId: String): Result<Unit, BambiniError>
}
