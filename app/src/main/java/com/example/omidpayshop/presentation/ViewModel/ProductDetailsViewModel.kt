package com.example.omidpayshop.presentation.ViewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omidpayshop.data.Model.Entity.ProductEntity
import com.example.omidpayshop.data.Repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private val prodouctId: Int  = savedStateHandle["productId"] ?: 0

    val product: StateFlow<ProductEntity?> = repository.getProductByIdFromDb(prodouctId)
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
}