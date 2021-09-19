package ru.alexydenkov.converter.currencylist.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.alexydenkov.converter.databinding.ItemCurrencyBinding
import ru.alexydenkov.converter.shared.domain.entity.CurrencyInfoEntity

class CurrencyItemHolder(
	private val binding: ItemCurrencyBinding,
	private val currencySectionClick: (currency: CurrencyInfoEntity) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

	fun bind(item: CurrencyInfoEntity) {
		with(binding) {
			currencyTitle.text = item.name
			currencyValue.text = item.value.toString()
			currencyTitle.setOnClickListener {
				currencySectionClick(item)
			}
		}
	}

}
