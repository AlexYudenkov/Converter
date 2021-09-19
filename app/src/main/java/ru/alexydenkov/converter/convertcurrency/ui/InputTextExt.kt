package ru.alexydenkov.converter.convertcurrency.ui

import android.text.Editable
import android.text.TextWatcher
import ru.alexydenkov.converter.convertcurrency.presentation.ConvertCurrencyViewModel

fun ConvertCurrencyFragment.addTextListener(viewModel: ConvertCurrencyViewModel){
	binding.currencyInput.addTextChangedListener(object : TextWatcher {
		override fun afterTextChanged(s: Editable) {}

		override fun beforeTextChanged(s: CharSequence, start: Int,count: Int, after: Int) {}

		override fun onTextChanged(s: CharSequence, start: Int,before: Int, count: Int) {
			parseText(s,viewModel)
		}
	})
}

private fun parseText(s:CharSequence,viewModel: ConvertCurrencyViewModel){
	if(s.isNotEmpty()){
		viewModel.onNumberSet(Integer.parseInt(s.toString()))
	} else {
		viewModel.onNumberSet(0)
	}
}