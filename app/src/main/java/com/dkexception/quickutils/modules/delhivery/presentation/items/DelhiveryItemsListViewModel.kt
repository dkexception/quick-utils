package com.dkexception.quickutils.modules.delhivery.presentation.items

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.dkexception.quickutils.base.BaseViewModel
import com.dkexception.quickutils.modules.delhivery.domain.model.DelhiveryListDataModel
import com.dkexception.quickutils.modules.delhivery.domain.repository.DelhiveryTrackingRepository
import com.dkexception.quickutils.utils.QuickUtilsConstants
import com.dkexception.quickutils.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DelhiveryItemsListViewModel @Inject constructor(
    private val delhiveryTrackingRepository: DelhiveryTrackingRepository
) : BaseViewModel<DelhiveryItemsScreenState>() {

    override var state: DelhiveryItemsScreenState by mutableStateOf(DelhiveryItemsScreenState())

    init {
        viewModelScope.launch { updateList() }
    }

    fun onWaybillNumberChanged(newNumber: String) {
        state = state.copy(waybillNumber = newNumber)
    }

    fun onConfirmClicked(
        navController: NavController,
        waybillNumber: String = state.waybillNumber
    ) = viewModelScope.launch {

        if (waybillNumber.isBlank()) {
            return@launch
        }

        delhiveryTrackingRepository.getDelhiveryTrackingInfoForShipment(
            waybillNumber = waybillNumber
        ).collect { result ->
            when (result) {
                is Resource.Error -> {
                    state = state.copy(errorMessage = result.message)
                }
                is Resource.Loading -> {
                    state = state.copy(isLoading = result.isLoading)
                }
                is Resource.Success -> {
                    result.data?.let {
                        delhiveryTrackingRepository.addDelhiveryItems(
                            listOf(DelhiveryListDataModel(waybillNumber))
                        )
                        navController.navigate("${QuickUtilsConstants.ScreenRoutes.DELHIVERY_DATA_SCREEN_ROUTE}/$waybillNumber")
                        updateList()
                    }
                }
            }
        }
    }

    fun onListItemClicked(navController: NavController, waybillNumber: String) {
        navController.navigate("${QuickUtilsConstants.ScreenRoutes.DELHIVERY_DATA_SCREEN_ROUTE}/$waybillNumber")
    }

    fun onItemDeletionRequested(key: Int) = Unit

    private suspend fun updateList() {
        delhiveryTrackingRepository.getAllDelhiveryItems().collect { result ->
            when (result) {
                is Resource.Error -> {
                    state = state.copy(errorMessage = result.message)
                }
                is Resource.Loading -> {
                    state = state.copy(isLoading = result.isLoading)
                }
                is Resource.Success -> {
                    result.data?.let {
                        state = state.copy(items = it)
                    }
                }
            }
        }
    }
}
