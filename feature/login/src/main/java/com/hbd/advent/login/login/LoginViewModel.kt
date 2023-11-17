package com.hbd.advent.login.login

import androidx.lifecycle.viewModelScope
import com.hbd.advent.datastore.PreferenceManager
import com.hbd.advent.feature.common.BaseViewModel
import com.hbd.advent.feature.common.UiEffect
import com.hbd.advent.feature.common.UiEvent
import com.hbd.advent.feature.common.UiState
import com.hbd.login_manager.LoginManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginManager: LoginManager,
    private val preferenceManager: PreferenceManager
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
                    preferenceManager.setUserToken(token.accessToken)
                }
            }

            if(token != null){
                Timber.d("kakao login failed", error)
            }
        }
    }

    private fun

}

data class LoginUiState(
    val state: LoginState
): UiState

enum class LoginState{
    BEFORE, SUCCESS, FAILED
}

sealed class LoginUiEvent: UiEvent {
    object OnClickLoginButton: LoginUiEvent()
}

sealed class LoginUiEffect: UiEffect {
    object GoToJoin: LoginUiEffect()
    object GoToHome: LoginUiEffect()
}