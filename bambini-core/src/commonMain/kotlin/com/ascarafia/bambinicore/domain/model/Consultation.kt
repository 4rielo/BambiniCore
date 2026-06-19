package com.ascarafia.bambinicore.domain.model

import kotlin.time.Instant

data class Consultation(
    val id: String,
    val date: Instant,
    val patientId: String,
    val reason: String,
    val diagnosis: String? = null,
    val treatment: String? = null,
    val notes: String? = null,
)
