package com.ascarafia.bambinicore.data.util

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object AuthEventHandler {
    private val _unauthorizedEvents = Channel<Unit>()
    val unauthorizedEvents = _unauthorizedEvents.receiveAsFlow()

    fun onUnauthorized() {
        _unauthorizedEvents.trySend(Unit)
    }
}
