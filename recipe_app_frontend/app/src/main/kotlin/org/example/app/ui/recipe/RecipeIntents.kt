package org.example.app.ui.recipe

import android.content.Context
import android.content.Intent

/**
 * PUBLIC_INTERFACE
 * Intent helpers for navigating to recipe features.
 */
object RecipeIntents {
    /**
     * PUBLIC_INTERFACE
     * Create an Intent to open the detail screen for a given recipe id.
     */
    fun openDetail(context: Context, recipeId: String): Intent {
        return Intent(context, RecipeDetailActivity::class.java).apply {
            putExtra(RecipeDetailActivity.EXTRA_ID, recipeId)
        }
    }
}
