package com.ascarafia.bambinicore.data.mappers

import com.ascarafia.bambinicore.data.network.dto.AllergyDto
import com.ascarafia.bambinicore.domain.model.Allergy

fun AllergyDto.toAllergy(): Allergy {
    return Allergy(
        id = id.orEmpty(),
        patientId = patientId.orEmpty(),
        description = description.orEmpty()
    )
}

fun Allergy.toAllergyDto(): AllergyDto {
    return AllergyDto(
        id = id,
        patientId = patientId,
        description = description,
    )
}
