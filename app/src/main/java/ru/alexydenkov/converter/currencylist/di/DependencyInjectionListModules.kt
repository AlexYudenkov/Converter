package ru.alexydenkov.converter.currencylist.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.alexydenkov.converter.currencylist.presentation.CurrencyViewModel

val CurrencyViewModelModule = module {
	viewModel{
		CurrencyViewModel(get())
	}
}


val EmploymentMainModules = listOf(
	CurrencyViewModelModule,
)
