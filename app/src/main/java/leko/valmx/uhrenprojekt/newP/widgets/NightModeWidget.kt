package leko.valmx.uhrenprojekt.newP.widgets

import android.widget.Toast
import leko.valmx.uhrenprojekt.newP.parents.Widget
import leko.valmx.uhrenprojekt.popup.CorrectionBottomSheet
import leko.valmx.uhrenprojekt.popup.InputBottomSheet
import leko.valmx.uhrenprojekt.popup.InvalidInputInterface

class NightModeWidget : Widget(), InvalidInputInterface{
    override fun init() {
        title("Nachtmodus")

        redirect("Beginn", "Setze den Beginn des Nachtmodus") {
            InputBottomSheet("Nachtmodus - Beginn","Wann soll der Nachtmodus einsetzen?" ,"hh:mm","seton", this).show(this.context)
        }

        redirect("Ende", "Setze das Ende des Nachtmodus") {
            InputBottomSheet(
                "Nachtmodus - Ende",
                "Wann soll der Nachtmodus enden?",
                "hh:mm",
                "setof",
                this
            ).show(this.context)
        }

        redirect("Helligkeit im Nachtmodus", "Setze die Helligkeit der Uhr im Nachtmodus") {
            InputBottomSheet(
                "Nachtmodus - Helligkeit", "Wie hoch soll die Helligkeit des Nachtmodus werden" +
                        "? (0 - 255)", "hh:mm","setnb", this
            ).show(this.context)
        }

    }

    override fun onInvalidInput() {
        Toast.makeText(this.context, "Fehlerhafte Eingabe", Toast.LENGTH_SHORT).show()
    }

}