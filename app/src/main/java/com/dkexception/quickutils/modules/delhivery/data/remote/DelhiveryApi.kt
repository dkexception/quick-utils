package com.dkexception.quickutils.modules.delhivery.data.remote

import com.dkexception.quickutils.modules.delhivery.data.remote.dto.DelhiveryTrackingDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DelhiveryApi {
	
	@GET("/track")
	suspend fun getTrackingInfoForShipment(
		@Query("wbn") waybillNumber: String
	): DelhiveryTrackingDto?
}
