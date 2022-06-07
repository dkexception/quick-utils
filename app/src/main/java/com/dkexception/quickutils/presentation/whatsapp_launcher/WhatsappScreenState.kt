package com.dkexception.quickutils.presentation.whatsapp_launcher

import com.dkexception.quickutils.domain.model.WhatsappListDataModel
import com.dkexception.quickutils.utils.UiText

data class WhatsappScreenState(
	val userEnteredPhone: String = "",
	val userEnteredText: String = "",
	val items: List<WhatsappListDataModel> = listOf(),
	val isLoading: Boolean = false,
	val errorMessage: UiText? = null,
	val snackbarState: SnackbarState = SnackbarState()
)

data class SnackbarState(
	val message: UiText? = null,
	val optionalActionLabel: UiText? = null,
	val isDismissed: Boolean = true
)
