package com.ansgar.harbor.component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.text.TextUtils
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

    @SuppressLint("PrivateResource")
    // icon1
    private val icon = ContextCompat.getDrawable(context, R.drawable.abc_ic_search_api_material)!!
    private var isSearchable: Boolean = true
    var ic = Icon(icon)

    // icon2
    private val icon2 = ContextCompat.getDrawable(context, R.drawable.abc_ic_search_api_material)!!
    private var isSearchable2: Boolean = true
    var ic2 = Icon(icon2)

    var text = " "
    private val textPaint = TextPaint().apply {
        color = ContextCompat.getColor(context, R.color.colorAccent)
        isAntiAlias = true
        style = Paint.Style.FILL
        textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20f, displayMetrics)
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
    }
    private val textBound = Rect()
    private var yShift = 0f

    private val text2 = ""
    private val textPaint2 = TextPaint(textPaint).apply {
        textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12f, displayMetrics)
    }
    private val textBound2 = Rect()
    private var yShift2 = 0f

    class Icon(private val icon: Drawable) {
        private var scale = 0f
        private var scaledH = 0f
        private var scaledW = 0f

        fun measure(sx: Float, sy: Float, lineHeight: Float) : Float {
            icon.run {
                scale = lineHeight / intrinsicHeight
                scaledH = intrinsicHeight * scale
                scaledW = intrinsicWidth * scale
                setBounds(sx.roundToInt(), (sy - scaledH / 2f).roundToInt(), (sx + scaledW).roundToInt(), (sy + scaledH / 2f).roundToInt())
            }
            return scaledW
        }

        fun draw(canvas: Canvas) {
            icon.draw(canvas)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        measureBound(w,h)
    }

    override fun invalidate() {
        super.invalidate()
        measureBound(width,height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Icon
        if (isSearchable) {
            ic.draw(canvas)
        }

        if (isSearchable2) {
            ic2.draw(canvas)
        }
        canvas.drawText(text, textBound.left.toFloat(), yShift, textPaint)
        canvas.drawText(text2, textBound2.left.toFloat(), yShift2, textPaint2)
    }

    private fun measureBound(w: Int, h: Int) {
        // Mostly icon will align text size, get text bound first
        textPaint.getTextBounds(text, 0, text.length, textBound)

        var drawCenterX = paddingStart.toFloat()
        val drawCenterY = textBound.height() / 2f + paddingTop

        if (isSearchable) {
            drawCenterX += ic.measure(drawCenterX, drawCenterY, textBound.height().toFloat())
        }

        if (isSearchable2) {
            drawCenterX += ic2.measure(drawCenterX, drawCenterY, textBound.height().toFloat())
        }

        textPaint2.getTextBounds(text2, 0, text2.length, textBound2)
        yShift2 = drawCenterY - textBound2.centerY()
        textBound2.offset(w - textBound2.width(), yShift.roundToInt())

        yShift = drawCenterY - textBound.centerY()
        // Shift base on pre icon
        textBound.offset(drawCenterX.roundToInt(), yShift.roundToInt())
        text = TextUtils.ellipsize(text, textPaint,textBound2.left - drawCenterX, TextUtils.TruncateAt.END).toString()
        textPaint.style = Paint.Style.STROKE
        drawCenterX += textBound.width()
    }

}

