package com.dkexception.quickutils.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dkexception.quickutils.data.local.dao.WhatsappItemsDao
import com.dkexception.quickutils.data.local.entities.WhatsappItemsEntity

@Database(
    entities = [
        WhatsappItemsEntity::class
    ],
    version = 1
)
abstract class QuickUtilsDatabase : RoomDatabase() {

    abstract val whatsappItemsDao: WhatsappItemsDao
}
