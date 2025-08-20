package org.example.app.ui

import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.example.app.R
import org.example.app.data.Recipe

/**
 * PUBLIC_INTERFACE
 * Adapter for displaying recipe cards in lists.
 */
class RecipeAdapter(
    private var items: List<Recipe>,
    private val isFavorite: (String) -> Boolean,
    private val onToggleFavorite: (Recipe) -> Unit,
    private val onClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.VH>() {

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val subtitle: TextView = itemView.findViewById(R.id.subtitle)
        val favButton: ImageButton = itemView.findViewById(R.id.favButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_card, parent, false)
        return VH(v)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val r = items[position]
        holder.title.text = r.title
        holder.subtitle.text = "Ready in ${r.readyInMinutes} min • ${r.servings} servings"

        if (r.imageUrl.isNotBlank()) {
            Picasso.get()
                .load(r.imageUrl)
                .placeholder(R.drawable.ic_placeholder_image)
                .error(R.drawable.ic_placeholder_image)
                .into(holder.image)
        } else {
            holder.image.setImageResource(R.drawable.ic_placeholder_image)
        }

        val favOn = isFavorite(r.id)
        holder.favButton.setImageResource(
            if (favOn) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off
        )

        holder.itemView.setOnClickListener { onClick(r) }
        holder.favButton.setOnClickListener {
            onToggleFavorite(r)
            // Update icon immediately for responsiveness; actual state will be refreshed by caller
            val nowFav = !favOn
            holder.favButton.setImageResource(
                if (nowFav) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off
            )
        }
        holder.itemView.setOnLongClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
            onToggleFavorite(r)
            true
        }
    }

    /**
     * PUBLIC_INTERFACE
     * Update data set.
     */
    fun submit(list: List<Recipe>) {
        val old = items
        val new = list
        val diff = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = old.size
            override fun getNewListSize(): Int = new.size
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].id == new[newItemPosition].id
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]
        }
        val result = DiffUtil.calculateDiff(diff)
        items = new
        result.dispatchUpdatesTo(this)
    }
}
