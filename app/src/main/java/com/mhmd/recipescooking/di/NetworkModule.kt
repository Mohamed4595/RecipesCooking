package com.mhmd.recipescooking.di

import com.mhmd.recipescooking.business.data.cache.mapper.RecipeEntityMapper
import com.mhmd.recipescooking.framework.datasource.network.abstraction.RecipeDtoService
import com.mhmd.recipescooking.framework.datasource.network.implementation.RecipeDtoServiceImpl
import com.mhmd.recipescooking.business.data.network.mapper.RecipeDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideDtoRecipeMapper(): RecipeDtoMapper {
        return RecipeDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRecipeService(): RecipeDtoService {
        return RecipeDtoServiceImpl(
            client = HttpClient(Android) {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
                install(JsonFeature) {

                    serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }
            }
        )
    }

}