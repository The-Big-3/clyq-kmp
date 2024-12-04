package org.big3.clyq.core.models.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val email: String,
    val firstName: String,
    val lastName: String,
    val picture: String
)

@Serializable
data class UserServiceDTO(
    val numberOfJoinedGroups: Long?,
    val numberOfOwnedGroups: Long?,
    val numberOfJoinedEvents: Long?,
    val user: UserDTO
)