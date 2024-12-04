package org.big3.clyq.constants

sealed class LoginState {
    data object Idle : LoginState()
    data object LoggedIn:LoginState()
    data object LoggedOut:LoginState()
    data object TokenExpired :LoginState()
    data class Error(val exception: Throwable) : LoginState()
}