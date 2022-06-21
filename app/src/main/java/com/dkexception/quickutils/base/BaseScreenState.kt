package com.dkexception.quickutils.base

import com.dkexception.quickutils.utils.UiText

interface BaseScreenState {
	val isLoading: Boolean
	val errorMessage: UiText?
	val snackbarState: SnackbarState
}

data class SnackbarState(
	val message: UiText? = null,
	val optionalActionLabel: UiText? = null,
	val isDismissed: Boolean = true
)
