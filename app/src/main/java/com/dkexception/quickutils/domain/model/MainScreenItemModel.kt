package com.dkexception.quickutils.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.dkexception.quickutils.R

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
                navigationRouteName = "whatsapp"
            )
        )
    }
}
