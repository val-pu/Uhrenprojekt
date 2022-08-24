package leko.valmx.uhrenprojekt.newP.iconersteller

import android.graphics.Color
import android.graphics.Paint
import leko.valmx.uhrenprojekt.R

class PaintManager(val params: IconErstellerParams) {

    val backPaint = Paint()
    val textOnPaint = Paint()
    val textOffPaint = Paint()

    fun init() {

        val res = params.ctx.resources

        backPaint.color = res.getColor(R.color.black)
        textOnPaint.color = Color.YELLOW
        textOffPaint.color = Color.WHITE

    }

}