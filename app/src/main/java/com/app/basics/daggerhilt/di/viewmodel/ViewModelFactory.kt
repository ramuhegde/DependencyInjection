package com.app.basics.daggerhilt.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val viewModelMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = viewModelMap[modelClass]
        return provider?.get() as T
            ?: throw IllegalArgumentException("Unknown ViewModel class $modelClass")
    }
}