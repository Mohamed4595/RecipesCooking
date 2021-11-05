package com.mhmd.recipescooking.framework.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mhmd.recipescooking.business.domain.model.Recipe
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun RecipeDetails(
    recipe: Recipe,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        item {
            recipe.featuredImage?.let { url ->
                Surface(
                    shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),

                    ) {
                    Image(
                        painter = rememberImagePainter(
                            data = url,
                            builder = {
                                crossfade(1000)
                                //placeholder(R.drawable.placeholder)
                                //transformations(CircleCropTransformation())
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop,
                    )

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    recipe.title?.let { title ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = title,
                                modifier = Modifier
                                    .fillMaxWidth(0.85f)
                                    .wrapContentWidth(Alignment.Start),
                                style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.onBackground)
                            )
                            val rank = recipe.rating.toString()
                            Text(
                                text = rank,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.End)
                                    .align(Alignment.CenterVertically),
                                style = MaterialTheme.typography.subtitle1.copy(color = MaterialTheme.colors.onBackground)
                            )
                        }
                    }
                    recipe.publisher?.let { publisher ->
                         Text(
                            text =
                                "Updated ${recipe.dateUpdated} by $publisher"
                            ,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            style = MaterialTheme.typography.caption.copy(color = MaterialTheme.colors.onBackground)
                        )
                    }
                    recipe.description?.let { description ->
                        if (description != "N/A") {
                            Text(
                                text = description,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp),
                                style = MaterialTheme.typography.subtitle2.copy(color = MaterialTheme.colors.onBackground)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    for (ingredient in recipe.ingredients) {
                        Text(
                            text = ingredient,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground)
                        )
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    recipe.cookingInstructions?.let { instructions ->
                        Text(
                            text = instructions,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp),
                            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onBackground)
                        )
                    }
                }
            }
        }
    }
}
