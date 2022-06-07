package com.dkexception.quickutils.utils

sealed class Resource<T>(
	val data: T? = null,
	val message: UiText = UiText.GenericError
) {
	
	class Success<T>(data: T?) : Resource<T>(
		data = data
	)
	
	class Error<T>(message: UiText = UiText.GenericError, data: T?) : Resource<T>(
		data = data,
		message = message
	)
	
	class Loading<T>(
		val isLoading: Boolean = true,
		message: UiText = UiText.GenericError
	) : Resource<T>(
		data = null,
		message = message
	)
}
