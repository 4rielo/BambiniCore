package com.ascarafia.bambinicore.domain.use_cases

import kotlin.time.Clock
import kotlin.time.Instant

object DateTimeUtils {
    fun getCurrentDateTime(): Instant {
        return Clock.System.now()
    }

    fun getCurrentDateTimeString(): String {
        val date = getCurrentDateTime()
        return toIsoString(date)
    }
    fun toIsoString(dateTime: Instant): String {
        return dateTime.toString()
    }

    fun fromIsoString(dateString: String): Instant? {
        var dateString = dateString
        val validationSubstring = dateString.takeLast(6)
        if(!validationSubstring.contains("Z") && !validationSubstring.contains("+") && !validationSubstring.contains("-")) {
            dateString += "Z"
        }
        return try {
            Instant.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }
}