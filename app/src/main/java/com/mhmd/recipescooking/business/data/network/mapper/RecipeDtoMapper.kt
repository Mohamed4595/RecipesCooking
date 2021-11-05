package com.mhmd.recipescooking.business.data.network.mapper

import com.mhmd.recipescooking.business.domain.model.Recipe
import com.mhmd.recipescooking.framework.datasource.network.model.RecipeDto
import com.mhmd.recipescooking.framework.datasource.utils.ModelMapper
import com.mhmd.recipescooking.utils.DateUtils

class RecipeDtoMapper : ModelMapper<RecipeDto, Recipe> {

    override fun mapToModel(model: RecipeDto): Recipe {
        return Recipe(
            id = model.pk,
            title = model.title,
            featuredImage = model.featured_image,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.source_url,
            description = model.description,
            cookingInstructions = model.cooking_instructions,
            ingredients = model.ingredients.orEmpty(),
            dateAdded = DateUtils.longToDate(model.long_date_added!!),
            dateUpdated = DateUtils.longToDate(model.long_date_updated!!),

            )
    }

    override fun mapFromModel(model: Recipe): RecipeDto {
        return RecipeDto(
            pk = model.id,
            title = model.title,
            featured_image = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            source_url = model.sourceUrl,
            description = model.description,
            cooking_instructions = model.cookingInstructions,
            ingredients = model.ingredients,
            long_date_added = DateUtils.dateToLong(model.dateAdded),
            long_date_updated = DateUtils.dateToLong(model.dateUpdated),
        )
    }

    fun fromEntityList(initial: List<RecipeDto>): List<Recipe>{
        return initial.map { mapToModel(it) }
    }

    fun toEntityList(initial: List<Recipe>): List<RecipeDto>{
        return initial.map { mapFromModel(it) }
    }


}