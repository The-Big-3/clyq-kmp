package org.big3.clyq.core.datasource

import org.big3.clyq.core.datasource.remote.EventCollectionDTO
import org.big3.clyq.core.datasource.remote.EventDTO
import org.big3.clyq.core.datasource.remote.EventInfoDTO
import org.big3.clyq.core.datasource.remote.EventItemDTO
import org.big3.clyq.core.datasource.remote.EventListDTO
import org.big3.clyq.core.datasource.remote.GroupDTO
import org.big3.clyq.core.datasource.remote.GroupInfoDTO
import org.big3.clyq.core.datasource.remote.GroupItemDTO
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