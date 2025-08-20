package org.example.app.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.example.app.util.AppConstants

/**
 * PUBLIC_INTERFACE
 * Provides access to recipes. Backed by mock data for offline demo.
 */
class RecipeRepository {

    private val recipes: List<Recipe> = listOf(
        Recipe(
            id = "1",
            title = "Avocado Toast",
            imageUrl = "https://images.unsplash.com/photo-1551183053-bf91a1d81141?w=800",
            readyInMinutes = 10,
            servings = 1,
            ingredients = listOf("2 slices bread", "1 avocado", "Salt", "Pepper", "Lemon juice"),
            instructions = "Toast bread. Mash avocado with salt, pepper, and lemon. Spread and serve."
        ),
        Recipe(
            id = "2",
            title = "Grilled Chicken Salad",
            imageUrl = "https://images.unsplash.com/photo-1555939594-58d7cb561ad1?w=800",
            readyInMinutes = 25,
            servings = 2,
            ingredients = listOf("200g chicken", "Lettuce", "Tomatoes", "Cucumber", "Vinaigrette"),
            instructions = "Grill chicken. Chop veggies. Toss with vinaigrette and sliced chicken."
        ),
        Recipe(
            id = "3",
            title = "Pasta Primavera",
            imageUrl = "https://images.unsplash.com/photo-1526318472351-c75fcf070305?w=800",
            readyInMinutes = 30,
            servings = 3,
            ingredients = listOf("Pasta", "Mixed veggies", "Olive oil", "Garlic", "Parmesan"),
            instructions = "Cook pasta. Sauté veggies with garlic. Mix with pasta and top with parmesan."
        ),
        Recipe(
            id = "4",
            title = "Berry Smoothie",
            imageUrl = "https://images.unsplash.com/photo-1523986371872-9d3ba2e2f642?w=800",
            readyInMinutes = 5,
            servings = 1,
            ingredients = listOf("Mixed berries", "Banana", "Yogurt", "Honey"),
            instructions = "Blend all ingredients until smooth."
        )
    )

    /**
     * PUBLIC_INTERFACE
     * Fetch all recipes (simulated IO).
     */
    suspend fun getAllRecipes(): List<Recipe> = withContext(Dispatchers.IO) {
        delay(AppConstants.REPOSITORY_NETWORK_DELAY_MS) // simulate network
        recipes
    }

    /**
     * PUBLIC_INTERFACE
     * Search recipes by title substring.
     */
    suspend fun search(query: String): List<Recipe> = withContext(Dispatchers.IO) {
        delay(AppConstants.REPOSITORY_NETWORK_DELAY_MS / 2)
        if (query.isBlank()) recipes
        else recipes.filter { it.title.contains(query, ignoreCase = true) }
    }

    /**
     * PUBLIC_INTERFACE
     * Get a recipe by id.
     */
    suspend fun getById(id: String): Recipe? = withContext(Dispatchers.IO) {
        delay(AppConstants.REPOSITORY_NETWORK_DELAY_MS / 2)
        recipes.find { it.id == id }
    }
}
