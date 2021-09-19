package ru.alexydenkov.converter.shared.domain.entity

data class CurrencyEntity(
	var date: String,
	val previousDate: String,
	val previousURL: String,
	val timestamp: String,
	var currencyEntity: List<CurrencyInfoEntity>
)

data class CurrencyInfoEntity(
	val key: String,
	val id: String,
	val numCode: String,
	val charCode: String,
	val nominal: String,
	val name: String,
	val value: Double,
	val previous: Double
)