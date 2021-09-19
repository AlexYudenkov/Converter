package ru.alexydenkov.converter.currencylist.ui

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.alexydenkov.converter.R
import ru.alexydenkov.converter.currencylist.presentation.CurrencyState
import ru.alexydenkov.converter.currencylist.presentation.CurrencyViewModel
import ru.alexydenkov.converter.currencylist.ui.adapter.CurrencyAdapter
import ru.alexydenkov.converter.databinding.RecyclerViewBinding
import ru.alexydenkov.converter.shared.domain.entity.CurrencyInfoEntity
import ru.alexydenkov.converter.shared.ext.launchWhenResumed

class CurrencyFragment : Fragment(), CurrencyViewModel.EventsListener {

	companion object {

		const val ITEM = "ITEM"
	}

	private var _binding: RecyclerViewBinding? = null
	private val binding
		get() = _binding ?: throw NullPointerException("Binding can't be null")

	private val viewModel: CurrencyViewModel by viewModel()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_binding = RecyclerViewBinding.inflate(inflater, container, false)
		initSwipeRefresh()
		initRV()
		bindState(viewModel.state)
		viewModel.fetchInfo()
		viewModel.eventsDispatcher.bind(viewLifecycleOwner, this)
		return _binding?.root
	}

	private fun initSwipeRefresh() {
		binding.swipeRefresh.setOnRefreshListener {
			viewModel.fetchInfo()
			binding.swipeRefresh.isRefreshing = false
		}
	}

	private fun bindState(state: Flow<CurrencyState>) {
		state.onEach {
			renderUiState(it)
		}.launchIn(viewLifecycleOwner.lifecycleScope)
	}

	private fun renderUiState(state: CurrencyState) {
		when (state) {
			is CurrencyState.Content -> renderContent()
			is CurrencyState.Loading -> renderLoading()
			is CurrencyState.Error   -> renderError()
		}
	}

	private fun renderError() {
		binding.aboutCompanyRv.visibility = View.GONE
		binding.loader.visibility = View.GONE
		binding.error.visibility = View.VISIBLE
	}

	private fun renderLoading() {
		val image = binding.loader
		image.setImageResource(R.drawable.ic_vector_anim_loader)
		(image.drawable as Animatable).start()
		binding.aboutCompanyRv.visibility = View.GONE
		binding.loader.visibility = View.VISIBLE
		binding.error.visibility = View.GONE
	}

	private fun renderContent() {
		binding.aboutCompanyRv.visibility = View.VISIBLE
		binding.loaderLayout.visibility = View.GONE
	}

	private fun initRV() {
		val adapter = CurrencyAdapter(viewModel::currencySectionClick)
		binding.aboutCompanyRv.adapter = adapter
		viewModel.state
			.filterIsInstance<CurrencyState.Content>()
			.onEach {
				adapter.submitList(it.information.currencyEntity)
			}
			.launchWhenResumed(viewLifecycleOwner.lifecycleScope)
	}

	override fun navigateToCurrencySection(item: CurrencyInfoEntity) {
		val bundle = bundleOf(ITEM to item.id)
		findNavController().navigate(R.id.blankFragment, bundle)
	}
}