package com.example.diatest.di

import com.example.domain.repository.ProductRepository
import com.example.domain.usecase.AddProductUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {

    @Provides
    fun provideAddProductUseCase(repository: ProductRepository): AddProductUseCase = AddProductUseCase(repository)

}