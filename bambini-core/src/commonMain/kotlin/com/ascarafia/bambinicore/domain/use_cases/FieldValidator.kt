package com.ascarafia.bambinicore.domain.use_cases

object FieldValidator {

    fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex(pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
        return emailRegex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordRegex = Regex(pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*(),.?\":{}|<>]).{8,}\$")
        return passwordRegex.matches(password)
    }
}