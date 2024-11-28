package org.big3.clyq.core.datasource.remote

data class GroupDTO(
    val group: GroupItemDTO
)

data class GroupListDTO(
    val groups: List<GroupItemDTO>
)

data class GroupItemDTO(
    val id: String,
    val info: GroupInfoDTO,
    val events: List<EventItemDTO>?,
    val participantsInfo: ParticipantsInfoDTO,
    val createdAt: Long,
    val updatedAt: Long?
)

data class GroupInfoDTO(
    val name: String?,
    val description: String?,
    val cultureAndValues: String?
)