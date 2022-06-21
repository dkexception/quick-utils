package com.dkexception.quickutils.modules.whatsapp.data.repository

import com.dkexception.quickutils.R
import com.dkexception.quickutils.data.local.dao.WhatsappItemsDao
import com.dkexception.quickutils.modules.whatsapp.data.mapper.toWhatsappItemsEntity
import com.dkexception.quickutils.modules.whatsapp.data.mapper.toWhatsappListDataModel
import com.dkexception.quickutils.di.IoDispatcher
import com.dkexception.quickutils.modules.whatsapp.domain.model.WhatsappListDataModel
import com.dkexception.quickutils.modules.whatsapp.domain.repository.WhatsappItemsRepository
import com.dkexception.quickutils.utils.Resource
import com.dkexception.quickutils.utils.Resource.Loading
import com.dkexception.quickutils.utils.UiText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WhatsappItemsRepositoryImpl @Inject constructor(
	private val whatsappItemsDao: WhatsappItemsDao,
	@IoDispatcher private val dispatcher: CoroutineDispatcher
) : WhatsappItemsRepository {
	
	override fun getAllWhatsappItems(): Flow<Resource<List<WhatsappListDataModel>>> = flow {
		emit(Loading(true))
		
		try {
			val dataItems = whatsappItemsDao.getAllWhatsappItemEntities()
			emit(Resource.Success(dataItems.orEmpty().map { it.toWhatsappListDataModel() }))
		} catch (e: Exception) {
			emit(Resource.Error(message = UiText.StringResource(R.string.generic_error), listOf()))
		}
		
		emit(Loading(false))
	}
	
	override suspend fun deleteWhatsappItemById(
		id: Int
	): Resource<WhatsappListDataModel?> = withContext(dispatcher) {
		try {
			val entity = whatsappItemsDao.getWhatsappEntityById(id)
			if (entity != null) {
				whatsappItemsDao.deleteWhatsappItemEntityForId(id)
				Resource.Success(entity.toWhatsappListDataModel())
			} else
				Resource.Error(UiText.GenericError, null)
		} catch (e: Exception) {
			Resource.Error(
				message = UiText.StringResource(R.string.whatsapp_entity_deletion_failed),
				data = null
			)
		}
	}
	
	override suspend fun addWhatsappItems(
		whatsappListDataModels: List<WhatsappListDataModel>
	): Resource<Unit> = withContext(dispatcher) {
		try {
			whatsappItemsDao.insertWhatsappItems(
				whatsappListDataModels.map { it.toWhatsappItemsEntity() }
			)
			Resource.Success(Unit)
		} catch (e: Exception) {
			Resource.Error(data = Unit)
		}
	}
}
