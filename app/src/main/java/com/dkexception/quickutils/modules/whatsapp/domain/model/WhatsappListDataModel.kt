package com.dkexception.quickutils.modules.whatsapp.domain.model

data class WhatsappListDataModel(
	val phoneNumberString: String,
	val optionalMessage: String = "",
	val uniqueKey: Int? = null,
)
