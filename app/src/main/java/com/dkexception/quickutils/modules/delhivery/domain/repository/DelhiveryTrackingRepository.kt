package com.dkexception.quickutils.modules.delhivery.domain.repository

import com.dkexception.quickutils.modules.delhivery.data.remote.dto.DelhiveryTrackingDto
import com.dkexception.quickutils.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DelhiveryTrackingRepository {
	
	fun getDelhiveryTrackingInfoForShipment(
		waybillNumber: String
	): Flow<Resource<DelhiveryTrackingDto>>
}
