package com.dkexception.quickutils.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dkexception.quickutils.data.local.entities.WhatsappItemsEntity

@Dao
interface WhatsappItemsDao {
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertWhatsappItems(
		whatsappItemEntities: List<WhatsappItemsEntity>
	)
	
	@Query("DELETE FROM WhatsappItemsEntity WHERE id = :id")
	suspend fun deleteWhatsappItemEntityForId(id: Int)
	
	@Query("SELECT * FROM WhatsappItemsEntity ORDER BY id ASC")
	suspend fun getAllWhatsappItemEntities(): List<WhatsappItemsEntity>?
	
	@Query("SELECT * FROM WhatsappItemsEntity WHERE id = :id")
	suspend fun getWhatsappEntityById(id: Int): WhatsappItemsEntity?
}
