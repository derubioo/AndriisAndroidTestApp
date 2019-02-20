package com.example.andriispasswordmanager

import android.content.Context
import android.util.TypedValue

class Utils {
    companion object {

        fun dpToPx(context: Context, dp: Int): Int {
            return Math.round(
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics)
            )
        }
    }
}