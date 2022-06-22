package com.dkexception.quickutils.modules.delhivery.domain.model

data class DelhiveryShipmentDataModel(

    val shipmentId: String,
    val alternateReferenceIds: List<String>?,

    val origin: String,
    val originCompleteAddress: String?,

    val destiny: String,
    val destinyCompleteAddress: String?,
    val tentativeDeliveryDateTime: String?,

    val flow: ShipmentDeliveryFlow?,
    val flowDirection: ShipmentDeliveryFlowDirection?,
    val deliveryType: ShipmentDeliveryType?,
    val sourceSystem: String?,

    val lifecycleEvents: List<ShipmentLifecycleEvent>?
)

enum class ShipmentDeliveryFlow {
    STRAIGHT,
    REVERSE
}

enum class ShipmentDeliveryFlowDirection {
    ONWARD,
    BACKWARD
}

enum class ShipmentDeliveryType {
    DROP_OFF,
    PICKUP
}

data class ShipmentLifecycleEvent(
    val eventDateTime: String?,
    val shortDescription: String?,
    val eventLocation: String?
)
