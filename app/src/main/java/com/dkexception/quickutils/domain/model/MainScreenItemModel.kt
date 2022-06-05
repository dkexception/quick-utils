package com.dkexception.quickutils.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dkexception.quickutils.R
import com.dkexception.quickutils.utils.QuickUtilsConstants

data class MainScreenItemModel(
    @StringRes val text: Int,
    @DrawableRes val image: Int,
    val navigationRouteName: String
) {
    companion object {

        fun getAllItems(): List<MainScreenItemModel> = listOf(
            MainScreenItemModel(
                text = R.string.whatsapp_launcher,
                image = R.drawable.ic_whatsapp,
                navigationRouteName = QuickUtilsConstants.ScreenRoutes.WHATSAPP_SCREEN_ROUTE
            )
        )
    }
}
