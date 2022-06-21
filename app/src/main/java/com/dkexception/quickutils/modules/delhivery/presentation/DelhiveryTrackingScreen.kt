package com.dkexception.quickutils.modules.delhivery.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.dkexception.quickutils.R
import com.dkexception.quickutils.ui.commoncomposables.QuickUtilsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DelhiveryTrackingScreen(

) = Scaffold(
	modifier = Modifier.fillMaxSize(),
	topBar = { QuickUtilsTopAppBar(title = stringResource(R.string.delhivery_screen_title)) },
	content = {
	
	}
)
