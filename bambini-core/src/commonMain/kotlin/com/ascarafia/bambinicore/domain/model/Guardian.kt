package com.ascarafia.bambinicore.domain.model

data class Guardian(
    val id: String,
    val patientId: String,
    val name: String,
    val lastName: String,
    val relationship: String,
    val phoneNumber: String,
    val email: String? = null,
)
