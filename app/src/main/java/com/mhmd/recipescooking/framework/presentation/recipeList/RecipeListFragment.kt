package com.mhmd.recipescooking.framework.presentation.recipeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.mhmd.recipescooking.framework.presentation.utils.ConnectivityManager
import com.mhmd.recipescooking.R
import com.mhmd.recipescooking.framework.datasource.datastore.SettingsDataStore
import com.mhmd.recipescooking.framework.presentation.components.NothingHere
import com.mhmd.recipescooking.framework.presentation.components.RecipeCard
import com.mhmd.recipescooking.framework.presentation.components.SearchAppBar
import com.mhmd.recipescooking.framework.presentation.recipeList.RecipeListEvent.*
import com.mhmd.recipescooking.framework.presentation.theme.RecipesCookingTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingsDataStore.isDark.value = true
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {


                val recipes = viewModel.recipes.value

                val query = viewModel.query.value

                val selectedCategory = viewModel.selectedCategory.value

                val categoryScrollPosition = viewModel.categoryScrollPosition

                val loading = viewModel.loading.value

                val page = viewModel.page.value

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
                            SearchAppBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = { viewModel.onTriggerEvent(SearchEvent) },
                                categories = getAllFoodCategories(),
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                scrollPosition = categoryScrollPosition,
                                onChangeScrollPosition = viewModel::onChangeCategoryScrollPosition,
                                onToggleTheme =
                                settingsDataStore::toggleTheme,
                                isDark = settingsDataStore.isDark.value
                            )
                            if (loading && recipes.isEmpty()) {
                                LoadingRecipeListShimmer(imageHeight = 200.dp)
                            } else if (recipes.isEmpty()) {
                                NothingHere()
                            } else {
                                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                                    itemsIndexed(
                                        items = recipes
                                    ) { index, recipe ->
                                        viewModel.onChangeRecipeScrollPosition(index)
                                        if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                                            viewModel.onTriggerEvent(NextPageEvent)
                                        }
                                        RecipeCard(recipe = recipe, onClick = {
                                            val bundle = Bundle()
                                            bundle.putInt("recipeId", recipe.id)
                                            findNavController().navigate(
                                                R.id.action_recipeListFragment_to_recipeFragment,
                                                bundle
                                            )
                                        })

                                    }
                                }
                            }
                        }

                        if (loading && recipes.isNotEmpty()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colors.surface)
                                    .align(alignment = Alignment.BottomCenter)
                                    .padding(vertical = 16.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator(
                                    color = MaterialTheme.colors.onSurface
                                )
                            }
                        }

                    }
                }
            }
        }
    }

}