package com.ascarafia.bambini.domain.network.model

import com.ascarafia.bambinicore.domain.model.error.BambiniError

data class ServerError(val errorMessages: List<String>): BambiniError