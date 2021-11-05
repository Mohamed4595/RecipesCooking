package com.mhmd.recipescooking.framework.presentation.recipeList

sealed class RecipeListEvent {

    object SearchEvent : RecipeListEvent()

    object NextPageEvent : RecipeListEvent()

    // restore after process death
    object RestoreStateEvent: RecipeListEvent()
}