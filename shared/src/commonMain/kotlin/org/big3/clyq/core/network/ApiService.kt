package org.big3.clyq.core.network


import org.big3.clyq.core.models.local.entity.EventInfoItem
import org.big3.clyq.core.models.local.entity.GroupInfoItem
import org.big3.clyq.core.models.local.entity.UserItem
import org.big3.clyq.core.models.remote.entity.EventItemDTO
import org.big3.clyq.core.models.remote.entity.GroupItemDTO
import org.big3.clyq.core.models.remote.entity.UserServiceDTO

interface ApiService {

    suspend fun getUser():  RemoteResponse<UserServiceDTO>

    suspend fun getAllAvailableGroups():RemoteResponse<List<GroupItemDTO>>

    suspend fun getJoinedGroups():RemoteResponse<List<GroupItemDTO>>

    suspend fun getOwnedGroups(): RemoteResponse<List<GroupItemDTO>>

    suspend fun getJoinedEvents():RemoteResponse<List<EventItemDTO>>

    suspend fun getSelectedGroup(groupId:String): RemoteResponse<GroupItemDTO>

    suspend fun getSelectedEvent(eventId:String): RemoteResponse<EventItemDTO>

    suspend fun joinSelectedGroup(groupId: String,userInfo:String): RemoteResponse<GroupItemDTO>

    suspend fun leaveSelectedGroup(groupId: String, userInfo: String): RemoteResponse<GroupItemDTO>

    suspend fun joinSelectedEvent(eventId: String, userInfo: String): RemoteResponse<EventItemDTO>

    suspend fun cancelSelectedEvent(eventId: String, userInfo: String): RemoteResponse<EventItemDTO>

    suspend fun createNewGroup(groupInfo: GroupInfoItem, owner: UserItem):RemoteResponse<GroupItemDTO>

    suspend fun updateSelectedGroup(groupInfo: GroupInfoItem, groupId:String): RemoteResponse<GroupItemDTO>

    suspend fun createNewEvent(groupId: String, eventInfo: EventInfoItem): RemoteResponse<EventItemDTO>

    suspend fun updateSelectEvent(eventInfoData: EventInfoItem, eventId: String): RemoteResponse<EventItemDTO>

    suspend fun removeSelectEvent(eventId: String): RemoteResponse<String>

}