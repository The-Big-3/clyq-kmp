package org.big3.clyq.core.models.local.entity


data class UserServiceItem(
    val numberOfJoinedGroups: Int = 0,
    val numberOfOwnedGroups: Int = 0,
    val numberOfJoinedEvents: Int = 0,
    val user: UserItem
)

data class UserItem(
    val email:String = "",
    val firstName:String = "",
    val lastName:String = "",
    val imageUrl:String = ""
)