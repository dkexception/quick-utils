package com.dkexception.quickutils.di

import android.app.Application
import androidx.room.Room
import com.dkexception.quickutils.data.local.QuickUtilsDatabase
import com.dkexception.quickutils.data.local.dao.WhatsappItemsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
	
	@Provides
	@Singleton
	fun provideQuickUtilsDatabase(
		app: Application
	): QuickUtilsDatabase = Room.databaseBuilder(
		app,
		QuickUtilsDatabase::class.java,
		"quickutilsdb.db"
	).build()
	
	@Provides
	@Singleton
	fun provideWhatsappItemsDao(
		database: QuickUtilsDatabase
	): WhatsappItemsDao = database.whatsappItemsDao
}
