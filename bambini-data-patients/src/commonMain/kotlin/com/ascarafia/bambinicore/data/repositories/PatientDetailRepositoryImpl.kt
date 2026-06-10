package com.ascarafia.bambinicore.data.repositories

import com.ascarafia.bambinicore.domain.datasource.*
import com.ascarafia.bambinicore.domain.model.*
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import com.ascarafia.bambinicore.domain.repositories.PatientDetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class PatientDetailRepositoryImpl(
    private val allergyDataSource: AllergyDataSource,
    private val consultationDataSource: ConsultationDataSource,
    private val guardianDataSource: GuardianDataSource,
    private val measurementDataSource: MeasurementDataSource,
    private val medicationDataSource: MedicationDataSource,
    private val studyDataSource: StudyDataSource,
    private val repositoryDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PatientDetailRepository {

    override suspend fun getPatientAllergies(patientId: String): Result<List<Allergy>, BambiniError> {
        return withContext(repositoryDispatcher) {
            allergyDataSource.getAllergies(patientId)
        }
    }

    override suspend fun upsertPatientAllergy(patientId: String, allergy: Allergy): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
            allergyDataSource.updateAllergy(patientId, allergy)
        }
    }

    override suspend fun deletePatientAllergy(patientId: String, allergy: Allergy): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
            allergyDataSource.deleteAllergy(patientId, allergy.id)
        }
    }

    override suspend fun getPatientConsultations(patientId: String): Result<List<Consultation>, BambiniError> {
        return withContext(repositoryDispatcher) {
            consultationDataSource.getConsultations(patientId)
        }
    }

    override suspend fun upsertPatientConsultation(patientId: String, consultation: Consultation): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
            consultationDataSource.updateConsultation(patientId, consultation)
        }
    }

    override suspend fun deletePatientConsultation(patientId: String, consultation: Consultation): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
            consultationDataSource.deleteConsultation(patientId, consultation.id)
        }
    }

    override suspend fun getPatientGuardians(patientId: String): Result<List<Guardian>, BambiniError> {
        return withContext(repositoryDispatcher) {
            guardianDataSource.getGuardians(patientId)
        }
    }

    override suspend fun upsertPatientGuardian(patientId: String, guardian: Guardian): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
            guardianDataSource.updateGuardian(patientId, guardian)
        }
    }

    override suspend fun deletePatientGuardian(patientId: String, guardian: Guardian): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
            guardianDataSource.deleteGuardian(patientId, guardian.id)
        }
    }

    override suspend fun getPatientMeasurements(patientId: String): Result<List<Measurement>, BambiniError> {
        return withContext(repositoryDispatcher) {
            measurementDataSource.getMeasurements(patientId)
        }
    }

    override suspend fun upsertPatientMeasurement(patientId: String, measurement: Measurement): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
            measurementDataSource.updateMeasurement(patientId, measurement)
        }
    }

    override suspend fun deletePatientMeasurement(patientId: String, measurement: Measurement): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
            measurementDataSource.deleteMeasurement(patientId, measurement.id)
        }
    }

    override suspend fun getPatientMedications(patientId: String): Result<List<Medication>, BambiniError> {
        return withContext(repositoryDispatcher) {
            medicationDataSource.getMedications(patientId)
        }
    }

    override suspend fun upsertPatientMedication(patientId: String, medication: Medication): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
            medicationDataSource.updateMedication(patientId, medication)
        }
    }

    override suspend fun deletePatientMedication(patientId: String, medication: Medication): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
            medicationDataSource.deleteMedication(patientId, medication.id)
        }
    }

    override suspend fun getPatientStudies(patientId: String): Result<List<Study>, BambiniError> {
        return withContext(repositoryDispatcher) {
            studyDataSource.getStudies(patientId)
        }
    }

    override suspend fun upsertPatientStudy(patientId: String, study: Study): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
            studyDataSource.updateStudy(patientId, study)
        }
    }

    override suspend fun deletePatientStudy(patientId: String, study: Study): Result<Unit, BambiniError> {
        return withContext(repositoryDispatcher) {
            studyDataSource.deleteStudy(patientId, study.id)
        }
    }
}
