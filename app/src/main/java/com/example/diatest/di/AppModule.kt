package com.example.diatest.di

import dagger.Module

@Module(
    includes = [UseCaseModule::class, RepositoryModule::class]
)
object AppModule