package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Consultation
import com.ascarafia.bambinicore.domain.model.error.BambiniError

interface ConsultationDataSource {
    suspend fun getConsultations(patientId: String): Result<List<Consultation>, BambiniError>
    suspend fun updateConsultation(patientId: String, consultation: Consultation): Result<Unit, BambiniError>
    suspend fun deleteConsultation(patientId: String, consultationId: String): Result<Unit, BambiniError>
}
