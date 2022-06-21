package com.dkexception.quickutils.ui.commoncomposables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCard(
	modifier: Modifier,
	itemText: String,
	@DrawableRes imageRes: Int,
	optionalTintColor: Color? = null,
	optionalOnClickAction: (() -> Unit)? = null
) {
	Column(
		modifier = modifier.padding(8.dp)
	) {
		Card(
			elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
			onClick = {
				optionalOnClickAction?.invoke()
			},
			colors = CardDefaults.cardColors(
				containerColor = MaterialTheme.colorScheme.surface
			)
		) {
			Column(
				modifier = Modifier.padding(16.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Image(
					modifier = Modifier.height(50.dp),
					painter = painterResource(imageRes),
					contentDescription = null,
					colorFilter = optionalTintColor?.let { ColorFilter.tint(it) }
				)
				Spacer(modifier = Modifier.height(20.dp))
				Text(
					text = itemText,
					textAlign = TextAlign.Center
				)
			}
		}
		Spacer(Modifier.height(5.dp))
	}
}
