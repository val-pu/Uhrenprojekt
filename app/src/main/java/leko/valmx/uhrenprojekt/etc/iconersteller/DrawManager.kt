package leko.valmx.uhrenprojekt.etc.iconersteller

import android.widget.Toast
import leko.valmx.uhrenprojekt.util.Util

class DrawManager(val params: IconErstellerParams) {

    val text =
        "ES@IST.KURZGLEICHGENAUFÜNFZWANZIGZEHNVIERTELVOR+Ä:PNACHHALB?ELFÜNFEINSX!QZWEIDREI#MJVIERSECHSYßACHTSIEBENZWÖLFZEHNEUN-UHR"

    fun drawEverything() {
        val dimensionManager = params.dimensionManager
        val paintManager = params.paintManager
        val v = params.v
        val grid = v.grid
        val backPaint = paintManager.backPaint

        val canvas = params.canvas

        canvas.drawPaint(backPaint)

        for (xRow in grid) {

            for (pixel in xRow) {

                val p = if (pixel.isOn) paintManager.textOnPaint else paintManager.textOffPaint

                val textID = pixel.x + pixel.y * 11

                val rect = dimensionManager.getRectF(pixel.x, pixel.y)

                Util.drawText(text[textID].toString(), rect, canvas, p, params.ctx)

            }

        }
        v.invalidate()

        Toast.makeText(v.context,"TOats made",Toast.LENGTH_SHORT).show()


    }


}