package com.hbd.advent.home

import androidx.lifecycle.viewModelScope
import com.hbd.advent.common.model.Result
import com.hbd.advent.common.util.RemainDateCalculator
import com.hbd.advent.common.util.RemainTime
import com.hbd.advent.feature.common.BaseViewModel
import com.hbd.advent.feature.common.UiEffect
import com.hbd.advent.feature.common.UiEvent
import com.hbd.advent.feature.common.UiState
import com.hbd.domain.model.SantaCalendar
import com.hbd.domain.usecase.calendar.GetSantaCalendarListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeSantaViewModel @Inject constructor(
    private val getSantaCalendarListUseCase: GetSantaCalendarListUseCase
): BaseViewModel<HomeSantaUiState, HomeSantaEvent, HomeSantaEffect>() {
    override fun createInitialState(): HomeSantaUiState {
        return HomeSantaUiState(RemainDateCalculator.getRemainDayAndHour(), listOf())
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
            getSantaCalendarListUseCase().collect{
                when(it){
                    is Result.Success -> {
                        setState(currentState.copy(calendarList = it.data ?: listOf()))
                    }
                    is Result.Error -> {

                    }
                }
            }
        }
    }
}

data class HomeSantaUiState(
    val remainTime: RemainTime,
    val calendarList: List<SantaCalendar>
): UiState

sealed class HomeSantaEvent: UiEvent{
    object FetchInitialState: HomeSantaEvent()
}

sealed class HomeSantaEffect: UiEffect