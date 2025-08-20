package org.example.app.ui.recipe

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.example.app.R
import org.example.app.data.FavoritesStore
import org.example.app.data.Recipe
import org.example.app.data.RecipeRepository

/**
 * PUBLIC_INTERFACE
 * Activity to show a recipe's details and manage favorites.
 */
class RecipeDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = org.example.app.util.Intents.EXTRA_RECIPE_ID
    }

    private val repo = RecipeRepository()
    private lateinit var favoritesStore: FavoritesStore
    private var currentRecipe: Recipe? = null
    private var isFav: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        favoritesStore = FavoritesStore(this)

        val id = intent.getStringExtra(EXTRA_ID) ?: return

        val ivHero = findViewById<ImageView>(R.id.ivHero)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvMeta = findViewById<TextView>(R.id.tvMeta)
        val btnFavorite = findViewById<Button>(R.id.btnFavorite)
        val btnShare = findViewById<Button>(R.id.btnShare)
        val tvIngredients = findViewById<TextView>(R.id.tvIngredients)
        val tvInstructions = findViewById<TextView>(R.id.tvInstructions)
        val btnShare = findViewById<Button>(R.id.btnShare)

        lifecycleScope.launch {
            currentRecipe = repo.getById(id)
            val r = currentRecipe ?: return@launch
            val favSet = favoritesStore.favoritesFlow.first()
            isFav = favSet.contains(r.id)
            updateFavButton(btnFavorite)

            tvTitle.text = r.title
            tvMeta.text = "${r.readyInMinutes} min • ${r.servings} servings"
            tvIngredients.text = r.ingredients.joinToString(separator = "\n") { "• $it" }
            tvInstructions.text = r.instructions
            if (r.imageUrl.isNotBlank()) {
                Picasso.get()
                    .load(r.imageUrl)
                    .placeholder(R.drawable.ic_placeholder_image)
                    .error(R.drawable.ic_placeholder_image)
                    .into(ivHero)
            } else {
                ivHero.setImageResource(R.drawable.ic_placeholder_image)
            }
        }

        btnFavorite.setOnClickListener {
            val r = currentRecipe ?: return@setOnClickListener
            lifecycleScope.launch {
                favoritesStore.toggleFavorite(r.id)
                val favSet = favoritesStore.favoritesFlow.first()
                isFav = favSet.contains(r.id)
                updateFavButton(btnFavorite)
            }
        }

        btnShare.setOnClickListener {
            val r = currentRecipe ?: return@setOnClickListener
            val share = android.content.Intent(android.content.Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.share_subject))
            share.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.share_message, r.title))
            startActivity(android.content.Intent.createChooser(share, getString(R.string.share)))
        }

        btnShare.setOnClickListener {
            val r = currentRecipe ?: return@setOnClickListener
            val shareText = "${r.title} • ${r.readyInMinutes} min • ${r.servings} servings"
            val sendIntent = android.content.Intent().apply {
                action = android.content.Intent.ACTION_SEND
                putExtra(android.content.Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            val shareIntent = android.content.Intent.createChooser(sendIntent, getString(R.string.share_recipe))
            startActivity(shareIntent)
        }
    }

    private fun updateFavButton(btn: Button) {
        btn.text = if (isFav) getString(R.string.remove_from_favorites) else getString(R.string.add_to_favorites)
    }
}
