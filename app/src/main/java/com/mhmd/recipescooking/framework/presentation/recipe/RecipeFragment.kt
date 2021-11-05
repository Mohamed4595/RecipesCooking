package com.mhmd.recipescooking.framework.presentation.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mhmd.recipescooking.framework.presentation.utils.ConnectivityManager
import com.mhmd.recipescooking.framework.datasource.datastore.SettingsDataStore
import com.mhmd.recipescooking.framework.presentation.components.NothingHere
import com.mhmd.recipescooking.framework.presentation.components.RecipeDetails
import com.mhmd.recipescooking.framework.presentation.recipe.RecipeEvent.GetRecipeEvent
import com.mhmd.recipescooking.framework.presentation.theme.RecipesCookingTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class RecipeFragment : Fragment() {

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("recipeId")?.let { recipeId ->
            viewModel.onTriggerEvent(GetRecipeEvent(recipeId))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val loading = viewModel.loading.value

                val recipe = viewModel.recipe.value

                val dialogQueue = viewModel.dialogQueue

                RecipesCookingTheme(
                    darkTheme = settingsDataStore.isDark.value,
                    isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                    dialogQueue = dialogQueue.queue.value,
                ) {
                    Box {
                        Column(
                            modifier = Modifier.background(
                                MaterialTheme.colors.background
                            )
                        ) {
                            if (loading && recipe == null) {
                                LoadingRecipeShimmer(imageHeight = 200.dp)
                            } else if (recipe == null) {
                                NothingHere()
                            } else {
                                RecipeDetails(recipe = recipe)
                            }
                        }
                    }
                }
            }
        }
    }
}
