package ru.alexydenkov.converter.currencylist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.alexydenkov.converter.shared.domain.GetCurrencyUseCase
import ru.alexydenkov.converter.shared.domain.entity.CurrencyInfoEntity

class CurrencyViewModel(
	private val getCurrencyUseCase: GetCurrencyUseCase
): ViewModel(), EventsDispatcherOwner<CurrencyViewModel.EventsListener> {

	interface EventsListener {
		fun navigateToCurrencySection(item: CurrencyInfoEntity)
	}

	override val eventsDispatcher :EventsDispatcher<EventsListener> = EventsDispatcher()

	private val _currencyList = MutableStateFlow<CurrencyState>(CurrencyState.Initial)
	val state: Flow<CurrencyState>
		get() = _currencyList

	private val errorHandler = CoroutineExceptionHandler{ _, _->
		_currencyList.value = CurrencyState.Error
	}

	fun fetchInfo() {
		viewModelScope.launch(errorHandler) {
			_currencyList.value = CurrencyState.Loading
			_currencyList.value = CurrencyState.Content(getCurrencyUseCase.invoke())
		}
	}

	fun currencySectionClick(item: CurrencyInfoEntity){
		eventsDispatcher.dispatchEvent {
			navigateToCurrencySection(item)
		}
	}
}