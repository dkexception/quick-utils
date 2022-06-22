package com.dkexception.quickutils.modules.delhivery.presentation.datainfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DelhiveryDataScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: DelhiveryDataViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = state.delhiveryShipmentDataModel.toString())
    }
}
