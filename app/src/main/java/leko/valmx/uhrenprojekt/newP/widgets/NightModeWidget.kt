package leko.valmx.uhrenprojekt.newP.widgets

import leko.valmx.uhrenprojekt.newP.parents.Widget

class NightModeWidget : Widget() {
    override fun init() {
        title("Nachtmodus")

        redirect("Beginn", "Setze den Beginn des Nachtmodus", {

        })

        redirect("Ende", "Setze das Ende des Nachtmodus", {

        })

        redirect("Helligkeit im Nachtmodus", "Setze die Helligkeit der Uhr im Nachtmodus", {

        })


    }

}