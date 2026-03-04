package com.ascarafia.bambinicore.domain.model

data class Account(
    val id: String,
    val email: String,
    val name: String,
    val lastName: String,
    val displayName: String,
    val photoUrl: String?,
    val lastUpdated: String,
    val patientsLimit: Int,
    val paymentStatus: String,
    val accountType: String,
)
