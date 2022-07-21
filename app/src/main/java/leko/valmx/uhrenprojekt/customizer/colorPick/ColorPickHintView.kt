package leko.valmx.uhrenprojekt.customizer.colorPick

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.toRectF
import leko.valmx.uhrenprojekt.utilities.Util

class ColorPickHintView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var textColor: Color = Color.valueOf(255F, 255F, 255F, 255F)
    var bgColor: Color = Color.valueOf(0F, 100F, 255F, 255F)

    fun setColor(textColor: Color, bgColor: Color) {

        this.textColor = textColor
        this.bgColor = bgColor

        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {


        // Farben werden initialisiert

        val bgPaint = Paint().apply { color = Color.DKGRAY }
        val textPaint = Paint().apply { color = textColor.toArgb() }

        // Wichtige Werte
        val radius = height * .1F
        val insetAmount = height * .1F


        val rect = Rect(0, 0, width, height).toRectF()
        val wallPaint = Paint().apply {
            color = bgColor.toArgb()
            alpha = 90
        }
        canvas!!.drawRoundRect(rect, radius / 2F, radius / 2F, wallPaint)
        rect.inset(insetAmount, insetAmount)

        super.onDraw(canvas)

        canvas.drawRoundRect(rect, radius, radius, bgPaint)
        Util.drawText("A", rect, canvas, textPaint, context)


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }

}