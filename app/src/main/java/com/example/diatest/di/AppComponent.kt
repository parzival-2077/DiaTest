package com.example.diatest.di

import com.example.diatest.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    fun inject(activity: MainActivity)

}