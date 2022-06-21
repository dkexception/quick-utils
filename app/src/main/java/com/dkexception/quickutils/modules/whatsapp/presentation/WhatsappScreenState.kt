package com.dkexception.quickutils.modules.whatsapp.presentation

import com.dkexception.quickutils.base.BaseScreenState
import com.dkexception.quickutils.base.SnackbarState
import com.dkexception.quickutils.modules.whatsapp.domain.model.WhatsappListDataModel
import com.dkexception.quickutils.utils.UiText

data class WhatsappScreenState(
	
	val userEnteredPhone: String = "",
	val userEnteredText: String = "",
	val items: List<WhatsappListDataModel> = listOf(),
	
	override val isLoading: Boolean = false,
	override val errorMessage: UiText? = null,
	override val snackbarState: SnackbarState = SnackbarState()

) : BaseScreenState
