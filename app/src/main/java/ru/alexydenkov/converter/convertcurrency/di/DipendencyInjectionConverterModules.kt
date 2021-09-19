package ru.alexydenkov.converter.convertcurrency.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.alexydenkov.converter.convertcurrency.presentation.ConvertCurrencyViewModel

val ConvertCurrencyViewModelModule = module {
	viewModel {
		ConvertCurrencyViewModel(get())
	}
}

val ConvertCurrencyModule = listOf(
	ConvertCurrencyViewModelModule,
)