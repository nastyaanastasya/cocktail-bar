package com.nastyaanastasya.cocktailbar.presentation.fragment.main.rv.itemdecorator

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecorator(
    context: Context,
    spacing: Float = 8f
) : RecyclerView.ItemDecoration() {

    private val spacingPx: Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        spacing,
        context.resources.displayMetrics
    ).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacing = (spacingPx * 0.5).toInt()
        val viewHolder = parent.getChildViewHolder(view)

        val currentPosition = parent.getChildAdapterPosition(view).takeIf {
            it != RecyclerView.NO_POSITION
        } ?: viewHolder.oldPosition

        when (currentPosition) {
            0 -> {
                outRect.bottom = spacing
            }
            state.itemCount - 1 -> {
                outRect.top = spacing
            }
            else -> {
                outRect.top = spacing
                outRect.bottom = spacing
            }
        }
        outRect.left = spacing
        outRect.right = spacing
    }
}
