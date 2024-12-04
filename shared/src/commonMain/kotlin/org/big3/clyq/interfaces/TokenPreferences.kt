package org.big3.clyq.interfaces

interface TokenPreferences {

    suspend fun saveAuthToken(token: String)

    suspend fun saveIdToken(token: String)

    suspend fun saveRefreshToken(token: String)

    suspend fun getAuthToken():String

    suspend fun getIdToken():String

    suspend fun getRefreshToken():String

    suspend fun clearToken()

}