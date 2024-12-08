package org.big3.clyq.core.models.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class EventDTO(
    val event: EventItemDTO
)

@Serializable
data class EventListDTO(
    val events: List<EventItemDTO>
)

@Serializable
data class EventItemDTO(
    val id: String,
    val info: EventInfoDTO,
    val collection: EventCollectionDTO?,
    val participantsInfo: ParticipantsInfoDTO,
    val createdAt: Long,
    val updatedAt: Long?
)
@Serializable
data class EventInfoDTO(
    val name: String?,
    val status: EventStatusDTO,
    val description: String?,
    val location: String?,
    val startAt: Long?,
    val endAt: Long?,
    val size: Long,
    val eventType: EventTypeDTO?,
    val interval: Long?
)

@Serializable
data class EventCollectionDTO(
    val collectionId: String?,
    val sequence: String?
)

enum class EventStatusDTO(val typeName: String) {
    UNSPECIFIED("unspecified"),
    DRAFT("draft"),
    PENDING("pending"),
    CONFIRMED("confirmed"),
    CANCELLED("cancelled"),
    RESCHEDULED("rescheduled"),
    COMPLETED("completed")
}

enum class EventTypeDTO(val typeName: String) {
    UNSPECIFIED("unspecified"),
    ONCE_OFF("onceOff"),
    REPEATABLE("repeatable")
}