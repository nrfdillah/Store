package com.d121211004.store.data.response

import com.d121211004.store.data.models.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetProductResponse(
    @SerialName("data")
    val data: List<Product>
)