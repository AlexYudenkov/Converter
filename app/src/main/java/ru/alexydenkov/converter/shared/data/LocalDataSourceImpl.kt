package ru.alexydenkov.converter.shared.data

import io.github.reactivecircus.cache4k.Cache
import ru.alexydenkov.converter.shared.domain.entity.CurrencyEntity

class LocalDataSourceImpl(private val cache: Cache<String, CurrencyEntity>):LocalDataSource {

	companion object{
		const val ENTITY = "entity"
	}

	override suspend fun getCurrencyList(): CurrencyEntity? {
		return cache.get(ENTITY)
	}

	override suspend fun updateCache(currencyEntity: CurrencyEntity) {
		cache.put(ENTITY,currencyEntity)
	}
}