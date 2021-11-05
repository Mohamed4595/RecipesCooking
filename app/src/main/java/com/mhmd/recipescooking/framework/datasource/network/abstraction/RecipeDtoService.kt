package com.mhmd.recipescooking.framework.datasource.network.abstraction

import com.mhmd.recipescooking.framework.datasource.network.model.RecipeDto
import com.mhmd.recipescooking.framework.datasource.network.RecipeSearchResponse

interface RecipeDtoService {

    suspend fun search(
        page: Int,
        query: String
    ): RecipeSearchResponse

    suspend fun get(
        id: Int
    ): RecipeDto?


}