package com.dkexception.quickutils.modules.whatsapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dkexception.quickutils.R.string
import com.dkexception.quickutils.ui.commoncomposables.ListItemCard

@OptIn(ExperimentalUnitApi::class)
@Composable
fun WhatsappLauncherScreen(
	paddingValues: PaddingValues,
	snackbarHostState: SnackbarHostState,
	viewModel: WhatsappLauncherViewModel = hiltViewModel()
) {
	val context = LocalContext.current
	val state = viewModel.state
	
	when {
		state.snackbarState.isDismissed -> {
			snackbarHostState.currentSnackbarData?.dismiss()
		}
		state.snackbarState.message != null -> {
			LaunchedEffect(snackbarHostState) {
				when (
					snackbarHostState.showSnackbar(
						message = state.snackbarState.message.asString(context),
						actionLabel = state.snackbarState.optionalActionLabel?.asString(context),
						duration = SnackbarDuration.Indefinite
					)
				) {
					SnackbarResult.ActionPerformed -> viewModel.onItemDeletionUndoRequested()
					SnackbarResult.Dismissed -> Unit
				}
			}
		}
	}
	
	Column(
		Modifier
			.fillMaxSize()
			.padding(paddingValues)
	) {
		Column(
			Modifier
				.fillMaxSize()
				.padding(16.dp)
		) {
			OutlinedTextField(
				modifier = Modifier.fillMaxWidth(),
				value = state.userEnteredPhone,
				onValueChange = viewModel::onPhoneNumberChange,
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Next,
					keyboardType = KeyboardType.Phone,
					capitalization = KeyboardCapitalization.Sentences,
				),
				placeholder = { Text(stringResource(string.whatsapp_enter_number_hint)) },
				singleLine = true
			)
			Spacer(modifier = Modifier.height(20.dp))
			OutlinedTextField(
				modifier = Modifier.fillMaxWidth(),
				value = state.userEnteredText,
				onValueChange = viewModel::onTextChange,
				keyboardOptions = KeyboardOptions(
					imeAction = ImeAction.Done,
					capitalization = KeyboardCapitalization.Sentences,
					autoCorrect = true
				),
				keyboardActions = KeyboardActions(
					onDone = {
						viewModel.onConfirmClicked(context)
					}
				),
				placeholder = { Text(stringResource(string.whatsapp_enter_text_hint)) }
			)
			Spacer(modifier = Modifier.height(20.dp))
			Button(
				modifier = Modifier.fillMaxWidth(),
				onClick = { viewModel.onConfirmClicked(context) }
			) {
				Text(text = stringResource(string.whatsapp_launch))
			}
			Spacer(modifier = Modifier.height(30.dp))
			if (state.items.isNotEmpty()) {
				Text(
					text = stringResource(id = string.whatsapp_recently_used_numbers),
					fontSize = TextUnit(20f, TextUnitType.Sp)
				)
				Spacer(modifier = Modifier.height(20.dp))
			}
			val alignment = when {
				state.isLoading || state.items.isEmpty() -> Alignment.Center
				else -> Alignment.TopCenter
			}
			Box(
				modifier = Modifier.fillMaxSize(),
				contentAlignment = alignment
			) {
				when {
					state.isLoading -> {
						CircularProgressIndicator()
					}
					state.items.isEmpty() -> {
						Text(stringResource(id = string.whatsapp_no_recently_used_numbers))
					}
					else -> {
						LazyColumn {
							items(items = state.items) { item ->
								ListItemCard(
									key = item.uniqueKey ?: 0,
									modifier = Modifier.fillMaxWidth(),
									title = item.phoneNumberString,
									optionalSubtitle = item.optionalMessage,
									optionalOnClickAction = { key ->
										viewModel.onListItemClicked(context, key)
									},
									optionalOnSecondaryButtonAction = { key ->
										viewModel.onItemDeletionRequested(key)
									}
								)
							}
						}
					}
				}
			}
		}
	}
}
