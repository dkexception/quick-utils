package com.dkexception.quickutils.base

import com.dkexception.quickutils.utils.UiText

abstract class BaseScreenState {
	abstract val isLoading: Boolean
	abstract val errorMessage: UiText?
	abstract val snackbarState: SnackbarState
}

data class SnackbarState(
	val message: UiText? = null,
	val optionalActionLabel: UiText? = null,
	val isDismissed: Boolean = true
)
