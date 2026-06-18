package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Guardian
import com.ascarafia.bambinicore.domain.model.error.BambiniError

interface GuardianDataSource {
    suspend fun getGuardians(patientId: String): Result<List<Guardian>, BambiniError>
    suspend fun updateGuardian(patientId: String, guardian: Guardian): Result<Guardian, BambiniError>
    suspend fun deleteGuardian(patientId: String, guardian: Guardian): Result<Unit, BambiniError>
}
