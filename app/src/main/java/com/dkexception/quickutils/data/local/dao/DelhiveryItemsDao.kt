package com.dkexception.quickutils.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dkexception.quickutils.data.local.entities.DelhiveryDataEntity
import com.dkexception.quickutils.data.local.entities.DelhiveryItemsEntity

@Dao
interface DelhiveryItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDelhiveryItems(
        delhiveryItemsEntities: List<DelhiveryItemsEntity>
    )

    @Query("DELETE FROM DelhiveryItemsEntity WHERE wbn = :wbn")
    suspend fun deleteDelhiveryItemEntityForWaybillNumber(wbn: String)

    @Query("SELECT * FROM DelhiveryItemsEntity ORDER BY creation ASC")
    suspend fun getAllDelhiveryEntities(): List<DelhiveryItemsEntity>?

    @Query("SELECT * FROM DelhiveryItemsEntity WHERE wbn = :wbn")
    suspend fun getDelhiveryEntityById(wbn: String): DelhiveryItemsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDelhiveryData(
        delhiveryDataEntity: DelhiveryDataEntity
    )

    @Query("SELECT * FROM DelhiveryDataEntity WHERE wbn = :wbn")
    suspend fun getShipmentDataByWbn(wbn: String): DelhiveryDataEntity?
}
