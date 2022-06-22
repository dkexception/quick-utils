package com.dkexception.quickutils.data.local.typeconverters

import androidx.room.TypeConverter
import com.dkexception.quickutils.modules.delhivery.data.remote.dto.DelhiveryTrackingDto
import com.google.gson.Gson

class DelhiveryTypeConverters {

    @TypeConverter
    fun getStringFromDelhiveryTrackingDto(value: DelhiveryTrackingDto?): String? {
        return value?.let {
            Gson().toJson(it)
        }
    }

    @TypeConverter
    fun getDelhiveryTrackingDtoFromString(value: String?): DelhiveryTrackingDto? {
        return Gson().fromJson(value, DelhiveryTrackingDto::class.java)
    }
}
