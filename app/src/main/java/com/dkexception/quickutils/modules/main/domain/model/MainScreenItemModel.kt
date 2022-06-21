package com.dkexception.quickutils.modules.main.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.dkexception.quickutils.R
import com.dkexception.quickutils.utils.QuickUtilsConstants

data class MainScreenItemModel(
	@StringRes val text: Int,
	@DrawableRes val image: Int,
	val navigationRouteName: String,
	val optionalTintColor: Color? = null
) {
	companion object {
		
		fun getAllItems(): List<MainScreenItemModel> = listOf(
			MainScreenItemModel(
				text = R.string.whatsapp_launcher,
				image = R.drawable.ic_whatsapp,
				navigationRouteName = QuickUtilsConstants.ScreenRoutes.WHATSAPP_SCREEN_ROUTE,
				optionalTintColor = Color(QuickUtilsConstants.WHATSAPP_COLOR)
			),
			MainScreenItemModel(
				text = R.string.delhivery_tracking,
				image = R.drawable.delhivery_logo,
				navigationRouteName = QuickUtilsConstants.ScreenRoutes.DELHIVERY_SCREEN_ROUTE
			)
		)
	}
}
