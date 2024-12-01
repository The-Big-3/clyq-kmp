package org.big3.clyq.interfaces

import kotlinx.coroutines.flow.Flow
import org.big3.clyq.core.models.local.entity.EventInfoItem
import org.big3.clyq.core.models.local.entity.EventItem
import org.big3.clyq.core.models.local.entity.GroupInfoItem
import org.big3.clyq.core.models.local.entity.GroupItem
import org.big3.clyq.core.models.local.entity.UserItem
import org.big3.clyq.core.models.local.entity.UserServiceItem
import org.big3.clyq.core.network.RemoteResponse

interface RemoteDataRepository {

    suspend fun fetchUser(): Flow<RemoteResponse<UserServiceItem>>

    suspend fun fetchAllAvailableGroups():Flow<RemoteResponse<List<GroupItem>>>

    suspend fun fetchJoinedGroups(): Flow<RemoteResponse<List<GroupItem>>>

    suspend fun fetchOwnedGroups(): Flow<RemoteResponse<List<GroupItem>>>

    suspend fun fetchJoinedEvents(): Flow<RemoteResponse<List<EventItem>>>

    suspend fun fetchSelectedGroup(groupId:String): Flow<RemoteResponse<GroupItem>>

    suspend fun fetchSelectedEvent(eventId:String): Flow<RemoteResponse<EventItem>>

    suspend fun joinSelectedGroup(groupId: String,userInfo:String): Flow<RemoteResponse<GroupItem>>

    suspend fun leaveSelectedGroup(groupId: String, userInfo: String): Flow<RemoteResponse<GroupItem>>

    suspend fun joinSelectedEvent(eventId: String, userInfo: String): Flow<RemoteResponse<EventItem>>

    suspend fun cancelSelectedEvent(eventId: String, userInfo: String): Flow<RemoteResponse<EventItem>>

    suspend fun createNewGroup(groupInfo: GroupInfoItem, owner: UserItem): Flow<RemoteResponse<GroupItem>>

    suspend fun updateSelectedGroup(groupInfo: GroupInfoItem, groupId:String): Flow<RemoteResponse<GroupItem>>

    suspend fun createNewEvent(groupId: String, eventInfo: EventInfoItem): Flow<RemoteResponse<EventItem>>

    suspend fun updateSelectEvent(eventInfoData: EventInfoItem, eventId: String): Flow<RemoteResponse<EventItem>>

    suspend fun removeSelectEvent(eventId: String): Flow<RemoteResponse<String>>

}