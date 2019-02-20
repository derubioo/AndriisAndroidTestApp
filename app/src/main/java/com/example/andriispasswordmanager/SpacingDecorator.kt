package com.example.andriispasswordmanager

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpacingDecorator(private val spacing: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State)
    {
        //outRect.top = spacing
        //outRect.left = spacing
        //outRect.right = spacing
        outRect.bottom = spacing
    }
}