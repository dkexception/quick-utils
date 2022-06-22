package com.dkexception.quickutils.base

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.dkexception.quickutils.ui.commoncomposables.QuickUtilsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen(
    content: @Composable (
        snackbarHostState: SnackbarHostState
    ) -> Unit,
    navController: NavController,
    screenTitle: String
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            QuickUtilsTopAppBar(
                title = screenTitle,
                optionalOnBackClickAction = navController.previousBackStackEntry?.let {
                    {
                        navController.navigateUp()
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            content(
                snackbarHostState = snackbarHostState
            )
        }
    }
}
