package ru.alexydenkov.converter.shared.domain

import ru.alexydenkov.converter.shared.data.CurrencyRepository
import ru.alexydenkov.converter.shared.domain.entity.CurrencyEntity

class GetCurrencyUseCase(private val repository: CurrencyRepository) {

	suspend operator fun invoke(): CurrencyEntity {
		return repository.getCurrencyList()
	}
}