package org.big3.clyq.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.big3.clyq.core.models.DomainDataMapper
import org.big3.clyq.core.models.DomainDataMapperImpl
import org.big3.clyq.core.models.local.entity.EventInfoItem
import org.big3.clyq.core.models.local.entity.EventItem
import org.big3.clyq.core.models.local.entity.GroupInfoItem
import org.big3.clyq.core.models.local.entity.GroupItem
import org.big3.clyq.core.models.local.entity.UserItem
import org.big3.clyq.core.models.local.entity.UserServiceItem
import org.big3.clyq.core.network.ApiService
import org.big3.clyq.core.network.RemoteResponse
import org.big3.clyq.core.network.RemoteResponseWrapper
import org.big3.clyq.interfaces.RemoteDataRepository
import javax.inject.Inject

class RemoteDataRepository @Inject constructor(
    val apiService: ApiService,
    val dataMapper: DomainDataMapper,
):RemoteDataRepository {

    override suspend fun fetchUser(): Flow<RemoteResponse<UserServiceItem>> {
        return flow {
            val domainResponse = when (val response = apiService.getUser()) {
                is RemoteResponseWrapper.Success -> {
                    val userDomain = dataMapper.mapToUserServiceDomain(response.body)
                    RemoteResponseWrapper.createSuccess(userDomain)
                }
                is RemoteResponseWrapper.Error -> response
            }

            emit(domainResponse)
        }.catch { e ->
            emit(RemoteResponseWrapper.createError())
        }
    }

    override suspend fun fetchAllAvailableGroups(): Flow<RemoteResponse<List<GroupItem>>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchJoinedGroups(): Flow<RemoteResponse<List<GroupItem>>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchOwnedGroups(): Flow<RemoteResponse<List<GroupItem>>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchJoinedEvents(): Flow<RemoteResponse<List<EventItem>>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchSelectedGroup(groupId: String): Flow<RemoteResponse<GroupItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchSelectedEvent(eventId: String): Flow<RemoteResponse<EventItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun joinSelectedGroup(
        groupId: String,
        userInfo: String
    ): Flow<RemoteResponse<GroupItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun leaveSelectedGroup(
        groupId: String,
        userInfo: String
    ): Flow<RemoteResponse<GroupItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun joinSelectedEvent(
        eventId: String,
        userInfo: String
    ): Flow<RemoteResponse<EventItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun cancelSelectedEvent(
        eventId: String,
        userInfo: String
    ): Flow<RemoteResponse<EventItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun createNewGroup(
        groupInfo: GroupInfoItem,
        owner: UserItem
    ): Flow<RemoteResponse<GroupItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateSelectedGroup(
        groupInfo: GroupInfoItem,
        groupId: String
    ): Flow<RemoteResponse<GroupItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun createNewEvent(
        groupId: String,
        eventInfo: EventInfoItem
    ): Flow<RemoteResponse<EventItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateSelectEvent(
        eventInfoData: EventInfoItem,
        eventId: String
    ): Flow<RemoteResponse<EventItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeSelectEvent(eventId: String): Flow<RemoteResponse<String>> {
        TODO("Not yet implemented")
    }
}