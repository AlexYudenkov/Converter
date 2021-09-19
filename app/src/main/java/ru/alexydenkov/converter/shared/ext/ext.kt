package ru.alexydenkov.converter.shared.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

object Constants {

	const val ITEM = "ITEM"
}

fun <T> Flow<T>.launchWhenResumed(scope: LifecycleCoroutineScope): Job =
	scope.launchWhenResumed {
		collect()
	}

fun Fragment.getCurrencyOnNavigationResult(): String =
	arguments?.get(Constants.ITEM).toString()