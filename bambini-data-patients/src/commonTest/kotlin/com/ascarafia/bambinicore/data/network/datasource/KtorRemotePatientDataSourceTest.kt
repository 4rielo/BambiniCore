package com.ascarafia.bambinicore.data.network.datasource

import com.ascarafia.bambinicore.data.HttpClientFactory
import com.ascarafia.bambinicore.data.network.http_util.TestResponses
import com.ascarafia.bambinicore.domain.BambiniRemoteConfig
import com.ascarafia.bambinicore.domain.Environment
import com.ascarafia.bambinicore.domain.datasource.AccountDataSource
import com.ascarafia.bambinicore.domain.model.Result
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class KtorRemotePatientDataSourceTest {

    private lateinit var remotePatientDataSource: KtorRemotePatientDataSource
    private lateinit var httpClient: HttpClient
    private lateinit var config: BambiniRemoteConfig
    private lateinit var accountDataSource: AccountDataSource

    @BeforeTest
    fun setUp() {
        accountDataSource = object: AccountDataSource {
            var localToken: String? = null
            var localRefreshToken: String? = null
            var localLastUpdate: String? = null
            var localAccountId: String? = "1"

            override fun saveToken(token: String) {
                localToken = token
            }
            override fun getToken(): String? {
                return localToken
            }
            override fun saveRefreshToken(refreshToken: String) {
                localRefreshToken = refreshToken
            }
            override fun getRefreshToken(): String? {
                return localRefreshToken
            }
            override fun saveLastUpdate(lastUpdate: String) {
                localLastUpdate = lastUpdate
            }
            override fun getLastUpdate(): String? {
                return localLastUpdate
            }
            override fun saveAccountId(accountId: String) {
                localAccountId = accountId
            }
            override fun getAccountId(): String? {
                return localAccountId
            }
        }

        config = BambiniRemoteConfig(
            baseUrls = mapOf(Environment.DEV to "http://localhost:8080"),
            Environment.DEV
        )

        httpClient = HttpClientFactory
            .create(
                MockEngine.create {
                    addHandler { request ->
                        val relativeUrl = request.url.encodedPath
                        when (relativeUrl) {
                            "/api/clients/1/patients" -> {
                                respond(
                                    content = Json.encodeToString(
                                        TestResponses.twoPatientsListResponse
                                    ),
                                    status = HttpStatusCode.OK,
                                    headers = headersOf(
                                        "Content-Type" , "application/json"
                                    )
                                )
                            }
                            "/api/clients/2/patients" -> {
                                respond(
                                    content = Json.encodeToString(
                                        TestResponses.emptyListResponse
                                    ),
                                    status = HttpStatusCode.OK,
                                    headers = headersOf(
                                        "Content-Type" , "application/json"
                                    )
                                )
                            }
                            else -> {
                                respondError(
                                    status = HttpStatusCode.Forbidden
                                )
                            }
                        }
                    }
                }
            )

        remotePatientDataSource = KtorRemotePatientDataSource(
            httpClient,
            config,
            accountDataSource
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

    @Test
    fun `Empty patient list is correctly returned by API call`() = runBlocking {
        accountDataSource.saveAccountId("2")
        val response = remotePatientDataSource.getPatients()
        val patientList = when(response) {
            is Result.Success -> {
                response.data
            }
            is Result.Error<*> -> {
                throw Exception("Error getting patients")
            }
        }

        assertTrue (patientList.isEmpty())
    }

}