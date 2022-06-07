package com.dkexception.quickutils.domain.repository

import com.dkexception.quickutils.domain.model.WhatsappListDataModel
import com.dkexception.quickutils.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WhatsappItemsRepository {
	
	fun getAllWhatsappItems(): Flow<Resource<List<WhatsappListDataModel>>>
	
	suspend fun deleteWhatsappItemById(id: Int): Resource<WhatsappListDataModel?>
	
	suspend fun addWhatsappItems(
		whatsappListDataModels: List<WhatsappListDataModel>
	): Resource<Unit>
}
