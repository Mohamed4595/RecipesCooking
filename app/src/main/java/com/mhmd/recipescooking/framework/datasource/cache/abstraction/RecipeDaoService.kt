package com.mhmd.recipescooking.framework.datasource.cache.abstraction

import com.mhmd.recipescooking.framework.datasource.cache.model.RecipeEntity
import com.mhmd.recipescooking.utils.RECIPE_PAGINATION_PAGE_SIZE

interface RecipeDaoService {


    suspend fun insertRecipe(recipe: RecipeEntity): Long

    suspend fun insertRecipes(recipes: List<RecipeEntity>): LongArray

    suspend fun getRecipeById(id: Int): RecipeEntity?

    suspend fun deleteRecipes(ids: List<Int>): Int

    suspend fun deleteAllRecipes()

    suspend fun deleteRecipe(primaryKey: Int): Int

    /**
     * Retrieve recipes for a particular page.
     * Ex: page = 2 retrieves recipes from 30-60.
     * Ex: page = 3 retrieves recipes from 60-90
     */
    suspend fun searchRecipes(
        query: String,
        page: Int,
        pageSize: Int = RECIPE_PAGINATION_PAGE_SIZE
    ): List<RecipeEntity>

    /**
     * Same as 'searchRecipes' function, but no query.
     */
    suspend fun getAllRecipes(
        page: Int,
        pageSize: Int = RECIPE_PAGINATION_PAGE_SIZE
    ): List<RecipeEntity>

    /**
     * Restore Recipes after process death
     */
    suspend fun restoreRecipes(
        query: String,
        page: Int,
        pageSize: Int = RECIPE_PAGINATION_PAGE_SIZE
    ): List<RecipeEntity>

    /**
     * Same as 'restoreRecipes' function, but no query.
     */
    suspend fun restoreAllRecipes(
        page: Int,
        pageSize: Int = RECIPE_PAGINATION_PAGE_SIZE
    ): List<RecipeEntity>

}