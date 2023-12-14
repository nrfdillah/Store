package com.d121211004.store

import android.app.Application
import com.d121211004.store.data.AppContainer
import com.d121211004.store.data.DefaultAppContainer


class MyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}