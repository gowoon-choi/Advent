package com.hbd.advent

import com.hbd.advent.common.model.Result
import com.hbd.advent.feature.common.BaseViewModel
import com.hbd.advent.feature.common.UiEffect
import com.hbd.advent.feature.common.UiEvent
import com.hbd.advent.feature.common.UiState
import com.hbd.domain.usecase.user.AutoLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val autoLoginUseCase: AutoLoginUseCase,
) : BaseViewModel<MainUiState, UiEvent, UiEffect>() {
    override fun createInitialState(): MainUiState {
        return MainUiState(TargetRoute.LOGIN)
    }

    init {
        setState(
            currentState.copy(
                route = if (hasToken()) {
                    TargetRoute.HOME
                } else {
                    TargetRoute.LOGIN
                }
            )
        )
    }

    private fun hasToken(): Boolean {
        return runBlocking {
            (autoLoginUseCase().firstOrNull() as? Result.Success)?.data ?: false
        }
    }

    override fun handleEvent(event: UiEvent) {}
}

data class MainUiState(
    val route: TargetRoute
) : UiState

enum class TargetRoute {
    LOGIN, HOME
}