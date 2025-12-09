package com.example.diatest.di

import androidx.lifecycle.ViewModel
import com.example.diatest.ui.screen.add_product.AddProductViewModel
import com.example.diatest.ui.screen.cart.CartViewModel
import com.example.diatest.ui.screen.history.HistoryViewModel
import com.example.diatest.ui.screen.products.ProductsViewModel
import com.example.diatest.ui.screen.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @ViewModelKey(ProductsViewModel::class)
    @IntoMap
    fun bindProductsViewModel(viewModel: ProductsViewModel): ViewModel

    @Binds
    @ViewModelKey(CartViewModel::class)
    @IntoMap
    fun bindCartViewModel(viewModel: CartViewModel): ViewModel

    @Binds
    @ViewModelKey(HistoryViewModel::class)
    @IntoMap
    fun bindHistoryViewModel(viewModel: HistoryViewModel): ViewModel

    @Binds
    @ViewModelKey(ProfileViewModel::class)
    @IntoMap
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @ViewModelKey(AddProductViewModel::class)
    @IntoMap
    fun bindAddProductViewModel(viewModel: AddProductViewModel): ViewModel

}