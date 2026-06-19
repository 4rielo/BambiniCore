package com.ascarafia.bambinicore.domain.model

import kotlin.time.Instant

data class Study(
    val id: String,
    val patientId: String,
    val type: String,
    val description: String? = null,
    val result: String? = null,
    val date: Instant,
    val attachments: List<String> = emptyList(),
)
