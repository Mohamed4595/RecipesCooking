package com.mhmd.recipescooking.di

import com.mhmd.recipescooking.business.data.cache.mapper.RecipeEntityMapper
import com.mhmd.recipescooking.business.data.network.mapper.RecipeDtoMapper
import com.mhmd.recipescooking.business.interactors.recipe.GetRecipe
import com.mhmd.recipescooking.business.interactors.recipeList.RestoreRecipes
import com.mhmd.recipescooking.business.interactors.recipeList.SearchRecipes
import com.mhmd.recipescooking.framework.datasource.cache.abstraction.RecipeDaoService
import com.mhmd.recipescooking.framework.datasource.network.abstraction.RecipeDtoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {

    @ViewModelScoped
    @Provides
    fun provideSearchRecipe(
        recipeDtoService: RecipeDtoService,
        recipeDaoService: RecipeDaoService,
        recipeEntityMapper: RecipeEntityMapper,
        recipeDtoMapper: RecipeDtoMapper,
    ): SearchRecipes {
        return SearchRecipes(
            recipeDtoService = recipeDtoService,
            recipeDaoService = recipeDaoService,
            entityMapper = recipeEntityMapper,
            dtoMapper = recipeDtoMapper,
        )
    }

    @ViewModelScoped
    @Provides
    fun provideRestoreRecipes(
        recipeDaoService: RecipeDaoService,
        recipeEntityMapper: RecipeEntityMapper,
    ): RestoreRecipes {
        return RestoreRecipes(
            recipeDaoService = recipeDaoService,
            entityMapper = recipeEntityMapper,
        )
    }

    @ViewModelScoped
    @Provides
    fun provideGetRecipe(
        recipeDaoService: RecipeDaoService,
        recipeEntityMapper: RecipeEntityMapper,
        recipeDtoService: RecipeDtoService,
        recipeDtoMapper: RecipeDtoMapper,
    ): GetRecipe {
        return GetRecipe(
            recipeDaoService = recipeDaoService,
            entityMapper = recipeEntityMapper,
            recipeService = recipeDtoService,
            recipeDtoMapper = recipeDtoMapper,
        )
    }

}











