package org.example.app.data

import kotlinx.serialization.Serializable

/**
 * PUBLIC_INTERFACE
 * Represents a recipe item.
 */
@Serializable
data class Recipe(
    val id: String,
    val title: String,
    val imageUrl: String,
    val readyInMinutes: Int,
    val servings: Int,
    val ingredients: List<String>,
    val instructions: String
)
