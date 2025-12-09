package com.example.data.di

import com.example.data.repository.ProductRepositoryImpl
import com.example.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

}