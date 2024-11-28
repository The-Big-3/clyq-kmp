package org.big3.clyq.core.datasource

import org.big3.clyq.core.datasource.remote.EventCollectionDTO
import org.big3.clyq.core.datasource.remote.EventDTO
import org.big3.clyq.core.datasource.remote.EventInfoDTO
import org.big3.clyq.core.datasource.remote.EventItemDTO
import org.big3.clyq.core.datasource.remote.EventListDTO
import org.big3.clyq.core.datasource.remote.GroupDTO
import org.big3.clyq.core.datasource.remote.GroupInfoDTO
import org.big3.clyq.core.datasource.remote.GroupListDTO
import org.big3.clyq.core.datasource.remote.ParticipantsInfoDTO
import org.big3.clyq.core.datasource.remote.UserDTO
import org.big3.clyq.core.datasource.remote.UserServiceDTO
import org.big3.clyq.core.entity.EventCollectionItem
import org.big3.clyq.core.entity.EventInfoItem
import org.big3.clyq.core.entity.EventItem
import org.big3.clyq.core.entity.GroupInfoItem
import org.big3.clyq.core.entity.GroupItem
import org.big3.clyq.core.entity.ParticipantItem
import org.big3.clyq.core.entity.UserItem
import org.big3.clyq.core.entity.UserServiceItem

class DomainDataMapperImpl : DomainDataMapper {
    override fun mapToUserDomain(userDTO: UserServiceDTO): UserServiceItem {
        return UserServiceItem(
            numberOfOwnedGroups = (userDTO.numberOfOwnedGroups ?: 0).toInt(),
            numberOfJoinedGroups = (userDTO.numberOfJoinedGroups ?: 0).toInt(),
            numberOfJoinedEvents = (userDTO.numberOfJoinedEvents ?: 0).toInt(),
            user = mapToUserDomain(userDTO.user)
        )
    }

    override fun mapToUserDomain(userDTO: UserDTO): UserItem {
        return UserItem(
            email = userDTO.email,
            firstName = userDTO.firstName ?: "",
            lastName = userDTO.lastName ?: "",
            imageUrl = userDTO.picture ?: ""
        )
    }

    override fun mapToUserListDomain(userDTOList: List<UserDTO>?): List<UserItem> {
        return userDTOList?.map { userDTO ->
            mapToUserDomain(userDTO)
        } ?: emptyList()
    }

    override fun mapToGroupListDomain(groupListDTO: GroupListDTO?): List<GroupItem> {
        return groupListDTO?.groups?.map { groupItemDTO ->
            GroupItem(
                id = groupItemDTO.id,
                info = mapGroupInfoDomain(groupItemDTO.info),
                events = groupItemDTO.events?.map { mapToEventItemDomain(it) } ?: emptyList(),
                participants = mapToParticipantDomain(groupItemDTO.participantsInfo),
                createdAt = groupItemDTO.createdAt,
                updatedAt = groupItemDTO.updatedAt
            )
        } ?: emptyList()
    }

    override fun mapGroupInfoDomain(groupInfoDTO: GroupInfoDTO): GroupInfoItem {
        return GroupInfoItem(
            name = groupInfoDTO.name ?: "",
            description = groupInfoDTO.description ?: "",
            cultureAndValues = groupInfoDTO.cultureAndValues ?: ""
        )
    }

    override fun mapToGroupItemDomain(groupDTO: GroupDTO): GroupItem {
        return GroupItem(
            id = groupDTO.group.id,
            info = mapGroupInfoDomain(groupDTO.group.info),
            events = if (groupDTO.group.events == null) {
                emptyList()
            } else {
                groupDTO.group.events.map { eventItemDTO ->
                    mapToEventItemDomain(eventItemDTO)
                }
            },
            participants = mapToParticipantDomain(groupDTO.group.participantsInfo),
            createdAt = groupDTO.group.createdAt,
            updatedAt = groupDTO.group.updatedAt
        )
    }

    override fun mapToEventListDomain(eventDTOList: EventListDTO?): List<EventItem> {
        return eventDTOList?.events?.map { eventItemDTO ->
            EventItem(
                id = eventItemDTO.id,
                info = mapToEventInfoDomain(eventItemDTO.info),
                collection = mapToEventCollectionDomain(eventItemDTO.collection),
                participantEntity = mapToParticipantDomain(eventItemDTO.participantsInfo),
                createdAt = eventItemDTO.createdAt,
                updatedAt = eventItemDTO.updatedAt ?: 0L
            )
        } ?: emptyList()
    }

    override fun mapToEventItemDomain(eventItemDTO: EventItemDTO): EventItem {
        return EventItem(
            id = eventItemDTO.id,
            info = mapToEventInfoDomain(eventItemDTO.info),
            collection = mapToEventCollectionDomain(eventItemDTO.collection),
            participantEntity = mapToParticipantDomain(eventItemDTO.participantsInfo),
            createdAt = eventItemDTO.createdAt,
            updatedAt = eventItemDTO.updatedAt ?: 0L
        )
    }

    override fun mapToEventInfoDomain(eventInfoDTO: EventInfoDTO): EventInfoItem {
        return EventInfoItem(
            name = eventInfoDTO.name ?: "",
            status = eventInfoDTO.status.typeName,
            description = eventInfoDTO.description ?: "",
            location = eventInfoDTO.location ?: "online",
            startAt = eventInfoDTO.startAt ?: 0L,
            endAt = eventInfoDTO.endAt ?: 0L,
            size = eventInfoDTO.size.toInt(),
            eventType = eventInfoDTO.eventType?.name ?: "",
            interval = eventInfoDTO.interval ?: 0L,
        )
    }

    override fun mapToEventCollectionDomain(eventCollectionDTO: EventCollectionDTO?): EventCollectionItem {
        return eventCollectionDTO?.let {
            EventCollectionItem(
                collectionId = it.collectionId ?: "",
                sequence = it.sequence ?: ""
            )
        } ?: EventCollectionItem()
    }

    override fun mapToParticipantDomain(participantInfoDTO: ParticipantsInfoDTO): ParticipantItem {
        return ParticipantItem(
            owner = mapToUserDomain(participantInfoDTO.owner),
            members = if (participantInfoDTO.members == null) emptyList() else mapToUserListDomain(
                participantInfoDTO.members
            ),
            totalSize = participantInfoDTO.totalNumber.toInt()
        )
    }

    override fun mapToString(str: String): String {
        return str
    }
}