package leko.valmx.uhrenprojekt.etc.iconersteller

import android.graphics.RectF

class DimensionManager(val params: IconErstellerParams) {

    var padding = 0F
    var step = 0F

    fun init() {

        val canvas = params.canvas

        val width = canvas.width

        padding = width * .02F

        step = (width - padding * 2) / 11F


    }

    fun getRectF(x: Int, y: Int): RectF =
        RectF(
            padding + +x * step,
            padding + y * step,
            padding + (x + 1) * step,
            padding + (y + 1) * step
        )

    fun convertY(y: Float) = ((y - padding) / step).toInt()
    fun convertX(x: Float) = ((x - padding) / step).toInt()

    fun isValidXY(x: Int) = x in 0..10

}