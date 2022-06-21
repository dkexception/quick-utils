package com.dkexception.quickutils.modules.delhivery.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dkexception.quickutils.base.BaseViewModel
import com.dkexception.quickutils.modules.delhivery.domain.repository.DelhiveryTrackingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DelhiveryTrackingViewModel @Inject constructor(
	private val delhiveryTrackingRepository: DelhiveryTrackingRepository
) : BaseViewModel<DelhiveryScreenState>() {
	
	override var state: DelhiveryScreenState by mutableStateOf(DelhiveryScreenState())
	
}
