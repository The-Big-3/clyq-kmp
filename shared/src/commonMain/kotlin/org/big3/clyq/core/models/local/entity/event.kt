package org.big3.clyq.core.models.local.entity


data class EventItem(
    val id:String = "",
    val info: EventInfoItem,
    val collection: EventCollectionItem,
    val participantEntity: ParticipantItem = ParticipantItem(),
    val createdAt:Long = 0L,
    val updatedAt:Long = 0L
)


data class EventInfoItem(
    val name:String = "",
    val status:String = "CONFIRMED",
    val description:String = "",
    val location:String = "online",
    val startAt:Long = 0L,
    val endAt:Long = 0L,
    val size:Int = 0,
    val eventType:String = "",
    val interval:Long = 0L,
)

data class EventCollectionItem(
    val collectionId:String = "",
    val sequence:String = ""
)