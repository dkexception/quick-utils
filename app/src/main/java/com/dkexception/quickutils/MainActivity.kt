package com.dkexception.quickutils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dkexception.quickutils.base.BaseScreen
import com.dkexception.quickutils.modules.delhivery.presentation.datainfo.DelhiveryDataScreen
import com.dkexception.quickutils.modules.delhivery.presentation.items.DelhiveryItemsScreen
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
                content = { MainScreen(navController = navHostController) },
                navController = navHostController,
                screenTitle = stringResource(id = R.string.main_screen_title)
            )
        }
        composable(QuickUtilsConstants.ScreenRoutes.WHATSAPP_SCREEN_ROUTE) {
            BaseScreen(
                content = { hostState ->
                    WhatsappLauncherScreen(
                        snackbarHostState = hostState
                    )
                },
                navController = navHostController,
                screenTitle = stringResource(id = R.string.whatsapp_screen_title)
            )
        }
        composable(QuickUtilsConstants.ScreenRoutes.DELHIVERY_LIST_SCREEN_ROUTE) {
            BaseScreen(
                content = { hostState ->
                    DelhiveryItemsScreen(
                        snackbarHostState = hostState,
                        navController = navHostController,
                    )
                },
                navController = navHostController,
                screenTitle = stringResource(id = R.string.delhivery_screen_title)
            )
        }
        composable(
            route = "${QuickUtilsConstants.ScreenRoutes.DELHIVERY_DATA_SCREEN_ROUTE}/{wbn}",
            arguments = listOf(
                navArgument("wbn") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            BaseScreen(
                content = { hostState ->
                    DelhiveryDataScreen(
                        snackbarHostState = hostState
                    )
                },
                navController = navHostController,
                screenTitle = it.arguments?.getString("wbn")
                    ?: stringResource(id = R.string.delhivery_screen_title)
            )
        }
    }
}
