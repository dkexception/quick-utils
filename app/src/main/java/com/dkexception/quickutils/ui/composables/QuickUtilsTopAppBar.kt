package com.dkexception.quickutils.ui.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

@Composable
fun QuickUtilsTopAppBar(
	title: String
) = SmallTopAppBar(
	title = {
		Text(
			text = title,
			color = MaterialTheme.colorScheme.onPrimary
		)
	},
	colors = TopAppBarDefaults.smallTopAppBarColors(
		containerColor = MaterialTheme.colorScheme.primary
	)
)
