package com.app.basics.daggerhilt.ui

import androidx.appcompat.app.AppCompatActivity
import com.app.basics.daggerhilt.DaggerHiltApp
import com.app.basics.daggerhilt.di.activity.ActivityModule

open class BaseActivity : AppCompatActivity() {

    private val appComponent get() = (application as DaggerHiltApp).coreComponent

    private val activityComponent by lazy {
        appComponent.provideActivityComponent(
            ActivityModule
        )
    }

    protected val injector get() = activityComponent

}