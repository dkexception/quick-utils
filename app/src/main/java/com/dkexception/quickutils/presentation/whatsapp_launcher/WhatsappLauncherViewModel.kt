package com.dkexception.quickutils.presentation.whatsapp_launcher

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dkexception.quickutils.R
import com.dkexception.quickutils.domain.model.WhatsappListDataModel
import com.dkexception.quickutils.domain.repository.WhatsappItemsRepository
import com.dkexception.quickutils.utils.QuickUtilsConstants
import com.dkexception.quickutils.utils.Resource.*
import com.dkexception.quickutils.utils.UiText
import com.dkexception.quickutils.utils.extensions.sanitizeWhatsappNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhatsappLauncherViewModel @Inject constructor(
	private val whatsappItemsRepository: WhatsappItemsRepository
) : ViewModel() {
	
	var state by mutableStateOf(WhatsappScreenState())
		private set
	
	private var lastDeletedItem: WhatsappListDataModel? = null
	private var snackBarJob: Job? = null
	
	init {
		viewModelScope.launch {
			updateList()
		}
	}
	
	fun onPhoneNumberChange(newPhone: String) {
		if (newPhone.length > 20) return
		state = state.copy(userEnteredPhone = newPhone)
	}
	
	fun onTextChange(newText: String) {
		state = state.copy(userEnteredText = newText)
	}
	
	fun onConfirmClicked(
		context: Context,
		relevantValue: String = state.userEnteredPhone.sanitizeWhatsappNumber(),
		relevantText: String = state.userEnteredText,
		shouldSave: Boolean = true
	) {
		try {
			if (relevantValue.isBlank()) {
				showSnackbar(
					message = UiText.StringResource(R.string.whatsapp_launcher_error_data)
				)
			} else {
				launchWhatsappForNumberAndText(
					context = context,
					numberString = relevantValue,
					text = relevantText
				)
				if (shouldSave) {
					viewModelScope.launch {
						saveEntity()
						updateList()
					}
				}
			}
		} catch (e: Exception) {
			showSnackbar(
				message = e.localizedMessage?.let(UiText::DynamicString) ?: run {
					UiText.GenericError
				}
			)
		}
	}
	
	private fun launchWhatsappForNumberAndText(
		context: Context,
		numberString: String,
		text: String = ""
	) {
		context.startActivity(
			Intent(Intent.ACTION_VIEW).apply {
				data = Uri.parse(
					QuickUtilsConstants.WHATSAPP_LAUNCH_URL.format(
						numberString,
						text
					)
				)
			}
		)
	}
	
	private suspend fun saveEntity() {
		whatsappItemsRepository.addWhatsappItems(
			listOf(
				WhatsappListDataModel(
					phoneNumberString = state.userEnteredPhone.sanitizeWhatsappNumber(),
					optionalMessage = state.userEnteredText
				)
			)
		)
	}
	
	private suspend fun updateList() {
		whatsappItemsRepository.getAllWhatsappItems().collect { result ->
			when (result) {
				is Error -> {
					state = state.copy(errorMessage = result.message)
				}
				is Loading -> {
					state = state.copy(isLoading = result.isLoading)
				}
				is Success -> {
					result.data?.let {
						state = state.copy(items = it)
					}
				}
			}
		}
	}
	
	fun onListItemClicked(
		context: Context,
		itemKey: Int
	) {
		
		val relevantValue = state.items
			.find { it.uniqueKey == itemKey }?.phoneNumberString
			?.sanitizeWhatsappNumber().orEmpty()
		
		val relevantText = state.items
			.find { it.uniqueKey == itemKey }?.optionalMessage.orEmpty()
		
		onConfirmClicked(
			context = context,
			relevantValue = relevantValue,
			relevantText = relevantText,
			shouldSave = false
		)
	}
	
	fun onItemDeletionRequested(
		itemKey: Int
	) = viewModelScope.launch {
		when (val value = whatsappItemsRepository.deleteWhatsappItemById(itemKey)) {
			is Error -> {
				showSnackbar(value.message)
				updateList()
			}
			is Success -> {
				lastDeletedItem = value.data
				showSnackbar(
					message = UiText.StringResource(R.string.whatsapp_entity_deletion_success),
					optionalActionLabel = UiText.StringResource(R.string.undo)
				)
				updateList()
			}
			is Loading -> Unit
		}
	}
	
	fun onItemDeletionUndoRequested() = viewModelScope.launch {
		dismissCurrentSnackbar()
		lastDeletedItem?.let {
			when (val value = whatsappItemsRepository.addWhatsappItems(listOf(it))) {
				is Error -> {
					showSnackbar(value.message)
					updateList()
				}
				else -> updateList()
			}
			updateList()
		}
	}
	
	private fun showSnackbar(
		message: UiText,
		optionalActionLabel: UiText? = null,
		optionalActionToPerformAfterSnackbarAutoDismissal: (suspend () -> Unit)? = null
	) {
		dismissCurrentSnackbar()
		snackBarJob = viewModelScope.launch {
			state = state.copy(
				snackbarState = SnackbarState(
					message = message,
					optionalActionLabel = optionalActionLabel,
					isDismissed = false
				)
			)
			delay(5000)
			optionalActionToPerformAfterSnackbarAutoDismissal?.invoke()
			dismissCurrentSnackbar()
		}
	}
	
	private fun dismissCurrentSnackbar() {
		snackBarJob?.cancel()
		state = state.copy(
			snackbarState = SnackbarState(
				message = null,
				isDismissed = true
			)
		)
	}
}
