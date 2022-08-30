package leko.valmx.uhrenprojekt.widgets

import android.content.Intent
import leko.valmx.uhrenprojekt.etc.game.SinkShipActivity
import leko.valmx.uhrenprojekt.parents.Widget

class GameWidget : Widget(){
    override fun init() {
        title("Games")
        description("")

        redirect("Schiffeversenken", "", {
            val intent = Intent(context, SinkShipActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        })

        redirect("Tetris", "", {
            //TODO Weitrleitung zum Spiel
        })
    }
}