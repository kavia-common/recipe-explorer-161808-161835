package org.example.app.ui.common

import android.content.Context
import org.example.app.R

/**
 * PUBLIC_INTERFACE
 * Provides convenient accessors for commonly used dimensions.
 */
object Dimensions {
    // PUBLIC_INTERFACE
    fun paddingSmallPx(context: Context): Int = context.resources.getDimensionPixelSize(R.dimen.padding_small)
    // PUBLIC_INTERFACE
    fun paddingMediumPx(context: Context): Int = context.resources.getDimensionPixelSize(R.dimen.padding_medium)
    // PUBLIC_INTERFACE
    fun paddingLargePx(context: Context): Int = context.resources.getDimensionPixelSize(R.dimen.padding_large)
}
