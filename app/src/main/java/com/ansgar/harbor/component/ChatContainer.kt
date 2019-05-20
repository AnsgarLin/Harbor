package com.ansgar.harbor.component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.ansgar.harbor.R
import kotlin.math.roundToInt

fun Drawable.setBoundWithLeftCenter(x: Int, y: Int) {
    setBounds(x, y - intrinsicHeight / 2, x + intrinsicWidth, y + intrinsicHeight)
}

class ChatContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val displayMetrics = context.resources.displayMetrics
    private val textPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        isAntiAlias = true
        style = Paint.Style.FILL
        textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, displayMetrics)
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
    }
    @SuppressLint("PrivateResource")
    private val icon = ContextCompat.getDrawable(context, R.drawable.abc_ic_search_api_material)!!
    private val textBound = Rect()
    private var isSearchable: Boolean = false

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // icon | Text
        // Icon will align text size, get text bound first
        val text = "abcd efg hijk"
        textPaint.getTextBounds(text, 0, text.length, textBound)
        val drawCenterX = width / 2f
        val drawCenterY = height / 2f
        // Dot
        canvas.drawCircle(drawCenterX, drawCenterY, 1f, textPaint)
        // Shift bound
        textBound.offset(width / 2, height / 2)
        // Icon
        if (isSearchable) {
            icon?.run {
                val scale = textBound.height().toFloat() / intrinsicHeight.toFloat()
                val targetW = scale * intrinsicWidth.toFloat()
                val targetH = textBound.height()
                setBounds(drawCenterX.roundToInt(), drawCenterY.roundToInt() - targetH / 2, (drawCenterX + targetW).roundToInt(), drawCenterY.roundToInt() + targetH / 2)
                draw(canvas)
            }
        }

        textPaint.style = Paint.Style.STROKE
        val yShift = drawCenterY - textBound.centerY()
        textBound.offset(if (isSearchable) icon.bounds.width() else 0 , yShift.toInt())
        canvas.drawRect(textBound, textPaint)
        canvas.drawText(
            text,
            textBound.left.toFloat(),
            drawCenterY + yShift,
            textPaint
        )
    }
}

