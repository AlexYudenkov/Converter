package ru.alexydenkov.converter.shared.data

import retrofit2.http.GET

interface CurrencyApi {

	@GET("/daily_json.js")
	suspend fun getCurrencyList(): CurrencyDto
}
