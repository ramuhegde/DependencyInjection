package com.app.basics.daggerhilt.di.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class ViewModelKey(val kClass: KClass<out ViewModel>)
