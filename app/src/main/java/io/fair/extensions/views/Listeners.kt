package io.fair.extensions.views

import android.annotation.SuppressLint
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import io.fair.extensions.getWindowWidthAndHeight

/**
 * Ability to Drag and Drop ImageViews freely
 * Only works with a RelativeLayout!
 */
@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("ClickableViewAccessibility")
fun View?.onMoveListener() {
    // Window Height and Width

    val windowWidthAndHeight = this?.context?.getWindowWidthAndHeight()
    val windowWidth = windowWidthAndHeight?.first ?: 0
    val windowHeight = windowWidthAndHeight?.second ?: 0
    // OnTouch
    this?.setOnTouchListener { _, event ->
        val layoutParams: RelativeLayout.LayoutParams = this.layoutParams as RelativeLayout.LayoutParams
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {}
            MotionEvent.ACTION_MOVE -> {
                var x_cord = event.rawX.toInt()
                var y_cord = event.rawY.toInt()
                if (x_cord > windowWidth) {
                    x_cord = windowWidth
                }
                if (y_cord > windowHeight) {
                    y_cord = windowHeight
                }
                layoutParams.leftMargin = x_cord - 100
                layoutParams.topMargin = y_cord - 200
                this.layoutParams = layoutParams
            }
            else -> {}
        }
        true
    }
}