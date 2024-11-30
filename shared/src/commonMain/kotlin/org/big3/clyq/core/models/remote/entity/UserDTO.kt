package org.big3.clyq.core.models.remote.entity

data class UserDTO(
    val email: String,
    val firstName: String,
    val lastName: String,
    val picture: String
)

data class UserServiceDTO(
    val numberOfJoinedGroups: Long?,
    val numberOfOwnedGroups: Long?,
    val numberOfJoinedEvents: Long?,
    val user: UserDTO
)