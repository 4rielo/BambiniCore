package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.AllergyDto
import com.ascarafia.bambinicore.domain.model.Allergy

fun AllergyDto.toAllergy(): Allergy {
    return Allergy(
        id = id.orEmpty(),
        description = description.orEmpty()
    )
}

fun Allergy.toAllergyDto(
    tenantId: String,
    patientId: String,
    createdAt: String? = null
): AllergyDto {
    return AllergyDto(
        id = id,
        tenantId = tenantId,
        patientId = patientId,
        description = description,
        createdAt = createdAt
    )
}
