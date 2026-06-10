package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.MedicationDto
import com.ascarafia.bambinicore.domain.model.Medication
import com.ascarafia.bambinicore.domain.use_cases.DateTimeUtils
import kotlin.time.Instant

fun MedicationDto.toMedication(): Medication {
    return Medication(
        id = id.orEmpty(),
        patientId = patientId.orEmpty(),
        name = name.orEmpty(),
        dose = dose,
        notes = notes,
        startDate = startDate?.let { DateTimeUtils.fromIsoString(it) },
        endDate = endDate?.let { DateTimeUtils.fromIsoString(it) }
    )
}

fun Medication.toMedicationDto(
    tenantId: String,
    createdAt: Instant? = null
): MedicationDto {
    return MedicationDto(
        id = id,
        tenantId = tenantId,
        patientId = patientId,
        name = name,
        dose = dose,
        notes = notes,
        startDate = startDate?.toString(),
        endDate = endDate?.toString(),
        createdAt = createdAt?.toString()
    )
}
