package org.big3.clyq.repository

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import org.big3.clyq.constants.Constants
import org.big3.clyq.interfaces.TokenPreferences

class TokenPreferencesImpl(private val settings: Settings) : TokenPreferences {

    override suspend fun saveAuthToken(token: String) {
        settings[Constants.AUTH_TOKEN_KEY] = token
    }

    override suspend fun saveIdToken(token: String) {
        settings[Constants.ID_TOKEN_KEY] = token
    }

    override suspend fun saveRefreshToken(token: String) {
        settings[Constants.REFRESH_TOKEN_KEY] = token
    }

    override suspend fun getAuthToken(): String {
        return settings[Constants.AUTH_TOKEN_KEY]?:""
    }

    override suspend fun getIdToken(): String {
        return settings[Constants.ID_TOKEN_KEY]?:""
    }

    override suspend fun getRefreshToken(): String {
        return settings[Constants.REFRESH_TOKEN_KEY]?:""
    }

    override suspend fun clearToken() {
        settings.clear()
    }
}