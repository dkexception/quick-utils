package com.dkexception.quickutils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dkexception.quickutils.base.BaseScreen
import com.dkexception.quickutils.modules.delhivery.presentation.DelhiveryTrackingScreen
import com.dkexception.quickutils.modules.main.presentation.main_screen.MainScreen
import com.dkexception.quickutils.modules.whatsapp.presentation.WhatsappLauncherScreen
import com.dkexception.quickutils.ui.theme.QuickUtilsTheme
import com.dkexception.quickutils.utils.QuickUtilsConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent { AppContent() }
	}
}

@Preview(showBackground = true)
@Composable
fun AppContent() = QuickUtilsTheme {
	val navHostController = rememberNavController()
	NavHost(
		navController = navHostController,
		startDestination = QuickUtilsConstants.ScreenRoutes.MAIN_SCREEN_ROUTE
	) {
		composable(QuickUtilsConstants.ScreenRoutes.MAIN_SCREEN_ROUTE) {
			BaseScreen(
				content = { paddingValues, _, navController ->
					MainScreen(paddingValues = paddingValues, navController = navController)
				},
				navController = navHostController,
				screenTitle = R.string.main_screen_title
			)
		}
		composable(QuickUtilsConstants.ScreenRoutes.WHATSAPP_SCREEN_ROUTE) {
			BaseScreen(
				content = { paddingValues, hostState, _ ->
					WhatsappLauncherScreen(
						paddingValues = paddingValues,
						snackbarHostState = hostState
					)
				},
				navController = navHostController,
				screenTitle = R.string.whatsapp_screen_title
			)
		}
		composable(QuickUtilsConstants.ScreenRoutes.DELHIVERY_SCREEN_ROUTE) {
			BaseScreen(
				content = { paddingValues, hostState, _ ->
					DelhiveryTrackingScreen(
						paddingValues = paddingValues,
						snackbarHostState = hostState
					)
				},
				navController = navHostController,
				screenTitle = R.string.delhivery_screen_title
			)
		}
	}
}
