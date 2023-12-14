package com.d121211004.store.data.source.remote

import com.d121211004.store.data.models.Product
import com.d121211004.store.data.response.GetProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //https://fakestoreapi.com/products
    @GET("products")
    suspend fun getProducts(): List<Product>
}