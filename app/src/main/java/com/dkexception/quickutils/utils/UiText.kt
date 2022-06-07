package com.dkexception.quickutils.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dkexception.quickutils.R

sealed class UiText {
	data class DynamicString(val value: String) : UiText()
	class StringResource(
		@StringRes val resId: Int,
		vararg val args: Any
	) : UiText()
	
	object GenericError : UiText()
	
	@Composable
	fun asString(): String {
		return when (this) {
			is DynamicString -> value
			is StringResource -> stringResource(resId, *args)
			GenericError -> stringResource(id = R.string.generic_error)
		}
	}
	
	fun asString(context: Context): String {
		return when (this) {
			is DynamicString -> value
			is StringResource -> context.getString(resId, *args)
			GenericError -> context.getString(R.string.generic_error)
		}
	}
}
