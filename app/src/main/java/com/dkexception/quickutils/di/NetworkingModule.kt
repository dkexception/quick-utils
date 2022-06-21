package com.dkexception.quickutils.di

import com.dkexception.quickutils.BuildConfig
import com.dkexception.quickutils.modules.delhivery.data.remote.DelhiveryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {
	
	@Provides
	@Singleton
	fun provideOkHttpClient(): OkHttpClient = OkHttpClient
		.Builder()
		.addInterceptor(HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		}).build()
	
	@Provides
	@Singleton
	fun provideDelhiveryAPIService(
		okHttpClient: OkHttpClient
	): DelhiveryApi = Retrofit.Builder()
		.baseUrl(BuildConfig.delhiveryAPIBaseURL)
		.client(okHttpClient)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(DelhiveryApi::class.java)
}
