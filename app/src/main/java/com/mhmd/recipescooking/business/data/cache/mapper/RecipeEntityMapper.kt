package com.mhmd.recipescooking.business.data.cache.mapper

import com.mhmd.recipescooking.business.domain.model.Recipe
import com.mhmd.recipescooking.framework.datasource.cache.model.RecipeEntity
import com.mhmd.recipescooking.framework.datasource.utils.ModelMapper
import com.mhmd.recipescooking.utils.DateUtils


class RecipeEntityMapper : ModelMapper<RecipeEntity, Recipe> {

    override fun mapToModel(model: RecipeEntity): Recipe {
        return Recipe(
            id = model.id,
            title = model.title,
            featuredImage = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            cookingInstructions = model.cookingInstructions,
            description = model.description,
            ingredients = convertIngredientsToList(model.ingredients),
            dateAdded = DateUtils.longToDate(model.dateAdded!!),
            dateUpdated = DateUtils.longToDate(model.dateUpdated!!),
        )
    }


    override fun mapFromModel(model: Recipe): RecipeEntity {
        return RecipeEntity(
            id = model.id,
            title = model.title,
            featuredImage = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            description = model.description,
            cookingInstructions = model.cookingInstructions,

            ingredients = convertIngredientListToString(model.ingredients),
            dateAdded = DateUtils.dateToLong(model.dateAdded),
            dateUpdated = DateUtils.dateToLong(model.dateUpdated),
            dateCached = DateUtils.dateToLong(DateUtils.createTimestamp())

        )
    }

    /**
     * "Carrot, potato, Chicken, ..."
     */
    private fun convertIngredientListToString(ingredients: List<String>): String {
        val ingredientsString = StringBuilder()
        for (ingredient in ingredients) {
            ingredientsString.append("$ingredient,")
        }
        return ingredientsString.toString()
    }

    private fun convertIngredientsToList(ingredientsString: String?): List<String> {
        val list: ArrayList<String> = ArrayList()
        ingredientsString?.let {
            for (ingredient in it.split(",")) {
                list.add(ingredient)
            }
        }
        return list
    }

    fun fromEntityList(initial: List<RecipeEntity>): List<Recipe> {
        return initial.map { mapToModel(it) }
    }

    fun toEntityList(initial: List<Recipe>): List<RecipeEntity> {
        return initial.map { mapFromModel(it) }
    }

}