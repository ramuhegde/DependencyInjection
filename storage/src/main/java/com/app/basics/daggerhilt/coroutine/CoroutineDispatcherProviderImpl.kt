package com.app.basics.daggerhilt.di.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherProviderImpl : CoroutineDispatcherProvider {
    override val mainDispatcher: CoroutineDispatcher = Dispatchers.Main
    override val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    override val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
    override val unconfinedDispatcher: CoroutineDispatcher = Dispatchers.Unconfined
}