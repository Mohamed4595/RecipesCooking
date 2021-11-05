package com.mhmd.recipescooking.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mhmd.recipescooking.framework.datasource.cache.model.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 2,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        const val DATABASE_NAME: String = "recipe_db"
    }


}