package com.ascarafia.bambinicore.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {
    @Upsert
    suspend fun upsert(log: PatientEntity)

    @Query("SELECT * FROM PatientEntity WHERE patientId = :patientId")
    suspend fun getPatient(patientId: String): PatientEntity?

    @Query("SELECT * FROM PatientEntity")
    suspend fun getPatientList(): List<PatientEntity>

    @Query("SELECT * FROM PatientEntity")
    fun getFlowPatientList(): Flow<List<PatientEntity>>

    @Query("SELECT * FROM PatientEntity WHERE idNumber = :id")
    suspend fun getPatientByDocumentNumber(id: String): PatientEntity?

    @Query("DELETE FROM PatientEntity WHERE idNumber = :id")
    suspend fun deletePatientByDocumentNumber(id: String)

    @Query("DELETE FROM PatientEntity")
    suspend fun deleteAllLocalPatients()
}