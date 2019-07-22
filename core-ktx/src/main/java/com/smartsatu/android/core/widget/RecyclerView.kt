package com.smartsatu.android.core.widget

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.smartsatu.android.core.R

/**
 * Author
 * Created by danaimset on 2019-04-02 and 23:51.
 */

typealias SpanSizeLookup = (position: Int) -> Int

fun RecyclerView.addVerticalDividers(@DrawableRes resId: Int = 0) {
    val drawable = ContextCompat.getDrawable(context, resId)
            ?: throw IllegalArgumentException("Drawable cannot be retrieved")
    addVerticalDividers(drawable)
}

fun RecyclerView.addVerticalDividers(drawable: Drawable) {
    val verticalDivider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    verticalDivider.setDrawable(drawable)
    addVerticalDividers(verticalDivider)
}

fun RecyclerView.addVerticalDividers(divider: DividerItemDecoration) {
    if (getTag(R.id.tagDecoratorVertical) == null) {
        setTag(R.id.tagDecoratorVertical, divider)
        addItemDecoration(divider)
    }
}

fun RecyclerView.addHorizontalDividers(@DrawableRes resId: Int = 0) {
    val drawable = ContextCompat.getDrawable(context, resId)
            ?: throw IllegalArgumentException("Drawable cannot be retrieved")
    addHorizontalDividers(drawable)
}

fun RecyclerView.addHorizontalDividers(drawable: Drawable) {
    val horizontalDivider = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
    horizontalDivider.setDrawable(drawable)
    addHorizontalDividers(horizontalDivider)
}

fun RecyclerView.addHorizontalDividers(divider: DividerItemDecoration) {
    if (getTag(R.id.tagDecoratorHorizontal) == null) {
        setTag(R.id.tagDecoratorHorizontal, divider)
        addItemDecoration(divider)
    }
}

fun RecyclerView.addHorizontalAndVerticalDividers(hDrawable: Drawable, vDrawable: Drawable) {
    addHorizontalDividers(hDrawable)
    addVerticalDividers(vDrawable)
}

fun RecyclerView.addHorizontalAndVerticalDividers(@DrawableRes hResId: Int = 0, @DrawableRes vResId: Int = 0) {
    addHorizontalDividers(hResId)
    addVerticalDividers(vResId)
}

fun RecyclerView.addDividers(@DrawableRes hResId: Int = 0, @DrawableRes vResId: Int = 0) {
    when (val layoutManager: RecyclerView.LayoutManager = this.layoutManager
            ?: throw IllegalStateException("LayoutManager must be set")) {
        is GridLayoutManager, is StaggeredGridLayoutManager -> {
            addHorizontalAndVerticalDividers(hResId, vResId)
        }

        is LinearLayoutManager -> {
            if (layoutManager.orientation == RecyclerView.VERTICAL) {
                addVerticalDividers(vResId)
            } else {
                addHorizontalDividers(hResId)
            }
        }
    }
}

fun RecyclerView.decorateByDefault(@RecyclerView.Orientation orientation: Int, nestedScrollingEnabled: Boolean = true, animateChanges: Boolean = false, @DrawableRes drawableResId: Int = 0) {
    layoutManager = LinearLayoutManager(context, orientation, false)
    ViewCompat.setNestedScrollingEnabled(this, nestedScrollingEnabled)
    if (animateChanges) {
        itemAnimator = DefaultItemAnimator()
    }
    addDividers(drawableResId, drawableResId)
}

fun RecyclerView.decorateByDefault(@RecyclerView.Orientation orientation: Int, nestedScrollingEnabled: Boolean = true, animateChanges: Boolean = false, @DrawableRes hResId: Int = 0, @DrawableRes vResId: Int = 0) {
    layoutManager = LinearLayoutManager(context, orientation, false)
    ViewCompat.setNestedScrollingEnabled(this, nestedScrollingEnabled)
    if (animateChanges) {
        itemAnimator = DefaultItemAnimator()
    }
    addDividers(hResId, vResId)
}

fun RecyclerView.decorateByDefault(@RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL, spanCount: Int, isStaggered: Boolean = false, spanSizeLookup: SpanSizeLookup? = null, nestedScrollingEnabled: Boolean = true, @DrawableRes divider: Int = 0) {
    this.decorateByDefault(orientation, spanCount, isStaggered, spanSizeLookup, nestedScrollingEnabled, divider, divider)
}

@SuppressLint("WrongConstant")
fun RecyclerView.decorateByDefault(@RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL, spanCount: Int, isStaggered: Boolean = false, spanSizeLookup: SpanSizeLookup? = null, nestedScrollingEnabled: Boolean = true, @DrawableRes hResId: Int = 0, @DrawableRes vResId: Int = 0) {
    val layoutManager: RecyclerView.LayoutManager = if (isStaggered) {
        StaggeredGridLayoutManager(spanCount, orientation)
    } else {
        GridLayoutManager(context, spanCount, orientation, false)
    }
    when (layoutManager) {
        is GridLayoutManager -> {
            if (spanSizeLookup != null && !isStaggered) {
                layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return spanSizeLookup(position)
                    }
                }
            }
        }
    }

    this.layoutManager = layoutManager
    ViewCompat.setNestedScrollingEnabled(this, nestedScrollingEnabled)
    addDividers(hResId, vResId)
}