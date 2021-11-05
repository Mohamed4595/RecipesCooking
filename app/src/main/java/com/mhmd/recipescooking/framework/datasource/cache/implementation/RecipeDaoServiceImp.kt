package com.mhmd.recipescooking.framework.datasource.cache.implementation

import com.mhmd.recipescooking.framework.datasource.cache.abstraction.RecipeDaoService
import com.mhmd.recipescooking.framework.datasource.cache.database.RecipeDao
import com.mhmd.recipescooking.framework.datasource.cache.model.RecipeEntity

class RecipeDaoServiceImp(private val recipeDao: RecipeDao) : RecipeDaoService {
    override suspend fun insertRecipe(recipe: RecipeEntity): Long {
        return recipeDao.insertRecipe(recipe)
    }

    override suspend fun insertRecipes(recipes: List<RecipeEntity>): LongArray {
        return recipeDao.insertRecipes(recipes)
    }

    override suspend fun getRecipeById(id: Int): RecipeEntity? {
        return recipeDao.getRecipeById(id)

    }

    override suspend fun deleteRecipes(ids: List<Int>): Int {
        return recipeDao.deleteRecipes(ids)
    }

    override suspend fun deleteAllRecipes() {
        return recipeDao.deleteAllRecipes()
    }

    override suspend fun deleteRecipe(primaryKey: Int): Int {
        return recipeDao.deleteRecipe(primaryKey)
    }

    override suspend fun searchRecipes(
        query: String,
        page: Int,
        pageSize: Int
    ): List<RecipeEntity> {
        return recipeDao.searchRecipes(
            query,
            page,
            pageSize
        )
    }

    override suspend fun getAllRecipes(page: Int, pageSize: Int): List<RecipeEntity> {
        return recipeDao.getAllRecipes(page,pageSize)
    }

    override suspend fun restoreRecipes(
        query: String,
        page: Int,
        pageSize: Int
    ): List<RecipeEntity> {
        return recipeDao.restoreRecipes(query,page,pageSize)
    }

    override suspend fun restoreAllRecipes(page: Int, pageSize: Int): List<RecipeEntity> {
        return recipeDao.restoreAllRecipes(page,pageSize)
    }
}