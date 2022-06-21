package com.dkexception.quickutils.modules.delhivery.domain.repository

import com.dkexception.quickutils.di.IoDispatcher
import com.dkexception.quickutils.modules.delhivery.data.remote.DelhiveryApi
import com.dkexception.quickutils.modules.delhivery.data.remote.dto.DelhiveryTrackingDto
import com.dkexception.quickutils.utils.Resource
import com.dkexception.quickutils.utils.UiText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DelhiveryTrackingRepositoryImpl @Inject constructor(
	private val delhiveryApi: DelhiveryApi,
	@IoDispatcher private val dispatcher: CoroutineDispatcher
) : DelhiveryTrackingRepository {
	
	override fun getDelhiveryTrackingInfoForShipment(
		waybillNumber: String
	): Flow<Resource<DelhiveryTrackingDto>> = flow {
		emit(Resource.Loading(true))
		
		try {
			val dataFromDelhiveryApi = delhiveryApi.getTrackingInfoForShipment(waybillNumber)
			emit(Resource.Success(dataFromDelhiveryApi))
		} catch (e: Exception) {
			emit(
				Resource.Error(
					message = e.localizedMessage?.let {
						UiText.DynamicString(it)
					} ?: UiText.GenericError,
					data = null
				)
			)
		}
		
		emit(Resource.Loading(false))
	}
}
