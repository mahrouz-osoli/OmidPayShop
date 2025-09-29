package com.example.omidpayshop.presentation.ViewModel

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omidpayshop.data.Model.Product.ProductResponseModel
import com.example.omidpayshop.data.Repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.*

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel(){
    private val _products = MutableStateFlow<List<ProductResponseModel>>(emptyList())
    val products: StateFlow<List<ProductResponseModel>> = _products

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _searchField = MutableStateFlow(TextFieldValue(""))
    val searchField: StateFlow<TextFieldValue> = _searchField

    val filteredProducts: StateFlow<List<ProductResponseModel>> = combine(_products, _searchField) { products, query ->
        if (query.text.isBlank()) products
        else products.filter { it.title.contains(query.text, ignoreCase = true) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val productList = repository.getProducts()
//                _products.value = productList.sortedBy { it.price }
                _products.value = productList
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
            _isLoading.value = false
        }
    }

    fun updateSearchField(field: TextFieldValue){
        _searchField.value = field
    }

}