package com.d121211004.store.data.repository

import com.d121211004.store.data.models.Product
import com.d121211004.store.data.response.GetProductResponse
import com.d121211004.store.data.source.remote.ApiService

class ProductsRepository (private val apiService: ApiService) {

    suspend fun getProducts(): List<Product> {
        return apiService.getProducts()
    }
}