package ru.alexydenkov.converter.currencylist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.alexydenkov.converter.databinding.ItemCurrencyBinding
import ru.alexydenkov.converter.shared.domain.entity.CurrencyInfoEntity

class CurrencyAdapter(
	private val currencySectionClick: (currency: CurrencyInfoEntity) -> Unit,
) : ListAdapter<CurrencyInfoEntity, CurrencyItemHolder>(CurrencyDiffUtil) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyItemHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val binding = ItemCurrencyBinding.inflate(layoutInflater, parent, false)
		return CurrencyItemHolder(binding, currencySectionClick)
	}

	override fun onBindViewHolder(holder: CurrencyItemHolder, position: Int) =
		holder.bind(getItem(position))
}

private object CurrencyDiffUtil : DiffUtil.ItemCallback<CurrencyInfoEntity>() {

	override fun areItemsTheSame(oldItem: CurrencyInfoEntity, newItem: CurrencyInfoEntity): Boolean =
		oldItem.id == newItem.id

	override fun areContentsTheSame(oldItem: CurrencyInfoEntity, newItem: CurrencyInfoEntity): Boolean =
		oldItem == newItem

}
