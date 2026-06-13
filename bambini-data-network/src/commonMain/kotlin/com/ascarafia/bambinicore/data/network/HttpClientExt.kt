package com.ascarafia.bambinicore.data.network

import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.model.error.BambiniError
import com.ascarafia.bambinicore.domain.model.error.DataError
import com.ascarafia.bambinicore.domain.model.error.ResponseError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
) : Result<T, BambiniError> {

    val response = try {
        execute()
    } catch(e: SocketTimeoutException) {
        return Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        return Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        return Result.Error(DataError.Remote.UNKNOWN)
    }

    return responseToResult(response)
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, BambiniError> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                //NOTE: this was commented out, because we're moving out of the DefaultResponse, but might be used again later
                //val defaultResponse = response.body<DefaultResponse<T>>()
                //defaultResponseToResult(defaultResponse)
                Result.Success(response.body())
            } catch(e: NoTransformationFoundException) {
                Result.Error(DataError.Remote.SERIALIZATION)
            }
        }
        else -> {
            val errorBody = try {
                response.body<ServerError>()
            } catch (e: Exception) {
                null
            }

            val error = when(response.status.value) {
                401 -> DataError.Remote.UNAUTHORIZED
                404 -> DataError.Remote.NOT_FOUND
                408 -> DataError.Remote.REQUEST_TIMEOUT
                429 -> DataError.Remote.TOO_MANY_REQUESTS
                in 500..599 -> DataError.Remote.SERVER
                else -> DataError.Remote.UNKNOWN
            }

            if (errorBody?.message != null) {
                Result.Error(errorBody)
            } else {
                Result.Error(error)
            }
        }
    }
}

inline fun <reified T> defaultResponseToResult(
    response: DefaultResponse<T>
): Result<T, BambiniError> {
    return when(response.responseCode) {
        0 -> {
            if(response.data != null) {
                Result.Success(response.data)
            } else {
                Result.Error(ResponseError(response.responseCode, response.responseMessage))
            }
        }
        else -> Result.Error(ResponseError(response.responseCode, response.responseMessage))
    }
}