package com.d121211004.store.data

import com.d121211004.store.data.repository.ProductsRepository
import com.d121211004.store.data.source.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val productsRepository: ProductsRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://fakestoreapi.com"

    val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val productsRepository : ProductsRepository
        get() = ProductsRepository(retrofitService)
}