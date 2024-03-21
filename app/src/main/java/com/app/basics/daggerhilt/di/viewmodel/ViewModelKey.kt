package com.app.basics.daggerhilt.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

// This annotation is used to provide a key for the map of ViewModels
@MapKey
annotation class ViewModelKey(val kClass: KClass<out ViewModel>)
