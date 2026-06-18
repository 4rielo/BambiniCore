package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Study
import com.ascarafia.bambinicore.domain.model.error.BambiniError

interface StudyDataSource {
    suspend fun getStudies(patientId: String): Result<List<Study>, BambiniError>
    suspend fun updateStudy(patientId: String, study: Study): Result<Study, BambiniError>
    suspend fun deleteStudy(patientId: String, study: Study): Result<Unit, BambiniError>
}
