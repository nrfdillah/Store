package com.d121211004.store.ui.activities.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211004.store.MyApplication
import com.d121211004.store.data.models.Product
import com.d121211004.store.data.repository.ProductsRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MainUiState {
    data class Success(val product: List<Product>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class ProductViewModels(private val productsRepository: ProductsRepository): ViewModel() {

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getProducts() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val response = productsRepository.getProducts()
            mainUiState = MainUiState.Success(response.orEmpty())
        } catch (e: IOException) {
            mainUiState = MainUiState.Error
        }
    }

    init {
        getProducts()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val productsRepository = application.container.productsRepository
                ProductViewModels(productsRepository)
            }
        }
    }
}