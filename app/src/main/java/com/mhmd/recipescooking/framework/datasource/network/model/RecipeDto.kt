package com.mhmd.recipescooking.framework.datasource.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(

    var pk: Int,

    var title: String?,

    var publisher: String?,

    var featured_image: String?,

    var rating: Int?,

    var source_url: String?,

    var description: String?,

    var cooking_instructions: String?,

    var ingredients: List<String>?,

    var long_date_added: Long?,

    var long_date_updated: Long?,
)
