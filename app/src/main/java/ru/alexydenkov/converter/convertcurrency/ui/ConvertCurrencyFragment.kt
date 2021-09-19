package ru.alexydenkov.converter.convertcurrency.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.alexydenkov.converter.R
import ru.alexydenkov.converter.convertcurrency.presentation.ConvertCurrencyState
import ru.alexydenkov.converter.convertcurrency.presentation.ConvertCurrencyViewModel
import ru.alexydenkov.converter.convertcurrency.presentation.ScreenStatus
import ru.alexydenkov.converter.databinding.ConverterFragmentBinding
import ru.alexydenkov.converter.shared.ext.getCurrencyOnNavigationResult
import ru.alexydenkov.converter.shared.ext.launchWhenResumed

class ConvertCurrencyFragment : Fragment(), ConvertCurrencyViewModel.EventsListener {

	private var _binding: ConverterFragmentBinding? = null
	internal val binding
		get() = _binding ?: throw NullPointerException("Binding can't be null")

	private val viewModel: ConvertCurrencyViewModel by viewModel()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = ConverterFragmentBinding.inflate(inflater, container, false)
		viewModel.eventsDispatcher.bind(viewLifecycleOwner, this)

		setSpinnerAdapter()
		bindState(viewModel.state)

		val currencyId = getCurrencyOnNavigationResult()

		setSwipeRefresh(viewModel,currencyId)
		viewModel.fetchInfo(currencyId)
		return binding.root
	}

	private fun setSpinnerAdapter() {
		val adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item)
		adapter.setDropDownViewResource(R.layout.spinner_item_dropdown)
		val spinner = binding.spinner
		spinner.adapter = adapter

		setOnSpinnerSelected(viewModel, spinner)
		submitList(viewModel, adapter)
	}

	private fun bindState(state: Flow<ConvertCurrencyState>) {
		state.onEach {
			renderUiState(it)
		}.launchIn(viewLifecycleOwner.lifecycleScope)
	}

	private fun renderUiState(state: ConvertCurrencyState) {
		when (state) {
			is ConvertCurrencyState.Content -> renderContent()
			ConvertCurrencyState.Loading    -> renderLoading()
			ConvertCurrencyState.Error      -> renderError()
		}
	}

	private fun renderError() {
		binding.content.visibility = View.GONE
		binding.loader.visibility = View.GONE
		binding.error.visibility = View.VISIBLE
	}

	private fun renderLoading() {
		binding.content.visibility = View.GONE
		binding.loader.visibility = View.VISIBLE
		binding.error.visibility = View.GONE
	}

	private fun renderContent() {
		binding.content.visibility = View.VISIBLE
		binding.loader.visibility = View.GONE
		binding.error.visibility = View.GONE

		renderScreenStatus()

		addTextListener(viewModel)
	}

	private fun renderScreenStatus() {
		viewModel.screenStatus.onEach {
			if (it is ScreenStatus.IsChosen) {
				binding.spinner.setSelection(it.itemIsChosen)
			}
		}.launchWhenResumed(viewLifecycleOwner.lifecycleScope)
	}

	override fun setResult(value: String) {
		binding.currencyValue.text = value
	}

}
