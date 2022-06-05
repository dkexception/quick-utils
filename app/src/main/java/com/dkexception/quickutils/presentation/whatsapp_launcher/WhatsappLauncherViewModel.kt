package com.dkexception.quickutils.presentation.whatsapp_launcher

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WhatsappLauncherViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(WhatsappScreenState())

    var latestTypedValue by mutableStateOf("")

    fun onConfirmClicked(context: Context): String? = try {

        val relevantValue = latestTypedValue.replace(" ", "").replace("+", "")

        if (relevantValue.isBlank()) {
            "Please enter a valid value"
        } else {
            context.startActivity(
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(
                        "https://api.whatsapp.com/send?phone=$latestTypedValue"
                    )
                }
            )
            null
        }
    } catch (e: Exception) {
        e.localizedMessage ?: "Something went wrong!"
    }
}
