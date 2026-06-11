package com.ascarafia.bambinicore.domain

import com.ascarafia.bambinicore.domain.model.Patient
import com.ascarafia.bambinicore.domain.use_cases.DateTimeUtils
import com.ascarafia.bambinicore.domain.use_cases.ListSortingUseCase
import kotlin.collections.emptyList
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Instant

class ListSortingUseCaseTest {

    private fun getPatientWith(id: String, lastUpdated: String): Patient {
        val lastUpdatedInstant = DateTimeUtils.fromIsoString(lastUpdated)
        return Patient(
            id = id,
            idNumber = "1",
            name = "Patient 1",
            lastName = "Last Name 1",
            updatedAt = lastUpdatedInstant ?: Instant.DISTANT_FUTURE,
            isDeleted = false,
            city = "",
            province = "",
            country = "",
            dateOfBirth = "",
            gender = "",
            allergies = emptyList(),
            medications = emptyList(),
            socialSecurity = "",
            socialSecurityNumber = ""
        )
    }

    @Test
    fun update_lists_for_remote_only() {
        val localList = listOf<Patient>(
            getPatientWith("1", "2023-08-01T00:00:00"),
        )
        val remoteList = listOf<Patient>()

        val (localListToUpdate, remoteListToUpdate) = ListSortingUseCase
            .getTasksToUpdate(
                localList,
                remoteList
            )
        assertTrue(localListToUpdate.isEmpty())
        assertEquals(1, remoteListToUpdate.size)
    }

    @Test
    fun update_lists_for_local_only() {
        val localList = listOf<Patient>()
        val remoteList = listOf<Patient>(
            getPatientWith("1", "2023-08-01T00:00:00"),
        )

        val (localListToUpdate, remoteListToUpdate) = ListSortingUseCase
            .getTasksToUpdate(
                localList,
                remoteList
            )
        assertEquals(1,localListToUpdate.size)
        assertTrue(remoteListToUpdate.isEmpty())
    }

    @Test
    fun update_lists_one_each_for_local_and_remote() {
        val localList = listOf<Patient>(
            getPatientWith("1", "2023-08-01T00:00:00"),
        )
        val remoteList = listOf<Patient>(
            getPatientWith("2", "2023-08-01T00:00:00"),
        )

        val (localListToUpdate, remoteListToUpdate) = ListSortingUseCase
            .getTasksToUpdate(
                localList,
                remoteList
            )
        assertEquals(1,localListToUpdate.size)
        assertEquals(1, remoteListToUpdate.size)
    }

    @Test
    fun update_lists_on_remote_only_when_local_is_newer() {
        val localList = listOf<Patient>(
            getPatientWith("1", "2023-08-02T00:00:00"),
        )
        val remoteList = listOf<Patient>(
            getPatientWith("1", "2023-08-01T00:00:00"),
        )

        val (localListToUpdate, remoteListToUpdate) = ListSortingUseCase
            .getTasksToUpdate(
                localList,
                remoteList
            )

        assertTrue(localListToUpdate.isEmpty())
        assertEquals(1, remoteListToUpdate.size)
    }

    @Test
    fun update_lists_on_local_only_when_remote_is_newer() {
        val localList = listOf<Patient>(
            getPatientWith("1", "2023-08-01T00:00:00"),
        )
        val remoteList = listOf<Patient>(
            getPatientWith("1", "2023-08-02T00:00:00"),
        )

        val (localListToUpdate, remoteListToUpdate) = ListSortingUseCase
            .getTasksToUpdate(
                localList,
                remoteList
            )

        assertEquals(1,localListToUpdate.size)
        assertTrue(remoteListToUpdate.isEmpty())
    }

    @Test
    fun update_lists_on_remote_only_when_local_is_newer_because_of_GMT() {
        val localList = listOf<Patient>(
            getPatientWith("1", "2023-01-02T22:35:01+01:00"),
        )
        val remoteList = listOf<Patient>(
            getPatientWith("1", "2023-01-02T22:35:01+02:00"),
        )

        val (localListToUpdate, remoteListToUpdate) = ListSortingUseCase
            .getTasksToUpdate(
                localList,
                remoteList
            )

        assertTrue(localListToUpdate.isEmpty())
        assertEquals(1, remoteListToUpdate.size)
    }

    @Test
    fun update_lists_when_remote_is_newer_but_local_contains_an_extra_Patient() {
        val localList = listOf<Patient>(
            getPatientWith("1", "2023-08-01T00:00:00"),
            getPatientWith("2", "2023-08-01T00:00:00"),
        )
        val remoteList = listOf<Patient>(
            getPatientWith("1", "2023-08-02T00:00:00"),
        )

        val (localListToUpdate, remoteListToUpdate) = ListSortingUseCase
            .getTasksToUpdate(
                localList,
                remoteList
            )

        assertEquals(1,localListToUpdate.size)
        assertEquals(1,remoteListToUpdate.size)
    }

    @Test
    fun update_lists_when_local_is_newer_but_remote_contains_an_extra_Patient() {
        val localList = listOf<Patient>(
            getPatientWith("1", "2023-08-02T00:00:00"),
        )
        val remoteList = listOf<Patient>(
            getPatientWith("1", "2023-08-01T00:00:00"),
            getPatientWith("2", "2023-08-01T00:00:00"),
        )

        val (localListToUpdate, remoteListToUpdate) = ListSortingUseCase
            .getTasksToUpdate(
                localList,
                remoteList
            )

        assertEquals(1,localListToUpdate.size)
        assertEquals(1,remoteListToUpdate.size)
    }

    @Test
    fun update_lists_when_local_is_newer_but_remote_contains_extra_Patients_large_list_sets() {
        val localList = listOf<Patient>(
            getPatientWith("1", "2023-08-02T00:00:00"),
            getPatientWith("2", "2023-08-02T00:00:00"),
            getPatientWith("3", "2023-08-02T00:00:00"),
            getPatientWith("4", "2023-08-02T00:00:00"),
            getPatientWith("5", "2023-08-02T00:00:00"),
            getPatientWith("6", "2023-08-02T00:00:00"),
            getPatientWith("7", "2023-08-02T00:00:00"),
            getPatientWith("8", "2023-08-02T00:00:00"),
            getPatientWith("9", "2023-08-02T00:00:00"),
            getPatientWith("10", "2023-08-02T00:00:00"),
        )
        val remoteList = listOf<Patient>(
            getPatientWith("1", "2023-08-01T00:00:00"),
            getPatientWith("2", "2023-08-01T00:00:00"),
            getPatientWith("3", "2023-08-01T00:00:00"),
            getPatientWith("4", "2023-08-01T00:00:00"),
            getPatientWith("5", "2023-08-01T00:00:00"),
            getPatientWith("6", "2023-08-01T00:00:00"),
            getPatientWith("7", "2023-08-01T00:00:00"),
            getPatientWith("8", "2023-08-01T00:00:00"),
            getPatientWith("9", "2023-08-01T00:00:00"),
            getPatientWith("10", "2023-08-01T00:00:00"),
            getPatientWith("11", "2023-08-01T00:00:00"),
            getPatientWith("12", "2023-08-01T00:00:00"),
            getPatientWith("13", "2023-08-01T00:00:00"),
            getPatientWith("14", "2023-08-01T00:00:00"),
            getPatientWith("15", "2023-08-01T00:00:00"),
        )

        val (localListToUpdate, remoteListToUpdate) = ListSortingUseCase
            .getTasksToUpdate(
                localList,
                remoteList
            )

        assertEquals(5,localListToUpdate.size)
        assertEquals(10,remoteListToUpdate.size)
    }
}
