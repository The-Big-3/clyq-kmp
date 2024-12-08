package org.big3.clyq.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import kotlinx.serialization.json.Json
import org.big3.clyq.constants.Constants
import org.big3.clyq.constants.NetworkConstants
import org.big3.clyq.core.models.local.entity.EventInfoItem
import org.big3.clyq.core.models.local.entity.GroupInfoItem
import org.big3.clyq.core.models.local.entity.UserItem
import org.big3.clyq.core.models.remote.entity.EventItemDTO
import org.big3.clyq.core.models.remote.entity.GroupItemDTO
import org.big3.clyq.core.models.remote.entity.UserServiceDTO
import org.big3.clyq.interfaces.TokenPreferences

class ApiServiceImpl(
    private val client: NetworkClient,
    private val tokenPref: TokenPreferences,
) : BaseApiService(), ApiService {


    override suspend fun getUser(): RemoteResponse<UserServiceDTO> {

        val idToken = tokenPref.getIdToken()
        val refreshToken = tokenPref.getRefreshToken()

        return makeRequest(
            client = client.client,
            call = { httpClient ->
                httpClient.post(NetworkConstants.BASE_URL + NetworkConstants.USER_SERVICE + "Me") {
                    headers {
                        append(Constants.ID_TOKEN_KEY, idToken)
                        append(Constants.REFRESH_TOKEN_KEY, refreshToken)
                    }
                }
            },
            responseParser = { response ->
                Json.decodeFromString<UserServiceDTO>(response)
            }
        )
    }

    override suspend fun getAllAvailableGroups(): RemoteResponse<List<GroupItemDTO>> {
        TODO("Not yet implemented")
    }

    override suspend fun getJoinedGroups(): RemoteResponse<List<GroupItemDTO>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOwnedGroups(): RemoteResponse<List<GroupItemDTO>> {
        TODO("Not yet implemented")
    }

    override suspend fun getJoinedEvents(): RemoteResponse<List<EventItemDTO>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSelectedGroup(groupId: String): RemoteResponse<GroupItemDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getSelectedEvent(eventId: String): RemoteResponse<EventItemDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun joinSelectedGroup(
        groupId: String,
        userInfo: String
    ): RemoteResponse<GroupItemDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun leaveSelectedGroup(
        groupId: String,
        userInfo: String
    ): RemoteResponse<GroupItemDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun joinSelectedEvent(
        eventId: String,
        userInfo: String
    ): RemoteResponse<EventItemDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun cancelSelectedEvent(
        eventId: String,
        userInfo: String
    ): RemoteResponse<EventItemDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun createNewGroup(
        groupInfo: GroupInfoItem,
        owner: UserItem
    ): RemoteResponse<GroupItemDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun updateSelectedGroup(
        groupInfo: GroupInfoItem,
        groupId: String
    ): RemoteResponse<GroupItemDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun createNewEvent(
        groupId: String,
        eventInfo: EventInfoItem
    ): RemoteResponse<EventItemDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun updateSelectEvent(
        eventInfoData: EventInfoItem,
        eventId: String
    ): RemoteResponse<EventItemDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun removeSelectEvent(eventId: String): RemoteResponse<String> {
        TODO("Not yet implemented")
    }
}