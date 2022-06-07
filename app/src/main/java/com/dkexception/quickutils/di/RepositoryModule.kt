package com.dkexception.quickutils.di

import com.dkexception.quickutils.data.repository.WhatsappItemsRepositoryImpl
import com.dkexception.quickutils.domain.repository.WhatsappItemsRepository
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
}
