package ru.alexydenkov.converter.shared.network.retrofit

import com.squareup.moshi.Moshi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.alexydenkov.converter.shared.data.CurrencyApi

val networkModule = module {
	single {
		provideRetrofit(get())
	}
}

object InternetUrl  {
		const val BASE_URL = "https://www.cbr-xml-daily.ru"
}

fun provideApi(retrofit: Retrofit) = retrofit.create(CurrencyApi::class.java)

fun provideRetrofit(moshi:Moshi):Retrofit =
	Retrofit.Builder().apply {
		addConverterFactory(MoshiConverterFactory.create(moshi))
		baseUrl(InternetUrl.BASE_URL)
	}.build()
