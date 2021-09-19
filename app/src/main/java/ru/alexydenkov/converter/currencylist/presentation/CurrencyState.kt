package ru.alexydenkov.converter.currencylist.presentation

import ru.alexydenkov.converter.shared.domain.entity.CurrencyEntity

sealed class CurrencyState {
	object Initial : CurrencyState()
	object Loading : CurrencyState()
	object Error : CurrencyState()
	data class Content(
		val information: CurrencyEntity
	) : CurrencyState()
}
