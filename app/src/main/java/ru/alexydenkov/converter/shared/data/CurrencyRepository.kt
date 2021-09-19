package ru.alexydenkov.converter.shared.data

import ru.alexydenkov.converter.shared.domain.entity.CurrencyEntity

interface CurrencyRepository {

	suspend fun getCurrencyList(): CurrencyEntity
}