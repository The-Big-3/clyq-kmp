package org.big3.clyq.core.models.local.entity

data class ParticipantItem(
    val owner: UserItem = UserItem(),
    val members:List<UserItem> = emptyList(),
    val totalSize:Int = 0
)