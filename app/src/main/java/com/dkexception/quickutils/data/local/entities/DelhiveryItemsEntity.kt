package com.dkexception.quickutils.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DelhiveryItemsEntity(

    @PrimaryKey
    val wbn: String,

    val creation: Long = System.currentTimeMillis()
)
