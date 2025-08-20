package org.example.app.ui.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.example.app.R
import org.example.app.data.FavoritesStore
import org.example.app.data.RecipeRepository
import org.example.app.ui.RecipeAdapter
import org.example.app.ui.common.VerticalSpaceItemDecoration
import org.example.app.ui.recipe.RecipeDetailActivity

/**
 * PUBLIC_INTERFACE
 * Search screen for filtering recipes by title.
 */
class SearchFragment : Fragment() {

    private val repo = RecipeRepository()
    private lateinit var adapter: RecipeAdapter
    private lateinit var favorites: FavoritesStore
    private var favSet: Set<String> = emptySet()
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favorites = FavoritesStore(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val et = root.findViewById<EditText>(R.id.etSearch)
        val rv = root.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rvSearch)
        val tvEmpty = root.findViewById<TextView>(R.id.tvEmptySearch)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.addItemDecoration(VerticalSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.padding_large)))

        adapter = RecipeAdapter(
            items = emptyList(),
            isFavorite = { id -> favSet.contains(id) },
            onToggleFavorite = { r -> viewLifecycleOwner.lifecycleScope.launch { favorites.toggleFavorite(r.id); favSet = favorites.favoritesFlow.first() } },
            onClick = { r ->
                val i = Intent(requireContext(), RecipeDetailActivity::class.java)
                i.putExtra(RecipeDetailActivity.EXTRA_ID, r.id)
                startActivity(i)
            }
        )
        rv.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            favSet = favorites.favoritesFlow.first()
            val initial = repo.getAllRecipes()
            adapter.submit(initial)
            tvEmpty.visibility = if (initial.isEmpty()) View.VISIBLE else View.GONE
        }

        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchJob?.cancel()
                searchJob = viewLifecycleOwner.lifecycleScope.launch {
                    delay(250)
                    val q = s?.toString() ?: ""
                    val list = repo.search(q)
                    adapter.submit(list)
                    tvEmpty.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
                }
            }
        })

        return root
    }
}
