package com.dkexception.quickutils.modules.delhivery.domain.repository

import com.dkexception.quickutils.modules.delhivery.domain.model.DelhiveryListDataModel
import com.dkexception.quickutils.modules.delhivery.domain.model.DelhiveryShipmentDataModel
import com.dkexception.quickutils.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DelhiveryTrackingRepository {

    suspend fun addDelhiveryItems(
        delhiveryListDataModels: List<DelhiveryListDataModel>
    ): Resource<Unit>

    fun getAllDelhiveryItems(): Flow<Resource<List<DelhiveryListDataModel>>>

    suspend fun deleteDelhiveryItemById(waybillNumber: String): Resource<DelhiveryListDataModel?>

    fun getDelhiveryTrackingInfoForShipment(
        waybillNumber: String,
        shouldGetFromRemote: Boolean = false
    ): Flow<Resource<DelhiveryShipmentDataModel>>
}
