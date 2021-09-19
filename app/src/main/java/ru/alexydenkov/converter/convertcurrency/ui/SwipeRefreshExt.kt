package ru.alexydenkov.converter.convertcurrency.ui

import ru.alexydenkov.converter.convertcurrency.presentation.ConvertCurrencyViewModel

internal fun ConvertCurrencyFragment.setSwipeRefresh(viewModel: ConvertCurrencyViewModel, currencyId: String) {
	binding.swipeRefresh.setOnRefreshListener {
		viewModel.fetchInfo(currencyId)
		binding.swipeRefresh.isRefreshing = false
	}
}