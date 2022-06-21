package com.dkexception.quickutils.ui.commoncomposables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun QuickUtilsTopAppBar(
	title: String,
	optionalOnBackClickAction: (() -> Unit)? = null
) = SmallTopAppBar(
	title = {
		Text(
			text = title,
			color = MaterialTheme.colorScheme.onPrimary
		)
	},
	colors = TopAppBarDefaults.smallTopAppBarColors(
		containerColor = MaterialTheme.colorScheme.primary
	),
	navigationIcon = {
		optionalOnBackClickAction?.let {
			IconButton(onClick = { it() }) {
				Icon(
					imageVector = Icons.Filled.ArrowBack,
					contentDescription = "Back",
					tint = MaterialTheme.colorScheme.onPrimary
				)
			}
		}
	}
)
