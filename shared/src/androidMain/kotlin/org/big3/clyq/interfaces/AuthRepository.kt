package org.big3.clyq.interfaces

import android.content.Context
import com.auth0.android.result.Credentials
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun userLogin(context: Context): Flow<Credentials>

    suspend fun userLogout(context: Context): Flow<String>

    suspend fun checkRefreshTokenValid(refreshToken:String):Boolean

}