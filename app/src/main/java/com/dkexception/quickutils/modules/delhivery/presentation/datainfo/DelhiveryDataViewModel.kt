package com.dkexception.quickutils.modules.delhivery.presentation.datainfo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dkexception.quickutils.base.BaseViewModel
import com.dkexception.quickutils.modules.delhivery.domain.repository.DelhiveryTrackingRepository
import com.dkexception.quickutils.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DelhiveryDataViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val delhiveryTrackingRepository: DelhiveryTrackingRepository
) : BaseViewModel<DelhiveryDataScreenState>() {

    private val waybillNumber: String = savedStateHandle.get<String>("wbn").orEmpty()

    override var state: DelhiveryDataScreenState by mutableStateOf(DelhiveryDataScreenState())

    init {
        getDataForShipment()
    }

    fun getDataForShipment(
        fetchFromRemote: Boolean = false
    ) = viewModelScope.launch {
        delhiveryTrackingRepository.getDelhiveryTrackingInfoForShipment(
            waybillNumber = waybillNumber,
            shouldGetFromRemote = fetchFromRemote
        ).collect { result ->
            state = when (result) {
                is Resource.Error -> {
                    state.copy(errorMessage = result.message)
                }
                is Resource.Loading -> {
                    state.copy(isLoading = result.isLoading)
                }
                is Resource.Success -> {
                    state.copy(delhiveryShipmentDataModel = result.data)
                }
            }
        }
    }
}
