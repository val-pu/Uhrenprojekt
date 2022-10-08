package leko.valmx.uhrenprojekt.widgets

import android.widget.Toast
import com.maxkeppeler.sheets.time.TimeFormat
import com.maxkeppeler.sheets.time.TimeSheet
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.parents.Widget
import leko.valmx.uhrenprojekt.popup.InvalidInputInterface

class NightModeWidget : Widget() {
    override fun init() {
        title("Nachtmodus")

        redirect("Beginn", "Setze den Beginn des Nachtmodus") {
            TimeSheet().show(context) {
                format(TimeFormat.HH_MM)

                title("Start Um:")
                onPositive {
                    val hh = it / (1000 * 60)
                    val mm = (it % (1000 * 60)) / (1000)
                    Blue.sendCommand("seton $hh:$mm")
                }
            }
        }

        redirect("Ende", "Setze das Ende des Nachtmodus") {
            TimeSheet().show(context) {
                format(TimeFormat.HH_MM)

                title("Ende Um:")
                onPositive {
                    val hh = it / (1000 * 60)
                    val mm = (it % (1000 * 60)) / (1000)
                    Blue.sendCommand("setoff $hh:$mm")
                }
            }
        }

        //TODO Helligkeitspopup hinzufügen

/*        redirect("Helligkeit im Nachtmodus", "Setze die Helligkeit der Uhr im Nachtmodus") {
            InputBottomSheet(
                "Nachtmodus - Helligkeit", "Wie hoch soll die Helligkeit des Nachtmodus werden" +
                        "? (0 - 255)", "hh:mm", "setnb", this
            ).show(this.context)



        }*/

    }


}