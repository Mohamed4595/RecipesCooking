package com.mhmd.recipescooking.business.domain.model

import java.util.*

/**
 * See Recipe example: https://food2fork.ca/
 */
data class Recipe(
    val id: Int,
    val title: String?,
    val publisher: String?,
    val featuredImage: String?,
    val rating: Int?,
    val sourceUrl: String?,
    val description: String?,
    val cookingInstructions: String?,
    val ingredients: List<String> = listOf(),
    val dateAdded: Date,
    val dateUpdated: Date,
)