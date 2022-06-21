package com.dkexception.quickutils.modules.delhivery.presentation

import com.dkexception.quickutils.modules.delhivery.domain.repository.DelhiveryTrackingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DelhiveryTrackingViewModel @Inject constructor(
	private val delhiveryTrackingRepository: DelhiveryTrackingRepository
) {




}
