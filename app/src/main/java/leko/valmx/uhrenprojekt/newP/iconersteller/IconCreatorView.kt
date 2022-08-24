package leko.valmx.uhrenprojekt.newP.iconersteller

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet

class IconCreatorView(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageView(context!!, attrs) {

    lateinit var grid: Array<Array<PixelInfo>>

    override fun onDraw(c: Canvas?) {
        super.onDraw(c)

        if (bm != null) init()

    }

    var bm: Bitmap? = null
    val canvas = Canvas()
    var padding = 100

    private fun init() {
        bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bm)

        grid = Array(11 ,{ _ -> Array(11) { _ -> PixelInfo() } })
    }

    fun drawClockMatrix() {

    }


    class PixelInfo() {
        var isOn = false
        var color = 0 // TODO add color
    }

}