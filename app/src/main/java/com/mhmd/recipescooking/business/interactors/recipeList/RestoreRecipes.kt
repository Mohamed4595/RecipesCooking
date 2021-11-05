package com.mhmd.recipescooking.business.interactors.recipeList

import com.mhmd.recipescooking.business.data.cache.mapper.RecipeEntityMapper
import com.mhmd.recipescooking.business.domain.model.Recipe
import com.mhmd.recipescooking.business.domain.state.DataState
import com.mhmd.recipescooking.framework.datasource.cache.abstraction.RecipeDaoService
import com.mhmd.recipescooking.utils.RECIPE_PAGINATION_PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Restore a list of recipes after process death.
 */
class RestoreRecipes(
    private val recipeDaoService: RecipeDaoService,
    private val entityMapper: RecipeEntityMapper,
) {
    fun execute(
        page: Int,
        query: String
    ): Flow<DataState<List<Recipe>>> = flow {
        try {
            emit(DataState.loading())

            // query the cache
            val cacheResult = if (query.isBlank()) {
                recipeDaoService.restoreAllRecipes(
                    pageSize = RECIPE_PAGINATION_PAGE_SIZE,
                    page = page
                )
            } else {
                recipeDaoService.restoreRecipes(
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
}




