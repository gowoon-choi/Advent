package com.hbd.advent.login.login

import androidx.lifecycle.viewModelScope
import com.hbd.advent.datastore.PreferenceManager
import com.hbd.advent.feature.common.BaseViewModel
import com.hbd.advent.feature.common.UiEffect
import com.hbd.advent.feature.common.UiEvent
import com.hbd.advent.feature.common.UiState
import com.hbd.domain.usecase.LoginUseCase
import com.hbd.login_manager.LoginManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import com.hbd.advent.common.model.Result

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginManager: LoginManager,
    private val loginUseCase: LoginUseCase,
): BaseViewModel<LoginUiState, LoginUiEvent, LoginUiEffect>(){
    override fun createInitialState(): LoginUiState {
        return LoginUiState(LoginState.BEFORE)
    }

    override fun handleEvent(event: LoginUiEvent) {
        when(event){
            is LoginUiEvent.OnClickLoginButton -> {
                requestKakaoLogin()
            }
        }
    }

    private fun requestKakaoLogin(){
        loginManager.requestLogin { token, error ->
            if(token != null){
                viewModelScope.launch {
                    loginUseCase.login(token.accessToken).collect{
                        when(it){
                            is Result.Success -> {
                                setState(currentState.copy(state = LoginState.SUCCESS(it.data.newUser)))
                            }
                            is Result.Error -> {
                                // TODO error handling
                                setState(currentState.copy(state = LoginState.FAILED))
                            }
                        }
                    }
                }
            }

            if(error != null){
                Timber.d("kakao login failed", error)
                setState(currentState.copy(LoginState.FAILED))
            }
        }
    }



}

data class LoginUiState(
    val state: LoginState
): UiState

sealed class LoginState{
    object BEFORE: LoginState()
    data class SUCCESS(val newUser: Boolean): LoginState()
    object FAILED: LoginState()
}

sealed class LoginUiEvent: UiEvent {
    object OnClickLoginButton: LoginUiEvent()
}

sealed class LoginUiEffect: UiEffect {
    object GoToJoin: LoginUiEffect()
    object GoToHome: LoginUiEffect()
}