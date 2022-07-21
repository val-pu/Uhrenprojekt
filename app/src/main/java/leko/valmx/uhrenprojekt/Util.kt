package leko.valmx.uhrenprojekt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Vibrator
import android.util.Log
import android.view.ViewGroup

object Util {
    fun vibrate(ms: Int, context: Context) {
//        if (!DataManager.getSetting("vibration")) return
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(ms.toLong())
    }

    fun drawText(text: String, rect: RectF, canvas: Canvas, textPaint: Paint, context: Context) {

        textPaint.textAlign = Paint.Align.CENTER;


        val xPos = rect.left + rect.width() / 2
        textPaint.textSize =
            (((rect.width()) / (text.length).toFloat()).toFloat())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textPaint.typeface = context.resources.getFont(R.font.quicksand_medium)
        }

        val yPos =
            (rect.top) + rect.height() / 2 - ((textPaint.descent() + textPaint.ascent()) / 2)



        canvas.drawText(text, xPos, yPos, textPaint)
    }

}