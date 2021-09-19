package ru.alexydenkov.converter.convertcurrency.ui

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onEach
import ru.alexydenkov.converter.convertcurrency.presentation.ConvertCurrencyState
import ru.alexydenkov.converter.convertcurrency.presentation.ConvertCurrencyViewModel
import ru.alexydenkov.converter.shared.ext.launchWhenResumed

fun ConvertCurrencyFragment.submitList(viewModel: ConvertCurrencyViewModel, adapter: ArrayAdapter<String>){
	viewModel.state.filterIsInstance<ConvertCurrencyState.Content>().onEach { it ->
		val itemList = mutableListOf<String>()
		it.information.currencyEntity.forEach {
			itemList.add(it.name)
		}
		adapter.addAll(itemList)
	}.launchWhenResumed(viewLifecycleOwner.lifecycleScope)
}

fun setOnSpinnerSelected(viewModel: ConvertCurrencyViewModel, spinner: Spinner) {
	spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
		override fun onNothingSelected(parent: AdapterView<*>?) {
		}
		override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
			viewModel.currencySectionClick(position)
		}
	}
}