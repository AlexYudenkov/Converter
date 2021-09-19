package ru.alexydenkov.converter.shared.data

import ru.alexydenkov.converter.shared.domain.entity.CurrencyEntity

class CurrencyDataSourceImpl(private val api: CurrencyApi) : CurrencyDataSource {

	override suspend fun getCurrencyList(): CurrencyEntity {
		return api.getCurrencyList().toEntity()
	}

}