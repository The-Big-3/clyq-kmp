package org.big3.clyq.core.entity


data class GroupItem(
    val id:String = "",
    val info:GroupInfoItem,
    val events:List<EventItem> = emptyList(),
    val participants:ParticipantItem = ParticipantItem(),
    val createdAt:Long = 0L,
    val updatedAt:Long? = 0L
)


data class GroupInfoItem(
    val name:String = "",
    val description:String = "",
    val cultureAndValues:String = ""
)