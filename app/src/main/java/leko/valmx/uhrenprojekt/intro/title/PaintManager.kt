package leko.valmx.uhrenprojekt.intro.title

import android.graphics.Color
import android.graphics.Paint
import leko.valmx.uhrenprojekt.R

class PaintManager(val v: UhrTitelView) {

    val activePaint = Paint()
    val inactivePaint = Paint()
    val backPaint = Paint()

    fun init() {
        val resources = v.resources

        activePaint.color = resources.getColor(R.color.titelview_title)
        inactivePaint.color = resources.getColor(R.color.titelview_obfuscated)
        backPaint.color = resources.getColor(R.color.titelview_back)

        activePaint.isFakeBoldText = true
        activePaint.setShadowLayer(10F,0F,0F, Color.WHITE)

    }

}