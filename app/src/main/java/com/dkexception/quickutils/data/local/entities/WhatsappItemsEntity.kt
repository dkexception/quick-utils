package com.dkexception.quickutils.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WhatsappItemsEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val phoneNumber: String,
    val optionalMessage: String
)
