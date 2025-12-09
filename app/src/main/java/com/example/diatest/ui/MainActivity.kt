package com.example.diatest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.diatest.appComponent
import com.example.diatest.di.ViewModelFactory
import com.example.diatest.ui.screen.main.MainScreen
import com.example.diatest.ui.theme.DiaTestTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        enableEdgeToEdge()
        setContent {
            DiaTestTheme {
                MainScreen(viewModelFactory = viewModelFactory)
            }
        }
    }

}