package com.dkexception.quickutils.di

import com.dkexception.quickutils.modules.delhivery.domain.repository.DelhiveryTrackingRepository
import com.dkexception.quickutils.modules.delhivery.domain.repository.DelhiveryTrackingRepositoryImpl
import com.dkexception.quickutils.modules.whatsapp.data.repository.WhatsappItemsRepositoryImpl
import com.dkexception.quickutils.modules.whatsapp.domain.repository.WhatsappItemsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
	
	@Binds
	@Singleton
	abstract fun provideWhatsappItemsRepository(
		whatsappItemsRepositoryImpl: WhatsappItemsRepositoryImpl
	): WhatsappItemsRepository
	
	@Binds
	@Singleton
	abstract fun provideDelhiveryTrackingRepository(
		delhiveryTrackingRepositoryImpl: DelhiveryTrackingRepositoryImpl
	): DelhiveryTrackingRepository
}
