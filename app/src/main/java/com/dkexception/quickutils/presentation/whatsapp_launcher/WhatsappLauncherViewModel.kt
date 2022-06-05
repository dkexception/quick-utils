package com.dkexception.quickutils.presentation.whatsapp_launcher

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.dkexception.quickutils.utils.QuickUtilsConstants
import com.dkexception.quickutils.R
import com.dkexception.quickutils.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WhatsappLauncherViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(WhatsappScreenState())

    var latestTypedValue by mutableStateOf("")

    fun onConfirmClicked(context: Context): UiText? = try {

        val relevantValue = latestTypedValue.replace(" ", "").replace("+", "")

        if (relevantValue.isBlank()) {
            UiText.StringResource(R.string.whatsapp_launcher_error_data)
        } else {
            context.startActivity(
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(
                        "${QuickUtilsConstants.WHATSAPP_LAUNCH_URL}$latestTypedValue"
                    )
                }
            )
            null
        }
    } catch (e: Exception) {
        e.localizedMessage?.let(UiText::DynamicString) ?: run {
            UiText.StringResource(R.string.generic_error)
        }
    }
}
