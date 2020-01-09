package com.example.financeapp.analytics

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View

class AnalyticsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val padding: Int = dpToPx(16f)
    private val data = intArrayOf(1000, 2000, 2000, 5000, 10000)
    private val colors = arrayOf(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawChart(canvas)
    }

    private fun drawChart(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        val graphWidth = (width - padding * 2).toFloat()
        val sum = data.sum()
        var offset : Float
        var ratio : Float
        var left : Float
        var top = padding.toFloat()
        var right = padding.toFloat()
        var bottom = (height - padding).toFloat()
        for ((i, amount) in data.withIndex()) {
            paint.color = colors[i % colors.size]
            ratio = amount.toFloat() / sum.toFloat()
            offset = graphWidth * ratio
            left = right
            right = left + offset
            canvas.drawRect(left, top, right, bottom, paint)
        }
    }

    private fun dpToPx(dp: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        ).toInt()
    }
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}