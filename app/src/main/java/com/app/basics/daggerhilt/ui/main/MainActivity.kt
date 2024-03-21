package com.app.basics.daggerhilt.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.app.basics.daggerhilt.R
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.di.viewmodel.ViewModelFactory
import com.app.basics.daggerhilt.repo.QuestionsRepo
import com.app.basics.daggerhilt.ui.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var dispatcher: CoroutineDispatcherProvider

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val coroutineScope by lazy { CoroutineScope(dispatcher.mainDispatcher) }

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel =
            ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        coroutineScope.launch(dispatcher.ioDispatcher) {
            val list = viewModel.getQuestions()
            Log.v("Ramu", "List: $list")
        }
    }
}