package org.big3.clyq.core.models

import org.big3.clyq.core.models.remote.entity.EventCollectionDTO
import org.big3.clyq.core.models.remote.entity.EventInfoDTO
import org.big3.clyq.core.models.remote.entity.EventItemDTO
import org.big3.clyq.core.models.remote.entity.EventListDTO
import org.big3.clyq.core.models.remote.entity.GroupDTO
import org.big3.clyq.core.models.remote.entity.GroupInfoDTO
import org.big3.clyq.core.models.remote.entity.GroupListDTO
import org.big3.clyq.core.models.remote.entity.ParticipantsInfoDTO
import org.big3.clyq.core.models.remote.entity.UserDTO
import org.big3.clyq.core.models.remote.entity.UserServiceDTO
import org.big3.clyq.core.models.local.entity.EventCollectionItem
import org.big3.clyq.core.models.local.entity.EventInfoItem
import org.big3.clyq.core.models.local.entity.EventItem
import org.big3.clyq.core.models.local.entity.GroupInfoItem
import org.big3.clyq.core.models.local.entity.GroupItem
import org.big3.clyq.core.models.local.entity.ParticipantItem
import org.big3.clyq.core.models.local.entity.UserItem
import org.big3.clyq.core.models.local.entity.UserServiceItem

interface DomainDataMapper {

    fun mapToUserDomain(userDTO: UserServiceDTO): UserServiceItem

    fun mapToUserDomain(userDTO: UserDTO): UserItem

    fun mapToUserListDomain(userDTOList: List<UserDTO>?): List<UserItem>

    fun mapToGroupListDomain(groupListDTO: GroupListDTO?): List<GroupItem>

    fun mapGroupInfoDomain(groupInfoDTO: GroupInfoDTO): GroupInfoItem

    fun mapToGroupItemDomain(groupDTO: GroupDTO): GroupItem

    fun mapToEventListDomain(eventDTOList: EventListDTO?): List<EventItem>

    fun mapToEventItemDomain(eventItemDTO: EventItemDTO): EventItem

    fun mapToEventInfoDomain(eventInfoDTO: EventInfoDTO): EventInfoItem

    fun mapToEventCollectionDomain(eventCollectionDTO: EventCollectionDTO?): EventCollectionItem

    fun mapToParticipantDomain(participantInfoDTO: ParticipantsInfoDTO): ParticipantItem

    fun mapToString(str: String): String

}