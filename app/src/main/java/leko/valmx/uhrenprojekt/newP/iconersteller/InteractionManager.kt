package leko.valmx.uhrenprojekt.newP.iconersteller

import android.view.MotionEvent
import android.view.View

class InteractionManager(val params: IconErstellerParams) : View.OnTouchListener {
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {


        if (event != null) processClick(event)

        return true
    }

    fun processClick(e: MotionEvent) {
        val dimensionManager = params.dimensionManager

        val x = dimensionManager.convertX(e.x)
        val y = dimensionManager.convertY(e.y)

        if (!dimensionManager.isValidXY(x)) return
        if (!dimensionManager.isValidXY(y)) return

        params.v.grid[x][y].isOn = !params.v.grid[x][y].isOn

        params.drawManager.drawEverything()


    }

}