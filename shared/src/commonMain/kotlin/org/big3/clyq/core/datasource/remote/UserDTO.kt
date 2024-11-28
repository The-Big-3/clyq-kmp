package org.big3.clyq.core.datasource.remote

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