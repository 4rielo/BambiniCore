package com.ascarafia.bambinicore.domain.extensions

import com.ascarafia.bambinicore.domain.model.Patient

fun MutableList<Patient>.addNotDuplicate(newPatient: Patient) {
    firstOrNull { it.id == newPatient.id } ?: add(newPatient)
}