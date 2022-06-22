package com.dkexception.quickutils.modules.delhivery.data.repository

import com.dkexception.quickutils.R
import com.dkexception.quickutils.data.local.dao.DelhiveryItemsDao
import com.dkexception.quickutils.data.local.entities.DelhiveryDataEntity
import com.dkexception.quickutils.di.IoDispatcher
import com.dkexception.quickutils.modules.delhivery.data.mapper.toDelhiveryDataEntity
import com.dkexception.quickutils.modules.delhivery.data.mapper.toDelhiveryItemsEntity
import com.dkexception.quickutils.modules.delhivery.data.mapper.toDelhiveryListDataModel
import com.dkexception.quickutils.modules.delhivery.data.mapper.toDelhiveryShipmentDataModel
import com.dkexception.quickutils.modules.delhivery.data.remote.DelhiveryApi
import com.dkexception.quickutils.modules.delhivery.data.remote.dto.DelhiveryTrackingDto
import com.dkexception.quickutils.modules.delhivery.domain.model.DelhiveryListDataModel
import com.dkexception.quickutils.modules.delhivery.domain.model.DelhiveryShipmentDataModel
import com.dkexception.quickutils.modules.delhivery.domain.repository.DelhiveryTrackingRepository
import com.dkexception.quickutils.utils.Resource
import com.dkexception.quickutils.utils.UiText
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DelhiveryTrackingRepositoryImpl @Inject constructor(
    private val delhiveryApi: DelhiveryApi,
    private val delhiveryItemsDao: DelhiveryItemsDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : DelhiveryTrackingRepository {

    override suspend fun addDelhiveryItems(
        delhiveryListDataModels: List<DelhiveryListDataModel>
    ): Resource<Unit> = withContext(dispatcher) {
        try {
            delhiveryItemsDao.insertDelhiveryItems(
                delhiveryListDataModels.map { it.toDelhiveryItemsEntity() }
            )
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(data = Unit)
        }
    }

    override fun getAllDelhiveryItems(): Flow<Resource<List<DelhiveryListDataModel>>> = flow {
        emit(Resource.Loading(true))

        try {
            val dataItems = delhiveryItemsDao.getAllDelhiveryEntities()
            emit(Resource.Success(dataItems.orEmpty().map { it.toDelhiveryListDataModel() }))
        } catch (e: Exception) {
            emit(Resource.Error(message = UiText.StringResource(R.string.generic_error), listOf()))
        }

        emit(Resource.Loading(false))
    }

    override suspend fun deleteDelhiveryItemById(
        waybillNumber: String
    ): Resource<DelhiveryListDataModel?> =
        try {
            val entity = delhiveryItemsDao.getDelhiveryEntityById(waybillNumber)
            if (entity != null) {
                delhiveryItemsDao.deleteDelhiveryItemEntityForWaybillNumber(waybillNumber)
                Resource.Success(entity.toDelhiveryListDataModel())
            } else
                Resource.Error(UiText.GenericError, null)
        } catch (e: Exception) {
            Resource.Error(
                message = UiText.StringResource(R.string.whatsapp_entity_deletion_failed),
                data = null
            )
        }

    override fun getDelhiveryTrackingInfoForShipment(
        waybillNumber: String,
        shouldGetFromRemote: Boolean
    ): Flow<Resource<DelhiveryShipmentDataModel>> = flow {
        emit(Resource.Loading(true))
        try {
            val cachedData: DelhiveryDataEntity? = delhiveryItemsDao.getShipmentDataByWbn(
                waybillNumber
            )

            val shouldCallApi =
                shouldGetFromRemote || (!shouldGetFromRemote && (cachedData == null))

            emit(
                if (shouldCallApi) {
                    getDelhiveryItemFromRemote(waybillNumber)
                } else {
                    getDelhiveryItemFromDatabase(waybillNumber)
                }
            )
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

    private suspend fun getDelhiveryItemFromDatabase(
        waybillNumber: String
    ): Resource<DelhiveryShipmentDataModel> {

        val cachedData: DelhiveryDataEntity? = delhiveryItemsDao.getShipmentDataByWbn(
            waybillNumber
        )

        val parcelDataItems: List<DelhiveryShipmentDataModel>? =
            cachedData?.toDelhiveryShipmentDataModel()

        val relevantParcel = parcelDataItems?.find {
            it.shipmentId.equals(waybillNumber, true)
        }

        return if (relevantParcel == null) {
            Resource.Error(
                message = UiText.GenericError,
                data = null
            )
        } else {
            Resource.Success(
                data = relevantParcel
            )
        }
    }

    private suspend fun getDelhiveryItemFromRemote(
        waybillNumber: String
    ): Resource<DelhiveryShipmentDataModel> = try {

        val dataFromDelhiveryApi: DelhiveryTrackingDto? = withContext(dispatcher) {
            delhiveryApi.getTrackingInfoForShipment(waybillNumber)
        }

        if (dataFromDelhiveryApi == null) {
            Resource.Error(
                message = UiText.GenericError,
                data = null
            )
        } else {

            delhiveryItemsDao.insertDelhiveryData(
                dataFromDelhiveryApi.toDelhiveryDataEntity(waybillNumber)
            )

            getDelhiveryItemFromDatabase(waybillNumber)
        }
    } catch (e: HttpException) {
        Resource.Error(
            message = UiText.DynamicString(e.message()),
            data = null
        )
    } catch (e: Exception) {
        Resource.Error(
            message = UiText.GenericError,
            data = null
        )
    }
}
