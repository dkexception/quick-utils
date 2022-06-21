package com.dkexception.quickutils.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<BS : BaseScreenState> : ViewModel() {
	
	abstract var state: BS
		protected set
}
