package com.dkexception.quickutils.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dkexception.quickutils.modules.delhivery.data.remote.dto.DelhiveryTrackingDto

@Entity
data class DelhiveryDataEntity(

    @PrimaryKey
    val wbn: String,

    val shipmentInfo: DelhiveryTrackingDto?
)
