package org.example.app.data

import kotlinx.coroutines.delay

/**
 * PUBLIC_INTERFACE
 * A fake repository useful for testing or previews. Returns the given list.
 */
class FakeRecipeRepository(private val items: List<Recipe>) {

    /**
     * PUBLIC_INTERFACE
     * Returns all recipes after a small artificial delay.
     */
    suspend fun getAllRecipes(): List<Recipe> {
        delay(10)
        return items
    }

    /**
     * PUBLIC_INTERFACE
     * Searches by title substring.
     */
    suspend fun search(query: String): List<Recipe> {
        delay(10)
        if (query.isBlank()) return items
        return items.filter { it.title.contains(query, ignoreCase = true) }
    }

    /**
     * PUBLIC_INTERFACE
     * Returns a recipe by id or null.
     */
    suspend fun getById(id: String): Recipe? {
        delay(10)
        return items.find { it.id == id }
    }
}
