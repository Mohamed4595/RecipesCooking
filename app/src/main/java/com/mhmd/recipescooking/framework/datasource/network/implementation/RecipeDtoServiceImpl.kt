package com.mhmd.recipescooking.framework.datasource.network.implementation

import com.mhmd.recipescooking.framework.datasource.network.model.RecipeDto
import com.mhmd.recipescooking.framework.datasource.network.RecipeSearchResponse
import com.mhmd.recipescooking.framework.datasource.utils.HttpRoutes
import com.mhmd.recipescooking.framework.datasource.network.abstraction.RecipeDtoService
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*


class RecipeDtoServiceImpl(
    private val client: HttpClient
) : RecipeDtoService {

    override suspend fun search(
        page: Int,
        query: String
    ): RecipeSearchResponse {
        return try {
            client.get {

                url(HttpRoutes.Search)
                header("Authorization", HttpRoutes.Token)
                parameter("page", page)
                parameter("query", query)

            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.value} ${e.response.status.description}")
            RecipeSearchResponse(e.response.status.value, emptyList())
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.value} ${e.response.status.description}")
            RecipeSearchResponse(e.response.status.value, emptyList())
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.value} ${e.response.status.description}")
            RecipeSearchResponse(e.response.status.value, emptyList())
        } catch (e: Exception) {
            println("Error: ${e.message}")
            RecipeSearchResponse(0, emptyList())
        }
    }

    override suspend fun get(id: Int): RecipeDto? {
        return try {
            client.get { url(HttpRoutes.Get)
                header("Authorization", HttpRoutes.Token)
                parameter("id", id)}
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
}