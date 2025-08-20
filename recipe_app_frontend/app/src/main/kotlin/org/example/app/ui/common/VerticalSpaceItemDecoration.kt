package org.example.app.ui.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * PUBLIC_INTERFACE
 * Simple vertical space item decoration for RecyclerView lists.
 */
class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val pos = parent.getChildAdapterPosition(view)
        if (pos >= 0) {
            outRect.top = if (pos == 0) 0 else verticalSpaceHeight
        }
    }
}
