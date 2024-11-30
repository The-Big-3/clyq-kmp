package org.big3.clyq.core.models.remote.entity

data class ParticipantsInfoDTO(
    val owner: UserDTO,
    val members: List<UserDTO>?,
    val totalNumber: Long
)