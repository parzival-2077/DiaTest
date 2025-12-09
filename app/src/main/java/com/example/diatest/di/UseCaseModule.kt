package com.example.diatest.di

import com.example.domain.repository.ProductRepository
import com.example.domain.usecase.AddProductInCartUseCase
import com.example.domain.usecase.AddProductUseCase
import com.example.domain.usecase.GetAllProductsInCartUseCase
import com.example.domain.usecase.GetAllProductsUseCase
import dagger.Module
import dagger.Provides

@Module
object UseCaseModule {

    @Provides
    fun provideAddProductUseCase(repository: ProductRepository): AddProductUseCase =
        AddProductUseCase(repository)

    @Provides
    fun provideAddProductInCartUseCase(repository: ProductRepository): AddProductInCartUseCase =
        AddProductInCartUseCase(repository)

    @Provides
    fun provideGetAllProductsUseCase(repository: ProductRepository): GetAllProductsUseCase =
        GetAllProductsUseCase(repository)

    @Provides
    fun provideGetAllProductsInCartUseCase(repository: ProductRepository): GetAllProductsInCartUseCase =
        GetAllProductsInCartUseCase(repository)

}