package com.dkexception.quickutils.modules.delhivery.presentation.items

import com.dkexception.quickutils.base.BaseScreenState
import com.dkexception.quickutils.base.SnackbarState
import com.dkexception.quickutils.modules.delhivery.domain.model.DelhiveryListDataModel
import com.dkexception.quickutils.utils.UiText

data class DelhiveryItemsScreenState(

    val waybillNumber: String = "",
    val items: List<DelhiveryListDataModel> = listOf(),

    override val isLoading: Boolean = false,
    override val errorMessage: UiText? = null,
    override val snackbarState: SnackbarState = SnackbarState()
) : BaseScreenState()
