package com.ascarafia.bambinicore.data.network.http_util

import com.ascarafia.bambinicore.data.network.dto.PatientDto

object TestResponses {

    val emptyListResponse = emptyList<PatientDto>()

    val twoPatientsListResponse = listOf(
        PatientDto(
            patientId = "69316331316628d3d6b127d8.70047535",
            name = "Jorge",
            lastName = "Pérez"
        ),
        PatientDto(
            patientId = "69316331316628d3d6b127d8.70047532",
            name = "Pedro",
            lastName = "López"
        )
    )
}