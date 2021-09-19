package ru.alexydenkov.converter.shared.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.reactivecircus.cache4k.Cache
import org.koin.dsl.module
import ru.alexydenkov.converter.shared.data.*
import ru.alexydenkov.converter.shared.domain.GetCurrencyUseCase
import ru.alexydenkov.converter.shared.domain.entity.CurrencyEntity
import ru.alexydenkov.converter.shared.network.retrofit.provideApi
import ru.alexydenkov.converter.shared.network.retrofit.provideRetrofit
import kotlin.time.Duration

val CurrencyDomainModule = module {
	factory { GetCurrencyUseCase(repository = get()) }
}

val CurrencyDataModule = module {
	factory<CurrencyRepository> { CurrencyRepositoryImpl(remoteDataSource = get(),localDataSource = get()) }

	factory<CurrencyDataSource> { CurrencyDataSourceImpl(api = get()) }
	factory<LocalDataSource> { LocalDataSourceImpl(cache = get()) }
}


val networkModule = module {
	single {
		provideRetrofit(get())
	}
}

val cacheModule = module {
	single{
		Cache.Builder()
			.expireAfterAccess(Duration.minutes(3))
			.build<String, CurrencyEntity>()
	}
}

val ApiModule = module {
	factory { provideApi(get()) }
}

val ProvideMoshi = module {
	single { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }
}

val SharedMainModules = listOf(
	CurrencyDomainModule,
	CurrencyDataModule,
	networkModule,
	ApiModule,
	ProvideMoshi,
	cacheModule,
)