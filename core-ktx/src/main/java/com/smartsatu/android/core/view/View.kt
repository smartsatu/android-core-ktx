package com.smartsatu.android.core.view

import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView

/**
 * Author
 * Created by danaimset on 9/2/18 and 2:49 PM.
 */
fun View.inflater(): LayoutInflater = LayoutInflater.from(context)

fun <T : View> T.observeScroll(observer: (T) -> Unit) {
    when (this) {
        is RecyclerView -> {
            val listener = object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    observer.invoke(this@observeScroll)
                }
            }
            addOnScrollListener(listener)
        }
        is NestedScrollView -> {
            setOnScrollChangeListener { v: NestedScrollView?, _: Int, _: Int, _: Int, _: Int ->
                observer.invoke(this@observeScroll)
            }
        }
    }
}