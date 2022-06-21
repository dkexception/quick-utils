package com.dkexception.quickutils.base

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
		paddingValues: PaddingValues,
		snackbarHostState: SnackbarHostState,
		navController: NavController
	) -> Unit,
	navController: NavController,
	@StringRes screenTitle: Int
) {
	val snackbarHostState = remember { SnackbarHostState() }
	
	Scaffold(
		modifier = Modifier.fillMaxSize(),
		snackbarHost = { SnackbarHost(snackbarHostState) },
		topBar = {
			QuickUtilsTopAppBar(
				title = stringResource(screenTitle),
				optionalOnBackClickAction = navController.previousBackStackEntry?.let {
					{
						navController.navigateUp()
					}
				}
			)
		},
		containerColor = MaterialTheme.colorScheme.surface
	) {
		content(
			paddingValues = it,
			snackbarHostState = snackbarHostState,
			navController = navController
		)
	}
}
