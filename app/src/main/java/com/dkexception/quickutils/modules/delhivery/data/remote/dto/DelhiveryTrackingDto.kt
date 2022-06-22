package com.dkexception.quickutils.modules.delhivery.data.remote.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class DelhiveryTrackingDto(
    @SerializedName("data")
    val delhiveryData: List<Data?>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("request_id")
    val requestId: String?,
    @SerializedName("statusCode")
    val statusCode: Int?
)

@Keep
data class Data(
    @SerializedName("awb")
    val awb: String?,
    @SerializedName("clt")
    val clt: String?,
    @SerializedName("consignee")
    val consignee: String?,
    @SerializedName("consignor")
    val consignor: String?,
    @SerializedName("containmentArea")
    val containmentArea: Boolean?,
    @SerializedName("covidZone")
    val covidZone: String?,
    @SerializedName("destination")
    val destination: String?,
    @SerializedName("returnPromiseDeliveryDate")
    val returnPromiseDeliveryDate: String?,
    @SerializedName("dispatchCenterId")
    val dispatchCenterId: String?,
    @SerializedName("flow")
    val flow: String?,
    @SerializedName("flowDirection")
    val flowDirection: String?,
    @SerializedName("hqStatus")
    val hqStatus: String?,
    @SerializedName("isAddressSpecific")
    val isAddressSpecific: Boolean?,
    @SerializedName("isInternational")
    val isInternational: Boolean?,
    @SerializedName("isPersonSpecific")
    val isPersonSpecific: Boolean?,
    @SerializedName("mcount")
    val mcount: Any?,
    @SerializedName("packageType")
    val packageType: String?,
    @SerializedName("productType")
    val productType: String?,
    @SerializedName("referenceNo")
    val referenceNo: String?,
    @SerializedName("scans")
    val scans: List<Scan?>?,
    @SerializedName("slot")
    val slot: Slot?,
    @SerializedName("srcDestCxRemarks")
    val srcDestCxRemarks: SrcDestCxRemarks?,
    @SerializedName("status")
    val status: Status?,
    @SerializedName("user_type")
    val userType: String?
)

@Keep
data class Scan(
    @SerializedName("cityLocation")
    val cityLocation: String?,
    @SerializedName("scan")
    val scan: String?,
    @SerializedName("scanDate")
    val scanDate: String?,
    @SerializedName("scanDateTime")
    val scanDateTime: String?,
    @SerializedName("scanNslRemark")
    val scanNslRemark: String?,
    @SerializedName("scanType")
    val scanType: String?,
    @SerializedName("setUndeliveredNsl")
    val setUndeliveredNsl: Boolean?,
    @SerializedName("tsUpdateNsl")
    val tsUpdateNsl: Boolean?
)

@Keep
class Slot

@Keep
data class SrcDestCxRemarks(
    @SerializedName("xnsl_present")
    val xnslPresent: Boolean?
)

@Keep
data class Status(
    @SerializedName("instructions")
    val instructions: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("statusDateTime")
    val statusDateTime: String?,
    @SerializedName("statusType")
    val statusType: String?
)
