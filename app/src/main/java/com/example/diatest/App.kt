package com.example.diatest

import android.app.Application
import android.content.Context
import com.example.diatest.di.AppComponent
import com.example.diatest.di.DaggerAppComponent
import javax.inject.Singleton

@Singleton
class App : Application() {

    val component by lazy {
        DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> component
        else -> this.applicationContext.appComponent
    }