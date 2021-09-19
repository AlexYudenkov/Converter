package ru.alexydenkov.converter.shared.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyDto(
	@Json(name = "Date") var date: String,
	@Json(name = "PreviousDate") val previousDate: String,
	@Json(name = "PreviousURL") val previousURL: String,
	@Json(name = "Timestamp") val timestamp: String,
	@Json(name = "Valute") val currencyDto: Map<String, CurrencyInfoDto>
) {

}

@JsonClass(generateAdapter = true)
data class CurrencyInfoDto(
	@Json(name = "ID") val id: String,
	@Json(name = "NumCode") val numCode: String,
	@Json(name = "CharCode") val charCode: String,
	@Json(name = "Nominal") val nominal: String,
	@Json(name = "Name") val name: String,
	@Json(name = "Value") val value: Double,
	@Json(name = "Previous") val previous: Double
)