package com.dkexception.quickutils.modules.whatsapp.data.mapper

import com.dkexception.quickutils.data.local.entities.WhatsappItemsEntity
import com.dkexception.quickutils.modules.whatsapp.domain.model.WhatsappListDataModel

fun WhatsappListDataModel.toWhatsappItemsEntity() = WhatsappItemsEntity(
	id = uniqueKey,
	phoneNumber = phoneNumberString,
	optionalMessage = optionalMessage
)

fun WhatsappItemsEntity.toWhatsappListDataModel() = WhatsappListDataModel(
	uniqueKey = id,
	phoneNumberString = phoneNumber,
	optionalMessage = optionalMessage
)
