package leko.valmx.uhrenprojekt.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class ProcessFeedBackView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var bitmap: Bitmap? = null
    private var canvas: Canvas = Canvas()


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (bitmap != null) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            canvas!!.setBitmap(bitmap)

        }

    }
}