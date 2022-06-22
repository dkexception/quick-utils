package com.dkexception.quickutils.modules.delhivery.presentation.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.dkexception.quickutils.R
import com.dkexception.quickutils.ui.commoncomposables.ListItemCard

@OptIn(ExperimentalUnitApi::class)
@Composable
fun DelhiveryItemsScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    viewModel: DelhiveryItemsListViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.waybillNumber,
            onValueChange = viewModel::onWaybillNumberChanged,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Phone,
                capitalization = KeyboardCapitalization.Sentences,
            ),
            placeholder = {
                Text(stringResource(R.string.delhivery_enter_waybill_number_hint))
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.onConfirmClicked(navController) }
        ) {
            Text(text = stringResource(R.string.delhivery_get_details))
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (state.items.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.whatsapp_recently_used_numbers),
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
                    Text(stringResource(id = R.string.delhivery_no_recently_added_parcels))
                }
                else -> {
                    LazyColumn {
                        itemsIndexed(items = state.items) { index, item ->
                            ListItemCard(
                                key = index,
                                modifier = Modifier.fillMaxWidth(),
                                title = item.wbn,
                                optionalOnClickAction = {
                                    viewModel.onListItemClicked(navController, item.wbn)
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
