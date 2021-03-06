package com.dkexception.quickutils.modules.main.presentation.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dkexception.quickutils.modules.main.domain.model.MainScreenItemModel
import com.dkexception.quickutils.ui.commoncomposables.ItemCard

@Composable
fun MainScreen(
    navController: NavController
) = Column(
	Modifier
		.fillMaxSize()
		.padding(vertical = 16.dp, horizontal = 8.dp)
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(items = MainScreenItemModel.getAllItems()) {
            ItemCard(
                modifier = Modifier
					.wrapContentWidth()
					.wrapContentHeight(),
                itemText = LocalContext.current.getString(it.text),
                imageRes = it.image,
                optionalTintColor = it.optionalTintColor,
                optionalOnClickAction = { navController.navigate(it.navigationRouteName) }
            )
        }
    }
}
