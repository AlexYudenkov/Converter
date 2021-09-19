package ru.alexydenkov.converter.shared.data

import ru.alexydenkov.converter.shared.domain.entity.CurrencyEntity
import ru.alexydenkov.converter.shared.domain.entity.CurrencyInfoEntity

fun CurrencyDto.toEntity(): CurrencyEntity =
	CurrencyEntity(
		date,
		previousDate,
		previousURL,
		timestamp,
		currencyDto.toEntity()
	)

private fun Map<String, CurrencyInfoDto>.toEntity(): List<CurrencyInfoEntity> {
	val newCurrencyInfoEntity: MutableList<CurrencyInfoEntity> = mutableListOf()
	forEach {
		val currencyInfoEntity = CurrencyInfoEntity(
			key = it.key,
			id = it.value.id,
			numCode = it.value.numCode,
			charCode = it.value.charCode,
			nominal = it.value.nominal,
			name = it.value.name,
			value = it.value.value,
			previous = it.value.previous
		)
		newCurrencyInfoEntity.add(currencyInfoEntity)
	}
	return newCurrencyInfoEntity
}
