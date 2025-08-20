package org.example.app.ui.favorites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.example.app.R
import org.example.app.data.FavoritesStore
import org.example.app.data.RecipeRepository
import org.example.app.ui.RecipeAdapter
import org.example.app.ui.common.VerticalSpaceItemDecoration
import org.example.app.ui.recipe.RecipeDetailActivity

/**
 * PUBLIC_INTERFACE
 * Favorites screen showing favorite recipes.
 */
class FavoritesFragment : Fragment() {

    private lateinit var favoritesStore: FavoritesStore
    private val repo = RecipeRepository()
    private lateinit var adapter: RecipeAdapter
    private lateinit var tvEmpty: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesStore = FavoritesStore(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_favorites, container, false)
        val rv = root.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvFavorites)
        tvEmpty = root.findViewById(R.id.tvEmpty)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.addItemDecoration(VerticalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.padding_large)))

        adapter = RecipeAdapter(
            items = emptyList(),
            isFavorite = { true },
            onToggleFavorite = { r -> viewLifecycleOwner.lifecycleScope.launch { favoritesStore.toggleFavorite(r.id) } },
            onClick = { r ->
                val i = Intent(requireContext(), RecipeDetailActivity::class.java)
                i.putExtra(RecipeDetailActivity.EXTRA_ID, r.id)
                startActivity(i)
            }
        )
        rv.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            favoritesStore.favoritesFlow.collect { set ->
                val all = repo.getAllRecipes()
                val favs = all.filter { set.contains(it.id) }
                tvEmpty.visibility = if (favs.isEmpty()) View.VISIBLE else View.GONE
                adapter.submit(favs)
            }
        }

        return root
    }
}
