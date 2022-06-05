package com.dkexception.quickutils.presentation.whatsapp_launcher

import com.dkexception.quickutils.domain.model.WhatsappListDataModel

data class WhatsappScreenState(
    val items: List<WhatsappListDataModel> = listOf(),
    val isLoading: Boolean = false
)
