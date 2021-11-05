package com.mhmd.recipescooking.di

import androidx.room.Room
import com.mhmd.recipescooking.business.data.cache.mapper.RecipeEntityMapper
import com.mhmd.recipescooking.framework.datasource.cache.abstraction.RecipeDaoService
import com.mhmd.recipescooking.framework.datasource.cache.database.AppDatabase
import com.mhmd.recipescooking.framework.datasource.cache.database.RecipeDao
import com.mhmd.recipescooking.framework.datasource.cache.implementation.RecipeDaoServiceImp
import com.mhmd.recipescooking.framework.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: BaseApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(db: AppDatabase): RecipeDao{
        return db.recipeDao()
    }

    @Singleton
    @Provides
    fun provideRecipeDaoService(recipeDao: RecipeDao): RecipeDaoService{
        return RecipeDaoServiceImp(recipeDao)
    }

    @Singleton
    @Provides
    fun provideEntityRecipeMapper(): RecipeEntityMapper {
        return RecipeEntityMapper()
    }

}