package com.libroom.auth.ui

import com.libroom.auth.domain.LoginUseCase
import com.waseem.mvi.BaseStateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val login: LoginUseCase,
    reducer: LoginStateReducer
) : BaseStateViewModel<LoginAction, LoginResult, LoginEvent, LoginUiState, LoginStateReducer>(
    initialState = LoginUiState.Default,
    reducer = reducer
) {
    override fun LoginAction.process(): Flow<LoginResult> {
        return when (this) {
            is LoginAction.SignIn -> flow<LoginResult> {
                login(email = email, password = password).onSuccess {
                    emit(LoginResult.Success)
                }.onFailure { throw it }
            }.onStart {
                emit(LoginResult.Loading)
            }.catch {
                emit(LoginResult.Failure(it.message ?: ""))
            }
        }
    }
}