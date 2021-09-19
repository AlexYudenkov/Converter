package ru.alexydenkov.converter

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.alexydenkov.converter.convertcurrency.di.ConvertCurrencyModule
import ru.alexydenkov.converter.currencylist.di.EmploymentMainModules
import ru.alexydenkov.converter.shared.di.SharedMainModules

class App : Application() {

	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger()
			androidContext(this@App)
			modules(SharedMainModules)
			modules(EmploymentMainModules)
			modules(ConvertCurrencyModule)
		}
	}
}