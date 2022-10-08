package leko.valmx.uhrenprojekt.widgets

import android.widget.Toast
import leko.valmx.uhrenprojekt.parents.Widget
import leko.valmx.uhrenprojekt.popup.InvalidInputInterface

class AlarmWidget: Widget() {
    override fun init() {
        title("Alarm")
        description("Setze einen Reminder")

        redirect("Bestimmte Uhrzeit", "Setze einen Alarm auf eine bestimmte Uhrzeit") {
//            InputBottomSheet(
//                "Alarm - Uhrzeit",
//                "Wann soll der Alarm einsetzen?",
//                "hh:mm:(:ss)",
//                "setal",
//                this
//            ).show(this.context)
        }

        redirect("Klingeldauer", "Setzte die länge des Alarms") {
//            InputBottomSheet(
//                "Alarm - Dauer",
//                "Wie lange soll der Alarm andauern? (0 - 20)",
//                "1 -> 3 Sek",
//                "setad",
//                this
//            ).show(this.context)

        }
    }

}