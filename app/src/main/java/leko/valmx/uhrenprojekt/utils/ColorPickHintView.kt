package leko.valmx.uhrenprojekt.utils

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.core.graphics.toRectF
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.util.Util

class ColorPickHintView(context: Context?, attrs: AttributeSet?) : androidx.appcompat.widget.AppCompatImageView(context!!, attrs) {

    var textColor: Color = Color.valueOf(255F, 255F, 255F, 255F)
    var bgColor: Color = Color.valueOf(0)
    var p = Color.BLACK

    fun setColor(textColor: Color, bgColor: Color) {

        this.textColor = textColor
        this.bgColor = bgColor

        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {


        // Farben werden initialisiert

        val textPaint = Paint().apply { color = textColor.toArgb() }
        val bgPaint = Paint().apply { color = context!!.getColor(R.color.card) }

        // Wichtige Werte
        val radius = height * .1F
        val insetAmount = height * .1F


        val rect = Rect(0, 0, width, height).toRectF()
        val wallPaint = Paint().apply {
            color = bgColor.toArgb()
            alpha = 100
        }
        canvas!!.drawRoundRect(rect, radius, radius, wallPaint)
        rect.inset(insetAmount, insetAmount)

        super.onDraw(canvas)

        bgPaint.color = p

        canvas.drawRoundRect(rect, radius, radius, bgPaint)
        Util.drawText("A", rect, canvas, textPaint, context)


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

}