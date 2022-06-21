package com.dkexception.quickutils.modules.delhivery.presentation

import com.dkexception.quickutils.base.BaseScreenState
import com.dkexception.quickutils.base.SnackbarState
import com.dkexception.quickutils.utils.UiText

data class DelhiveryScreenState(
	
	
	
	override val isLoading: Boolean = false,
	override val errorMessage: UiText? = null,
	override val snackbarState: SnackbarState = SnackbarState()
) : BaseScreenState
