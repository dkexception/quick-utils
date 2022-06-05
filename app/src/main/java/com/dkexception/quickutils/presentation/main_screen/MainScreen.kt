package com.dkexception.quickutils.presentation.main_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dkexception.quickutils.domain.model.MainScreenItemModel
import com.dkexception.quickutils.ui.composables.ItemCard
import com.dkexception.quickutils.ui.composables.QuickUtilsTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController
) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = { QuickUtilsTopAppBar(title = "Home") },
    content = {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = it
            ) {
                items(items = MainScreenItemModel.getAllItems()) {
                    ItemCard(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        itemText = LocalContext.current.getString(it.text),
                        imageRes = it.image,
                        optionalTintColor = Color(0xFF25D366),
                        optionalOnClickAction = { navController.navigate(it.navigationRouteName) }
                    )
                }
            }
        }
    }
)