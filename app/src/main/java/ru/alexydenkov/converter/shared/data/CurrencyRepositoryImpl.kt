package ru.alexydenkov.converter.shared.data

import ru.alexydenkov.converter.shared.domain.entity.CurrencyEntity

class CurrencyRepositoryImpl(
	private val remoteDataSource: CurrencyDataSource,
	private val localDataSource: LocalDataSource
	):CurrencyRepository {

	override suspend fun getCurrencyList(): CurrencyEntity {
		var currencyEntity = localDataSource.getCurrencyList()
		return if(currencyEntity!=null){
			currencyEntity
		} else {
			currencyEntity = remoteDataSource.getCurrencyList()
			localDataSource.updateCache(currencyEntity)
			currencyEntity
		}
	}
}