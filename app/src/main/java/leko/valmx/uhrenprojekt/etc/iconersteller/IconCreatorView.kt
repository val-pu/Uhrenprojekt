package leko.valmx.uhrenprojekt.etc.iconersteller

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet

class IconCreatorView(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatImageView(context!!, attrs) {

    lateinit var grid: Array<Array<PixelInfo>>

    override fun onDraw(c: Canvas?) {
        super.onDraw(c)

        if (bm == null) init()

    }

    var bm: Bitmap? = null
    val canvas = Canvas()
    var padding = 100
    lateinit var params: IconErstellerParams

    private fun init() {
        bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bm)
        background = BitmapDrawable(bm)

        grid = Array(11, { i -> Array(11) { j -> PixelInfo(i, j) } })

        params = IconErstellerParams(context, canvas, this)

        params.paintManager.init()
        params.dimensionManager.init()
        params.drawManager.drawEverything()
        setOnTouchListener(params.interactionManager)


    }

    fun drawClockMatrix() {

    }


    class PixelInfo(var x: Int, var y: Int) {
        var isOn = false
        var color = 0 // TODO add color

    }

}