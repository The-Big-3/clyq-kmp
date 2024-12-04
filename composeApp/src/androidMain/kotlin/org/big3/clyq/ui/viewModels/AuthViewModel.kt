package org.big3.clyq.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.big3.clyq.constants.LoginState
import org.big3.clyq.interfaces.AuthRepository
import org.big3.clyq.interfaces.TokenPreferences
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AuthViewModel : ViewModel(),KoinComponent {

    private val authRepository: AuthRepository by inject()
    private val tokenPreferences:TokenPreferences by inject()


    private val _requestLoading = MutableStateFlow(false)
    val requestLoading: StateFlow<Boolean> = _requestLoading

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState


    fun login(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _requestLoading.value = true
            authRepository.userLogin(context).catch { exception ->
                _loginState.value = LoginState.Error(exception)
                _requestLoading.value = false
            }.collect { credentials ->
                Log.e("Login", " the credentials refresh is ${credentials.refreshToken}    --- id is ${credentials.idToken}")
                _loginState.value = LoginState.LoggedIn
                tokenPreferences.saveRefreshToken(credentials.refreshToken?:"")
                tokenPreferences.saveIdToken(credentials.idToken)
                _requestLoading.value = false
            }
        }
    }

    fun logout(context: Context) {
        _requestLoading.value = true
        viewModelScope.launch(Dispatchers.IO){
            authRepository.userLogout(context).catch { exception ->
                _loginState.value = LoginState.Error(exception)
                _requestLoading.value = false
            }.collect { _ ->
                _loginState.value = LoginState.Idle
                tokenPreferences.clearToken()
                _requestLoading.value = false
            }
        }
    }

}