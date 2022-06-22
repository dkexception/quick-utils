package com.dkexception.quickutils.modules.delhivery.presentation.datainfo

import com.dkexception.quickutils.base.BaseScreenState
import com.dkexception.quickutils.base.SnackbarState
import com.dkexception.quickutils.modules.delhivery.domain.model.DelhiveryShipmentDataModel
import com.dkexception.quickutils.utils.UiText

data class DelhiveryDataScreenState(

    val delhiveryShipmentDataModel: DelhiveryShipmentDataModel? = null,

    override val isLoading: Boolean = false,
    override val errorMessage: UiText? = null,
    override val snackbarState: SnackbarState = SnackbarState()
) : BaseScreenState()
