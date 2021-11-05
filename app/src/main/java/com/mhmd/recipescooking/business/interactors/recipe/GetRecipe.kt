package com.mhmd.recipescooking.business.interactors.recipe

import com.mhmd.recipescooking.business.data.cache.mapper.RecipeEntityMapper
import com.mhmd.recipescooking.business.data.network.mapper.RecipeDtoMapper
import com.mhmd.recipescooking.business.domain.model.Recipe
import com.mhmd.recipescooking.business.domain.state.DataState
import com.mhmd.recipescooking.framework.datasource.cache.abstraction.RecipeDaoService
import com.mhmd.recipescooking.framework.datasource.network.abstraction.RecipeDtoService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Retrieve a recipe from the cache given it's unique id.
 */
class GetRecipe(
    private val recipeDaoService: RecipeDaoService,
    private val entityMapper: RecipeEntityMapper,
    private val recipeService: RecipeDtoService,
    private val recipeDtoMapper: RecipeDtoMapper,
) {

    fun execute(
        recipeId: Int,
        isNetworkAvailable: Boolean,
    ): Flow<DataState<Recipe>> = flow {
        try {
            emit(DataState.loading())

            delay(1000)

            var recipe = getRecipeFromCache(recipeId = recipeId)

            if (recipe != null) {
                emit(DataState.success(recipe))
            }
            // if the recipe is null, it means it was not in the cache for some reason. So get from network.
            else {

                if (isNetworkAvailable) {
                    // get recipe from network
                    val networkRecipe = getRecipeFromNetwork(recipeId) // dto -> domain

                    // insert into cache
                    recipeDaoService.insertRecipe(
                        // map domain -> entity
                        entityMapper.mapFromModel(networkRecipe)
                    )
                }

                // get from cache
                recipe = getRecipeFromCache(recipeId = recipeId)

                // emit and finish
                if (recipe != null) {
                    emit(DataState.success(recipe))
                } else {
                    throw Exception("Unable to get recipe from the cache.")
                }
            }

        } catch (e: Exception) {
            emit(DataState.error<Recipe>(e.message ?: "Unknown Error"))
        }
    }

    private suspend fun getRecipeFromCache(recipeId: Int): Recipe? {
        return recipeDaoService.getRecipeById(recipeId)?.let { recipeEntity ->
            entityMapper.mapToModel(recipeEntity)
        }
    }

    private suspend fun getRecipeFromNetwork(recipeId: Int): Recipe {
        return recipeDtoMapper.mapToModel(recipeService.get(recipeId)!!)
    }
}