package com.mhmd.recipescooking.framework.datasource.network

import com.mhmd.recipescooking.framework.datasource.network.model.RecipeDto
import kotlinx.serialization.Serializable

@Serializable
data class RecipeSearchResponse(

    var count: Int,
    var results: List<RecipeDto>,
)
