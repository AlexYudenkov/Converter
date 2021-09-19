package ru.alexydenkov.converter.convertcurrency.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.alexydenkov.converter.currencylist.presentation.EventsDispatcher
import ru.alexydenkov.converter.currencylist.presentation.EventsDispatcherOwner
import ru.alexydenkov.converter.shared.domain.GetCurrencyUseCase

class ConvertCurrencyViewModel(
	private val getCurrencyUseCase: GetCurrencyUseCase
): ViewModel(), EventsDispatcherOwner<ConvertCurrencyViewModel.EventsListener> {

	interface EventsListener {
		fun setResult(value: String)
	}

	override val eventsDispatcher: EventsDispatcher<EventsListener> = EventsDispatcher()

	private val _currencyList = MutableStateFlow<ConvertCurrencyState>(ConvertCurrencyState.Initial)
	val state: Flow<ConvertCurrencyState>
		get() = _currencyList

	private val _screenStatus = MutableStateFlow<ScreenStatus>(ScreenStatus.Initial)
	val screenStatus:Flow<ScreenStatus>
		get() = _screenStatus

	private val errorHandler = CoroutineExceptionHandler{ _, _->
		_currencyList.value = ConvertCurrencyState.Error
	}

	fun fetchInfo(id: String){
		viewModelScope.launch(errorHandler) {
			_currencyList.value = ConvertCurrencyState.Loading
			val info = getCurrencyUseCase.invoke()
			val list = info.currencyEntity
			val index = list.indexOfFirst {
				it.id==id
			}
			_screenStatus.value = ScreenStatus.IsChosen(itemIsChosen = index)
			_currencyList.value = ConvertCurrencyState.Content(info)
		}
	}

	fun currencySectionClick(item: Int){
		_screenStatus.value = (_screenStatus.value as ScreenStatus.IsChosen).copy(itemIsChosen = item )
		getCalculationResult()
	}

	fun onNumberSet(number: Int?) {
		var numberIsSet = number
		if(numberIsSet == null){
			numberIsSet = 0
		}
		_screenStatus.value = (_screenStatus.value as ScreenStatus.IsChosen).copy(numberIsSet = numberIsSet)
		getCalculationResult()
	}

	private fun getCalculationResult() {
		val isChosen = _screenStatus.value as ScreenStatus.IsChosen
		val index = isChosen.itemIsChosen
		val numIsSet = isChosen.numberIsSet
		val currencyValue = (_currencyList.value as ConvertCurrencyState.Content).information.currencyEntity[index].value
		val calculatedNumber = String.format("%.3f",numIsSet/currencyValue)
		eventsDispatcher.dispatchEvent {
			setResult(calculatedNumber)
		}
	}

}