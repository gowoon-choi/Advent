package com.hbd.create_calendar

import androidx.lifecycle.viewModelScope
import com.hbd.advent.common.model.Result
import com.hbd.advent.datastore.PreferenceManager
import com.hbd.advent.feature.common.BaseViewModel
import com.hbd.advent.feature.common.UiEffect
import com.hbd.advent.feature.common.UiEvent
import com.hbd.advent.feature.common.UiState
import com.hbd.domain.model.Calendar
import com.hbd.domain.model.UiTheme
import com.hbd.domain.usecase.calendar.CreateCalendarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCalendarViewModel @Inject constructor(
    private val createCalendarUseCase: CreateCalendarUseCase,
    private val preferenceManager: PreferenceManager
): BaseViewModel<CreateCalendarUiState, CreateCalendarUiEvent, CreateCalendarUiEffect>() {
    override fun createInitialState(): CreateCalendarUiState {
        return CreateCalendarUiState("", "", UiTheme.GREEN)
    }

    init {
        setEvent(CreateCalendarUiEvent.InitUserNickname)
    }

    override fun handleEvent(event: CreateCalendarUiEvent) {
        when(event){
            is CreateCalendarUiEvent.InitUserNickname -> {
                initUserNickname()
            }
            is CreateCalendarUiEvent.UpdateCalendarName -> {
                setState(currentState.copy(calendarName = event.calendarName))
            }
            is CreateCalendarUiEvent.SelectCalendarTheme -> {
                setState(currentState.copy(calendarTheme = event.selectedCalendarTheme))
            }
            is CreateCalendarUiEvent.OnClickDone -> {
                requestCreateCalendar()
            }
        }
    }

    private fun initUserNickname(){
        viewModelScope.launch {
            preferenceManager.userNickname.collect{
                it?.let { nickname ->
                    setState(currentState.copy(userNickname = nickname))
                }
            }
        }
    }

    private fun requestCreateCalendar(){
        viewModelScope.launch {
            createCalendarUseCase(Calendar(currentState.calendarName, currentState.calendarTheme)).collect{
                when(it){
                    is Result.Success -> {
                        setEffect(CreateCalendarUiEffect.GoToSuccessScreen)
                    }
                    is Result.Error -> {
                        // TODO Error Handling
                    }
                }
            }
        }
    }
}

data class CreateCalendarUiState(
    val userNickname: String,
    val calendarName: String,
    val calendarTheme: UiTheme
): UiState

sealed class CreateCalendarUiEvent: UiEvent{
    object InitUserNickname: CreateCalendarUiEvent()
    data class UpdateCalendarName(val calendarName: String): CreateCalendarUiEvent()
    data class SelectCalendarTheme(val selectedCalendarTheme: UiTheme): CreateCalendarUiEvent()
    object OnClickDone: CreateCalendarUiEvent()
}

sealed class CreateCalendarUiEffect: UiEffect{
    object GoToSuccessScreen: CreateCalendarUiEffect()
}