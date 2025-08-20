package org.example.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.example.app.R
import org.example.app.data.FavoritesStore
import org.example.app.data.Recipe
import org.example.app.data.RecipeRepository
import org.example.app.ui.RecipeAdapter
import org.example.app.ui.common.VerticalSpaceItemDecoration
import org.example.app.ui.recipe.RecipeIntents

/**
 * PUBLIC_INTERFACE
 * Home screen: displays all recipes with pull-to-refresh.
 */
class HomeFragment : Fragment() {

    private val repo = RecipeRepository()
    private lateinit var adapter: RecipeAdapter
    private lateinit var favorites: FavoritesStore
    private var favSet: Set<String> = emptySet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favorites = FavoritesStore(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val rv = root.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvRecipes)
        val swipe = root.findViewById<androidx.swiperefreshlayout.widget.SwipeRefreshLayout>(R.id.swipeRefresh)

        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.addItemDecoration(VerticalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.padding_large)))

        adapter = RecipeAdapter(
            items = emptyList(),
            isFavorite = { id -> favSet.contains(id) },
            onToggleFavorite = { r ->
                viewLifecycleOwner.lifecycleScope.launch {
                    favorites.toggleFavorite(r.id)
                    refreshFavsAndList()
                }
            },
            onClick = { r -> openDetail(r) }
        )
        rv.adapter = adapter

        swipe.setOnRefreshListener {
            viewLifecycleOwner.lifecycleScope.launch {
                refreshFavs()
                val list = repo.getAllRecipes()
                adapter.submit(list)
                swipe.isRefreshing = false
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            refreshFavs()
            val list = repo.getAllRecipes()
            adapter.submit(list)
        }

        return root
    }

    private suspend fun refreshFavs() {
        favSet = favorites.favoritesFlow.first()
    }

    private suspend fun refreshFavsAndList() {
        refreshFavs()
        val list = repo.getAllRecipes()
        adapter.submit(list)
    }

    private fun openDetail(recipe: Recipe) {
        val i = RecipeIntents.openDetail(requireContext(), recipe.id)
        startActivity(i)
    }
}
