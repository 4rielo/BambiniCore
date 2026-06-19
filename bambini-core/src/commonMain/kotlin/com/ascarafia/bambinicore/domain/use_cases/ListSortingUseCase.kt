package com.ascarafia.bambinicore.domain.use_cases

import com.ascarafia.bambinicore.domain.extensions.addNotDuplicate
import com.ascarafia.bambinicore.domain.model.Patient

object ListSortingUseCase {

    enum class AddToList {
        LOCAL,
        LOCAL_UPDATING_TIME,
        REMOTE,
        NONE
    }
    private fun addToListBasedOnLastUpdated(localItem: Patient, remoteItem: Patient): AddToList {
        val localInstant = localItem.updatedAt
        val remoteInstant = remoteItem.updatedAt

        val lastUpdatedDifference = localInstant.compareTo(remoteInstant)
        return when {
            lastUpdatedDifference == 0 -> AddToList.NONE
            lastUpdatedDifference > 0 -> AddToList.REMOTE
            else -> AddToList.LOCAL
        }
    }

    fun getTasksToUpdate(localList: List<Patient>, remoteList: List<Patient>): Pair<List<Patient>, List<Patient>> {
        val remoteListToUpdate = mutableListOf<Patient>()
        val localListToUpdate = mutableListOf<Patient>()

        for (localItem in localList) {
            val matchingRemoteItem = remoteList.firstOrNull { it.id == localItem.id }
            matchingRemoteItem?.let {
                when( addToListBasedOnLastUpdated(localItem, matchingRemoteItem) ) {
                    AddToList.LOCAL -> localListToUpdate.addNotDuplicate(matchingRemoteItem)
                    AddToList.REMOTE -> remoteListToUpdate.addNotDuplicate(localItem)
                    AddToList.LOCAL_UPDATING_TIME -> {
                        val localLastUpdated = DateTimeUtils.getCurrentInstant()
                        localListToUpdate.addNotDuplicate( matchingRemoteItem.copy(updatedAt = localLastUpdated ) )
                    }
                    AddToList.NONE -> Unit
                }

            } ?: run {
                remoteListToUpdate.addNotDuplicate(localItem)
            }
        }

        for (remoteItem in remoteList) {
            val matchingLocalItem = localList.firstOrNull { it.id == remoteItem.id }
            matchingLocalItem?.let {
                when( addToListBasedOnLastUpdated(matchingLocalItem, remoteItem) ) {
                    AddToList.LOCAL -> localListToUpdate.addNotDuplicate(remoteItem)
                    AddToList.REMOTE -> remoteListToUpdate.addNotDuplicate(matchingLocalItem)
                    AddToList.LOCAL_UPDATING_TIME -> {
                        val localLastUpdated = DateTimeUtils.getCurrentInstant()
                        localListToUpdate.addNotDuplicate( remoteItem.copy(updatedAt = localLastUpdated ) )
                    }
                    AddToList.NONE -> Unit
                }

            } ?: run {
                localListToUpdate.addNotDuplicate(remoteItem)
            }
        }

        return Pair( localListToUpdate.toList(), remoteListToUpdate.toList())
    }
}