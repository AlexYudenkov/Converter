package ru.alexydenkov.converter.shared.data

import ru.alexydenkov.converter.shared.domain.entity.CurrencyEntity

interface LocalDataSource {

	suspend fun getCurrencyList(): CurrencyEntity?

	suspend fun updateCache(currencyEntity: CurrencyEntity)
}
