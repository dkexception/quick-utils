package com.dkexception.quickutils.presentation.whatsapp_launcher

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dkexception.quickutils.ui.composables.QuickUtilsEditText
import com.dkexception.quickutils.ui.composables.QuickUtilsTopAppBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatsappLauncherScreen(
    viewModel: WhatsappLauncherViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { QuickUtilsTopAppBar(title = "Whatsapp Launcher") }
    ) {

        val context = LocalContext.current
        val state = viewModel.state

        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                QuickUtilsEditText(
                    modifier = Modifier.fillMaxWidth(),
                    optionalKeyboardType = KeyboardType.Phone,
                    optionalInitialValue = viewModel.latestTypedValue,
                    optionalHint = "Enter a number with ISD code"
                ) {
                    viewModel.latestTypedValue = it
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        viewModel.onConfirmClicked(context)?.let {
                            scope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                        }
                    }
                ) {
                    Text(text = "Launch")
                }
            }
        }
    }
}
