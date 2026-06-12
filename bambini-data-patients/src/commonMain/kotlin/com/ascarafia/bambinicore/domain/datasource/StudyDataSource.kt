package com.ascarafia.bambinicore.domain.datasource

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.Study
import com.ascarafia.bambinicore.domain.model.error.BambiniError

interface StudyDataSource {
    suspend fun getStudies(patientId: String, accountId: String): Result<List<Study>, BambiniError>
    suspend fun updateStudy(patientId: String, accountId: String, study: Study): Result<Unit, BambiniError>
    suspend fun deleteStudy(patientId: String, accountId: String, studyId: String): Result<Unit, BambiniError>
}
