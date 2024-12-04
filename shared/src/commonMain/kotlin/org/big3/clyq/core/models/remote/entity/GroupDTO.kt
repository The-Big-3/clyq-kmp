package org.big3.clyq.core.models.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class GroupDTO(
    val group: GroupItemDTO
)

@Serializable
data class GroupListDTO(
    val groups: List<GroupItemDTO>
)

@Serializable
data class GroupItemDTO(
    val id: String,
    val info: GroupInfoDTO,
    val events: List<EventItemDTO>?,
    val participantsInfo: ParticipantsInfoDTO,
    val createdAt: Long,
    val updatedAt: Long?
)

@Serializable
data class GroupInfoDTO(
    val name: String?,
    val description: String?,
    val cultureAndValues: String?
)