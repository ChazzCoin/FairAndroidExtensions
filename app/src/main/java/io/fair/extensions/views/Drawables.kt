package io.fair.extensions.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun GradientDrawable.setCornerRadius(topLeft: Float = 0F, topRight: Float = 0F, bottomRight: Float = 0F, bottomLeft: Float = 0F) {
    cornerRadii = arrayOf(topLeft, topLeft, topRight, topRight, bottomRight, bottomRight, bottomLeft, bottomLeft).toFloatArray()
}

internal fun Context.getDrawableCompat(@DrawableRes drawable: Int) = ContextCompat.getDrawable(this, drawable)

fun getDrawable(context: Context?, drawable: Int): Drawable? {
    return context?.getDrawableCompat(drawable)
}