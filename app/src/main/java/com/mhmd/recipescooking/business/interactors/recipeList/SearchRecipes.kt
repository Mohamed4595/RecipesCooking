package com.mhmd.recipescooking.business.interactors.recipeList

import com.mhmd.recipescooking.business.data.cache.mapper.RecipeEntityMapper
import com.mhmd.recipescooking.business.data.network.mapper.RecipeDtoMapper
import com.mhmd.recipescooking.business.domain.model.Recipe
import com.mhmd.recipescooking.business.domain.state.DataState
import com.mhmd.recipescooking.framework.datasource.cache.abstraction.RecipeDaoService
import com.mhmd.recipescooking.framework.datasource.network.abstraction.RecipeDtoService
import com.mhmd.recipescooking.utils.RECIPE_PAGINATION_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeDaoService: RecipeDaoService,
    private val recipeDtoService: RecipeDtoService,
    private val entityMapper: RecipeEntityMapper,
    private val dtoMapper: RecipeDtoMapper,
) {

    fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow {
        try {
            emit(DataState.loading())

            try {
                // Convert: NetworkRecipeEntity -> Recipe -> RecipeCacheEntity
                val recipes = getRecipesFromNetwork(
                    page = page,
                    query = query,
                )

                // insert into cache
                recipeDaoService.insertRecipes(entityMapper.toEntityList(recipes))
            } catch (e: Exception) {
                // There was a network issue
                e.printStackTrace()
            }

            // query the cache
            val cacheResult = if (query.isBlank()) {
                recipeDaoService.getAllRecipes(
                    pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                    page = page
                )
            } else {
                recipeDaoService.searchRecipes(
                    query = query,
                    pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                    page = page
                )
            }

            // emit List<Recipe> from cache
            val list = entityMapper.fromEntityList(cacheResult)

            emit(DataState.success(list))
        } catch (e: Exception) {
            emit(DataState.error<List<Recipe>>(e.message ?: "Unknown Error"))
        }
    }

    // WARNING: This will throw exception if there is no network connection
    private suspend fun getRecipesFromNetwork(
        page: Int,
        query: String
    ): List<Recipe> {
        return dtoMapper.fromEntityList(
            recipeDtoService.search(
                page = page,
                query = query,
            ).results
        )
    }
}