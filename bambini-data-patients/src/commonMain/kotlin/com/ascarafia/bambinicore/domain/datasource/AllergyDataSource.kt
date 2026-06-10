package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Allergy
import com.ascarafia.bambinicore.domain.model.error.BambiniError

interface AllergyDataSource {
    suspend fun getAllergies(patientId: String): Result<List<Allergy>, BambiniError>
    suspend fun updateAllergy(patientId: String, allergy: Allergy): Result<Unit, BambiniError>
    suspend fun deleteAllergy(patientId: String, allergyId: String): Result<Unit, BambiniError>
}
