package com.hbd.advent.home

import androidx.lifecycle.viewModelScope
import com.hbd.advent.common.model.Result
import com.hbd.advent.common.util.RemainDateCalculator
import com.hbd.advent.feature.common.BaseViewModel
import com.hbd.advent.feature.common.UiEffect
import com.hbd.advent.feature.common.UiEvent
import com.hbd.advent.feature.common.UiState
import com.hbd.domain.model.SantaCalendar
import com.hbd.domain.usecase.calendar.GetSantaCalendarListUseCase
import com.hbd.domain.usecase.common.GetDailySentenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeSantaViewModel @Inject constructor(
    private val getSantaCalendarListUseCase: GetSantaCalendarListUseCase,
    private val getDailySentenceUseCase: GetDailySentenceUseCase
): BaseViewModel<HomeSantaUiState, HomeSantaEvent, HomeSantaEffect>() {
    override fun createInitialState(): HomeSantaUiState {
        return HomeSantaUiState(RemainDateCalculator.getRemainDay(), "", listOf())
    }

    init {
        setEvent(HomeSantaEvent.FetchInitialState)
    }

    override fun handleEvent(event: HomeSantaEvent) {
        when(event){
            is HomeSantaEvent.FetchInitialState -> {
                fetchInitialData()
            }
        }
    }

    private fun fetchInitialData(){
        viewModelScope.launch {
            getDailySentenceUseCase().combine(getSantaCalendarListUseCase()){ sentence, calendarList ->
                if(sentence is Result.Success && calendarList is Result.Success){
                    setState(currentState.copy(message = sentence.data ?: "", calendarList = calendarList.data ?: listOf()))
                } else {
                    // TODO error handling
                }
            }
        }
    }
}

data class HomeSantaUiState(
    val dday: Long,
    val message: String,
    val calendarList: List<SantaCalendar>
): UiState

sealed class HomeSantaEvent: UiEvent{
    object FetchInitialState: HomeSantaEvent()
}

sealed class HomeSantaEffect: UiEffect