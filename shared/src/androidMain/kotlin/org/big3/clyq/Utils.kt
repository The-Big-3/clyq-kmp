package org.big3.clyq


import androidx.core.util.PatternsCompat
import org.big3.clyq.core.models.local.entity.EventItem
import org.big3.clyq.core.models.local.entity.GroupInfoItem
import org.big3.clyq.core.models.local.entity.GroupItem
import org.big3.clyq.core.models.local.entity.UserItem
import org.big3.clyq.enum.UserEventState
import org.big3.clyq.enum.UserGroupState
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

object Utils {

    fun resetDateFormat(pattern: String, date: Long): String {
        val zonedDateTime = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault())
        val formatter = DateTimeFormatter.ofPattern(pattern)
        return zonedDateTime.format(formatter)
    }

    fun getTimeOfDay(): String {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 0..11 -> "Good morning"
            in 12..17 -> "Good afternoon"
            else -> "Good night"
        }
    }

    fun resetEventTimeFormat(
        startTime: Long,
        endTime: Long,
        pattern: String = "dd/MM HH:mm"
    ): String {
        val start = resetDateFormat(pattern, startTime)
        val end = resetDateFormat(pattern, endTime)
        return "$start - $end"
    }


    fun calculateEventDuration(startTime: Long, endTime: Long): String {
        val durationInSeconds = endTime - startTime
        // Convert seconds to other time units
        val days = TimeUnit.MILLISECONDS.toDays(durationInSeconds)
        val totalHours = TimeUnit.MILLISECONDS.toHours(durationInSeconds)
        val totalMinutes = TimeUnit.MILLISECONDS.toMinutes(durationInSeconds)

        return when {
            totalMinutes <= 60 -> "${totalMinutes}m"
            totalHours in 1..23 && totalMinutes % 60 == 0L -> "${totalHours}h"
            totalHours in 1..23 -> "${totalHours}h ${totalMinutes % 60}m"
            else -> "${days}d"
        }
    }

    private fun convertTimeToDateTime(timeString: String): LocalDateTime? {

        val builder = DateTimeFormatterBuilder()
            .appendPattern("h:mma")
            .parseDefaulting(
                ChronoField.YEAR,
                LocalDateTime.now().year.toLong()
            )
            .parseDefaulting(
                ChronoField.MONTH_OF_YEAR,
                LocalDateTime.now().monthValue.toLong()
            )
            .parseDefaulting(
                ChronoField.DAY_OF_MONTH,
                LocalDateTime.now().dayOfMonth.toLong()
            )

        val formatter = builder.toFormatter()

        return try {
            LocalDateTime.parse(timeString.uppercase(), formatter)
        } catch (e: Exception) {
            null
        }
    }

    fun isValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun formatMemberDisplay(members: List<UserItem>, maxFilter: Int = 5): List<String> {
        return if (members.size > maxFilter) {
            members.take(maxFilter).map { it.email.take(2) } + "+"
        } else {
            members.map { it.email.take(2) }.ifEmpty { listOf("+") }
        }
    }

    fun verifyJoinEventPermission(currentUser: String, event: EventItem): Boolean {
        return event.participantEntity.members.any { it.email == currentUser } || event.participantEntity.owner.email == currentUser
    }

    fun verifyUserStateForGroup(currentUser: String, groupDTO: GroupItem): UserGroupState {
        return when {
            groupDTO.participants.owner.email == currentUser -> UserGroupState.OWNED
            groupDTO.participants.members.any { it.email == currentUser } -> UserGroupState.JOINED
            else -> UserGroupState.GUEST
        }
    }

    fun verifyUserStateForEvent(currentUser: String, eventDTO: EventItem): UserEventState {
        return when {
            eventDTO.participantEntity.owner.email == currentUser -> UserEventState.OWNED
            eventDTO.participantEntity.members.isEmpty() -> UserEventState.IDLE
            eventDTO.participantEntity.members.any { it.email == currentUser } -> UserEventState.JOINED
            else -> UserEventState.IDLE
        }
    }


    fun isVerifiedGroup(groupInfo: GroupInfoItem): Boolean {
        return groupInfo.name.isNotEmpty() && groupInfo.description.isNotEmpty()
                && groupInfo.cultureAndValues.isNotEmpty()
    }
}