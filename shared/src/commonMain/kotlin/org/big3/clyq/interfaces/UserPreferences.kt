package org.big3.clyq.interfaces

import org.big3.clyq.core.models.local.entity.UserItem

interface UserPreferences {
    suspend fun saveUserProfile(user: UserItem)

    suspend fun getUserProfile(): UserItem?
}