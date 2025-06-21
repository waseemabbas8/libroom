package com.libroom.auth.ui

import com.waseem.mvi.MviAction
import com.waseem.mvi.MviEvent
import com.waseem.mvi.MviResult
import com.waseem.mvi.MviStateReducer
import com.waseem.mvi.MviViewState
import javax.inject.Inject

internal sealed interface LoginUiState : MviViewState {
    data object Default : LoginUiState
    data object Loading : LoginUiState
    data class Error(val message: String) : LoginUiState
}

internal sealed interface LoginAction : MviAction {
    data class SignIn(val email: String, val password: String) : LoginAction
}

internal sealed interface LoginResult : MviResult {
    data object Loading : LoginResult
    data object Success : LoginResult
    data class Failure(val message: String) : LoginResult
}

internal sealed interface LoginEvent : MviEvent

internal class LoginStateReducer @Inject constructor() : MviStateReducer<LoginUiState, LoginResult> {
    override fun LoginUiState.reduce(result: LoginResult): LoginUiState {
        return when (result) {
            LoginResult.Loading -> LoginUiState.Loading
            LoginResult.Success -> LoginUiState.Default
            is LoginResult.Failure -> LoginUiState.Error(result.message)
        }
    }
}