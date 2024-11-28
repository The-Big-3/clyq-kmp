package org.big3.clyq.core.datasource.remote

data class ParticipantsInfoDTO(
    val owner: UserDTO,
    val members: List<UserDTO>?,
    val totalNumber: Long
)