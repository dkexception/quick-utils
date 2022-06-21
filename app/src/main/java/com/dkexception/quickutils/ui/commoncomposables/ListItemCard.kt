package com.dkexception.quickutils.ui.commoncomposables

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dkexception.quickutils.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ListItemCard(
	key: Int,
	modifier: Modifier,
	title: String,
	optionalSubtitle: String? = null,
	optionalOnClickAction: ((Int) -> Unit)? = null,
	optionalOnSecondaryButtonAction: ((Int) -> Unit)? = null
) {
	
	Column(modifier) {
		Card(
			modifier = Modifier
				.fillMaxWidth()
				.wrapContentHeight(),
			elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
			onClick = {
				optionalOnClickAction?.invoke(key)
			},
			colors = CardDefaults.cardColors(
				containerColor = MaterialTheme.colorScheme.surface
			)
		) {
			Row(
				modifier = Modifier.fillMaxWidth(),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				Column(
					modifier = Modifier.padding(16.dp),
					horizontalAlignment = Alignment.Start
				) {
					Text(text = title)
					optionalSubtitle.takeIf { !it.isNullOrBlank() }?.let { Text(text = it) }
				}
				IconButton(
					onClick = { optionalOnSecondaryButtonAction?.invoke(key) },
					colors = IconButtonDefaults.iconButtonColors(
						contentColor = MaterialTheme.colorScheme.error
					)
				) {
					Icon(Icons.Default.Delete, stringResource(id = R.string.delete))
				}
			}
			
		}
		Spacer(modifier = Modifier.height(10.dp))
	}
}
