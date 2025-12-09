package com.example.diatest.di

import com.example.data.di.RepositoryModule
import dagger.Module

@Module(
    includes = [UseCaseModule::class, RepositoryModule::class, ViewModelModule::class]
)
object AppModule