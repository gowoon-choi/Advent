package com.hbd.advent.login.join

import androidx.lifecycle.viewModelScope
import com.hbd.advent.common.model.Result
import com.hbd.advent.feature.common.BaseViewModel
import com.hbd.advent.feature.common.UiEffect
import com.hbd.advent.feature.common.UiEvent
import com.hbd.advent.feature.common.UiState
import com.hbd.domain.usecase.user.RegisterNicknameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val registerNicknameUseCase: RegisterNicknameUseCase
): BaseViewModel<JoinUiState, JoinUiEvent, JoinUiEffect>() {
    override fun createInitialState(): JoinUiState {
        return JoinUiState("")
    }

    override fun handleEvent(event: JoinUiEvent) {
        when(event){
            is JoinUiEvent.OnClickDone -> {
                registerNickname()
            }
            is JoinUiEvent.UpdateNickname -> {
                setState(currentState.copy(nickname = event.nickname))
            }
        }
    }

    private fun registerNickname(){
        viewModelScope.launch {
            registerNicknameUseCase(currentState.nickname).collect{
                when(it){
                    is Result.Success -> {
                        setEffect(JoinUiEffect.GoToCreateCalendarScreen)
                    }
                    is Result.Error -> {
                        // TODO Error Handling
                    }
                }
            }
        }
    }
}

data class JoinUiState(
    val nickname: String
): UiState

sealed class JoinUiEvent: UiEvent {
    data class UpdateNickname(val nickname: String): JoinUiEvent()
    object OnClickDone: JoinUiEvent()
}

sealed class JoinUiEffect: UiEffect {
    object GoToCreateCalendarScreen: JoinUiEffect()
}