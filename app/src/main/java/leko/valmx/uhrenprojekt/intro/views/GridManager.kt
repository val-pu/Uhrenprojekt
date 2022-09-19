package leko.valmx.uhrenprojekt.intro.views

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import androidx.core.graphics.toRectF
import kotlin.math.roundToInt

class GridManager(val v: UhrTitelView) {

    var bm: Bitmap? = null
    val charsPerLine = 15
    var step = 0
    var padding = 0
    var radius = 0F

    fun init() {

        padding = (v.width * .02F).roundToInt()

        step = (v.width - 2 * padding) / charsPerLine
        bm = Bitmap.createBitmap(
            v.width,
            step * v.textArr.size + padding * 2,
            Bitmap.Config.ARGB_8888
        )

        v.background = BitmapDrawable(bm)
        v.c = Canvas(bm!!)

        radius = v.c.height*.1F

    }

    fun getLetterRect(x: Int, y: Int): RectF {
        var r = Rect(
            padding + x * step,
            y * step + padding,
            (x + 1) * step + padding,
            (y + 1) * step + padding
        ).toRectF()

        r.inset(r.width()*.1F, (-r.width()*.4).toFloat())
        return r
    }


}