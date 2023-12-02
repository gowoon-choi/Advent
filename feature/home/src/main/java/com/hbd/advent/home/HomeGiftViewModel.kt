package com.hbd.advent.home

import androidx.lifecycle.viewModelScope
import com.hbd.advent.common.model.Result
import com.hbd.advent.common.util.RemainDateCalculator
import com.hbd.advent.common.util.RemainTime
import com.hbd.advent.feature.common.BaseViewModel
import com.hbd.advent.feature.common.UiEffect
import com.hbd.advent.feature.common.UiEvent
import com.hbd.advent.feature.common.UiState
import com.hbd.domain.model.GiftCalendar
import com.hbd.domain.usecase.calendar.GetGiftCalendarListUseCase
import com.hbd.domain.usecase.common.GetDailySentenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeGiftViewModel @Inject constructor(
    private val getGiftCalendarListUseCase: GetGiftCalendarListUseCase,
    private val getDailySentenceUseCase: GetDailySentenceUseCase
): BaseViewModel<HomeGiftUiState, HomeGiftUiEvent, HomeGiftUiEffect>() {
    override fun createInitialState(): HomeGiftUiState {
        return HomeGiftUiState(RemainDateCalculator.getRemainDayAndHour(), "", listOf())
    }

    init {
        setEvent(HomeGiftUiEvent.FetchInitialState)
    }

    override fun handleEvent(event: HomeGiftUiEvent) {
        when(event){
            is HomeGiftUiEvent.FetchInitialState -> {
                fetchInitialData()
            }
        }
    }

    private fun fetchInitialData(){
        viewModelScope.launch {
            getGiftCalendarListUseCase().combine(getDailySentenceUseCase()){ calendarList, message ->
                if(calendarList is Result.Success && message is Result.Success){
                    Result.Success(currentState.copy(message = message.data ?: "", calendarList = calendarList.data ?: listOf()))
                } else {
                    // TODO
                    Result.Error(Exception())
                }
            }.collect{
                when(it){
                    is Result.Success -> {
                        setState(it.data ?: currentState)
                    }
                    is Result.Error -> {
                        // TODO
                    }
                }
            }
        }
    }
}

data class HomeGiftUiState(
    val remainTime: RemainTime,
    val message: String,
    val calendarList: List<GiftCalendar>
): UiState

sealed class HomeGiftUiEvent: UiEvent{
    object FetchInitialState: HomeGiftUiEvent()
}

sealed class HomeGiftUiEffect: UiEffect