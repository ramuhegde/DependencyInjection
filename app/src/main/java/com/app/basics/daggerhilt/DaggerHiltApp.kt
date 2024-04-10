package com.app.basics.daggerhilt

import android.app.Application
import com.app.basics.daggerhilt.di.app.CoreComponent
import com.app.basics.daggerhilt.di.app.DaggerCoreComponent

class DaggerHiltApp : Application() {

    // Initialise the Core/App component in the application class
    // So that it can be accessed throughout the application lifecycle
    val coreComponent: CoreComponent by lazy {
        // DaggerCoreComponent is created/provided by Dagger annotation processing and code generation.
        DaggerCoreComponent.builder()
            .application(this)
            .build()
    }
}