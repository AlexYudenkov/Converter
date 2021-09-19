package ru.alexydenkov.converter.convertcurrency.presentation

import ru.alexydenkov.converter.shared.domain.entity.CurrencyEntity

sealed class ConvertCurrencyState {
	object Initial : ConvertCurrencyState()
	object Loading : ConvertCurrencyState()
	object Error : ConvertCurrencyState()
	data class Content(
		val information: CurrencyEntity,
	) : ConvertCurrencyState()
}

sealed class ScreenStatus {
	object Initial : ScreenStatus()
	data class IsChosen(
		val itemIsChosen: Int = 0,
		val numberIsSet: Int = 1,
	) : ScreenStatus()
}