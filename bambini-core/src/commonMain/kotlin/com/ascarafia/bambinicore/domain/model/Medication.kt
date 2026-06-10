package com.ascarafia.bambinicore.domain.model

import kotlin.time.Instant

data class Medication(
    val id: String,
    val patientId: String,
    val name: String,
    val dose: String? = null,
    val notes: String? = null,
    val startDate: Instant? = null,
    val endDate: Instant? = null,
)
