package org.big3.clyq.repository

import android.content.Context
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.suspendCancellableCoroutine
import org.big3.clyq.constants.AuthConfig
import org.big3.clyq.interfaces.AuthRepository
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthRepositoryImpl:AuthRepository {

    private val auth0 = Auth0(
        clientId = AuthConfig.AUTH0_CLIENT,
        domain = AuthConfig.AUTH0_DOMAIN,
    )

    override suspend fun userLogin(context: Context): Flow<Credentials> {
        return flow {
            val credentials = suspendCancellableCoroutine<Credentials> { cancellableContinuation ->

                val client = WebAuthProvider.login(auth0)
                client
                    .withScheme(AuthConfig.SCHEME)
                    .withScope("openid profile email offline_access")
                    .withAudience("https://${AuthConfig.AUTH0_DOMAIN}/api/v2/")
                    .start(context,
                        object : Callback<Credentials, AuthenticationException> {
                            override fun onFailure(error: AuthenticationException) {
                                cancellableContinuation.resumeWithException(error)
                            }
                            override fun onSuccess(result: Credentials) {
                                cancellableContinuation.resume(result)
                            }
                        }
                    )
            }
            emit(credentials)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun userLogout(context: Context): Flow<String> {
        return flow {
            val res = suspendCancellableCoroutine<String> { cancellableContinuation ->
                WebAuthProvider
                    .logout(auth0)
                    .withScheme(AuthConfig.SCHEME)
                    .start(context,
                        object : Callback<Void?, AuthenticationException> {
                            override fun onFailure(error: AuthenticationException) {
                                cancellableContinuation.resumeWithException(error)
                            }

                            override fun onSuccess(result: Void?) {
                                cancellableContinuation.resume("Done")
                            }

                        }
                    )
            }
            emit(res)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun checkRefreshTokenValid(refreshToken: String): Boolean {
        var res: Boolean
        coroutineScope {
            try{
                AuthenticationAPIClient(auth0).renewAuth(refreshToken).await()
                res = true
            }catch (e:AuthenticationException){
                res =false
            }

        }
        return res
    }
}