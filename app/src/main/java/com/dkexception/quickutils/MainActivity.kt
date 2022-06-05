package com.dkexception.quickutils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dkexception.quickutils.presentation.main_screen.MainScreen
import com.dkexception.quickutils.presentation.whatsapp_launcher.WhatsappLauncherScreen
import com.dkexception.quickutils.ui.theme.QuickUtilsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppContent() = QuickUtilsTheme {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = "home"
    ) {
        composable("home") {
            MainScreen(navHostController)
        }
        composable("whatsapp") {
            WhatsappLauncherScreen()
        }
    }
}
