package leko.valmx.uhrenprojekt.newP.widgets

import android.content.Intent
import leko.valmx.uhrenprojekt.developertools.DeveloperActivity
import leko.valmx.uhrenprojekt.game.SinkShipActivity
import leko.valmx.uhrenprojekt.newP.parents.Widget
import leko.valmx.uhrenprojekt.popup.LoadingDialog

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