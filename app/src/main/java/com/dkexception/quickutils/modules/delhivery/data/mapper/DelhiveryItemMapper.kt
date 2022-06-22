package com.dkexception.quickutils.modules.delhivery.data.mapper

import com.dkexception.quickutils.data.local.entities.DelhiveryDataEntity
import com.dkexception.quickutils.data.local.entities.DelhiveryItemsEntity
import com.dkexception.quickutils.modules.delhivery.data.remote.dto.DelhiveryTrackingDto
import com.dkexception.quickutils.modules.delhivery.domain.model.*

fun DelhiveryListDataModel.toDelhiveryItemsEntity() = DelhiveryItemsEntity(
    wbn = wbn
)

fun DelhiveryItemsEntity.toDelhiveryListDataModel() = DelhiveryListDataModel(
    wbn = wbn
)

fun DelhiveryTrackingDto.toDelhiveryDataEntity(
    waybillNumber: String
): DelhiveryDataEntity = DelhiveryDataEntity(
    wbn = waybillNumber,
    shipmentInfo = this
)

fun DelhiveryDataEntity.toDelhiveryShipmentDataModel(): List<DelhiveryShipmentDataModel>? {
    shipmentInfo?.delhiveryData ?: return null
    return shipmentInfo.delhiveryData.filterNotNull().map {
        if (it.awb == null) return null
        DelhiveryShipmentDataModel(
            shipmentId = it.awb,
            alternateReferenceIds = if (!it.referenceNo.isNullOrBlank()) listOf(it.referenceNo) else null,
            origin = "",
            originCompleteAddress = null,
            destiny = it.destination ?: "Unknown",
            destinyCompleteAddress = null,
            tentativeDeliveryDateTime = it.returnPromiseDeliveryDate,
            flow = when (it.flow) {
                "REVERSE" -> ShipmentDeliveryFlow.REVERSE
                else -> ShipmentDeliveryFlow.STRAIGHT
            },
            flowDirection = when (it.flowDirection) {
                "ONWARD" -> ShipmentDeliveryFlowDirection.ONWARD
                else -> ShipmentDeliveryFlowDirection.BACKWARD
            },
            deliveryType = when (it.packageType) {
                "PICKUP" -> ShipmentDeliveryType.PICKUP
                else -> ShipmentDeliveryType.DROP_OFF
            },
            sourceSystem = it.consignor,
            lifecycleEvents = it.scans?.filterNotNull()?.map { scan ->
                ShipmentLifecycleEvent(
                    eventDateTime = scan.scanDateTime,
                    shortDescription = scan.scanNslRemark,
                    eventLocation = scan.cityLocation
                )
            }
        )
    }
}
