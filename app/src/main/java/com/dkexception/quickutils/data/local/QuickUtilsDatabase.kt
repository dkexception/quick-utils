package com.dkexception.quickutils.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dkexception.quickutils.data.local.dao.DelhiveryItemsDao
import com.dkexception.quickutils.data.local.dao.WhatsappItemsDao
import com.dkexception.quickutils.data.local.entities.DelhiveryDataEntity
import com.dkexception.quickutils.data.local.entities.DelhiveryItemsEntity
import com.dkexception.quickutils.data.local.entities.WhatsappItemsEntity
import com.dkexception.quickutils.data.local.typeconverters.DelhiveryTypeConverters

@Database(
    entities = [
        WhatsappItemsEntity::class,
        DelhiveryDataEntity::class,
        DelhiveryItemsEntity::class
    ],
    version = 1
)
@TypeConverters(
    DelhiveryTypeConverters::class
)
abstract class QuickUtilsDatabase : RoomDatabase() {

    abstract val whatsappItemsDao: WhatsappItemsDao
    abstract val delhiveryItemsDao: DelhiveryItemsDao
}
