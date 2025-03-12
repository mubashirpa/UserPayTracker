package com.example.userpaytracker.presentation.core.utils

import android.content.Context
import android.util.TypedValue

fun dpToPx(
    dp: Float,
    context: Context,
): Int =
    TypedValue
        .applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics,
        ).toInt()
