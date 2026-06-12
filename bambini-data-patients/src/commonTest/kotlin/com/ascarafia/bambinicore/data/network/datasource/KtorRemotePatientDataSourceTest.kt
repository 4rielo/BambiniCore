package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.network.HttpClientFactory
import com.ascarafia.bambinicore.data.network.http_util.TestResponses
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.domain.Environment
import com.ascarafia.bambinicore.domain.model.Result
import com.ascarafia.bambinicore.domain.network.TokenProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class KtorRemotePatientDataSourceTest {

    private lateinit var remotePatientDataSource: KtorRemotePatientDataSource
    private lateinit var httpClient: HttpClient
    private lateinit var config: BambiniRemoteConfig

    @BeforeTest
    fun setUp() {
        config = BambiniRemoteConfig(
            baseUrls = mapOf(Environment.DEV to "http://localhost:8080"),
            Environment.DEV
        )

        httpClient = HttpClientFactory
            .create(
                engine = MockEngine { request ->
                        val relativeUrl = request.url.encodedPath
                        when (relativeUrl) {
                            "/api/patients" -> {
                                respond(
                                    content = Json.encodeToString(
                                        TestResponses.twoPatientsListResponse
                                    ),
                                    status = HttpStatusCode.OK,
                                    headers = headersOf(
                                        "Content-Type", "application/json"
                                    )
                                )
                            }
                            else -> {
                                respondError(
                                    status = HttpStatusCode.Forbidden
                                )
                            }
                        }
                },
                config = config,
                tokenProvider = object : TokenProvider {
                    override suspend fun getAccessToken(): String? = "token"
                    override suspend fun getRefreshToken(): String? = "refreshToken"
                    override suspend fun saveTokens(accessToken: String, refreshToken: String) {}
                    override suspend fun clearTokens() {}
                }
            )

        remotePatientDataSource = KtorRemotePatientDataSource(
            httpClient,
            config
        )
    }

    @Test
    fun `Patient list is correctly returned by API call`() = runBlocking {
        val response = remotePatientDataSource.getPatients()
        val patientList = when(response) {
            is Result.Success -> {
                response.data
            }
            is Result.Error<*> -> {
                throw Exception("Error getting patients")
            }
        }

        assertTrue (patientList.isNotEmpty())
    }

}
