package leko.valmx.uhrenprojekt.newP.widgets

import leko.valmx.uhrenprojekt.newP.parents.Widget

class GameWidget : Widget(){
    override fun init() {
        title("Games")
        description("")

        redirect("Schiffeversenken", "", {
            //TODO Weitrleitung zum Spiel
        })

        redirect("Tetris", "", {
            //TODO Weitrleitung zum Spiel
        })
    }
}