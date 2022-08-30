package leko.valmx.uhrenprojekt.widgets

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.recyclerview.widget.GridLayoutManager
import com.maxkeppeler.sheets.color.ColorSheet
import leko.valmx.uhrenprojekt.adapters.widgets.ColorAdapter
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.bundles.ChoiceItem
import leko.valmx.uhrenprojekt.parents.Widget

class ColorWidget : Widget() {

    @SuppressLint("NewApi")
    override fun init() {
        title("Farberscheinung")
        description("Ändere das Thema der Uhr")
        recycler(
            "Voreinstellungen",
            ColorAdapter(),
            "Voreingestellte Farbthema",
            GridLayoutManager(view.context, 5)
        )

        redirect("Hintergrundfarbe", "Setze eine eigene Hintergrundfarbe") {
            ColorSheet().show(context!!) {
                title("Hintergrundfarbe")
                onPositive {
                    val color = Color.valueOf(it)
                    Blue.sendCommand("setbg ${(color.red()*255).toInt()},${(color.green()*255).toInt()},${(color.blue()*255).toInt()}")

                }
            }
        }
        redirect("Vordergrundfarbe", "Setze eine eigene Vordergrundfarbe") {

            ColorSheet().show(context) {
                title("Vordergrundfarbe")
                disableAlpha()
                onPositive {
                    val color = Color.valueOf(it)
                    Blue.sendCommand("setdp ${(color.red()*255).toInt()},${(color.green()*255).toInt()},${(color.blue()*255).toInt()}")
                }
            }

        }

        multipleChoice(command = "mc", name = "Textfarbmodus") {
            it.add(ChoiceItem("Einfarbig","Jedes Wort in der selben Farbe","0"))
            it.add(ChoiceItem("Leicht Variiert","Jedes Wort in einer leicht anderen Farbe","1"))
            it.add(ChoiceItem("Zufällig","Jedes Wort in einer zufälligen Farbe","2"))
        }
        multipleChoice(command = "sm", name = "Texterscheinungsmodus") {
            it.add(ChoiceItem("Unmittelbar","Neue Worte erscheinen unmittelbar","0"))
            it.add(ChoiceItem("Von links nach rechts","Neue Worte laufen von links nach rechts herein","1"))
            it.add(ChoiceItem("Alle von rechts nach links","Alle Worte laufen nach links hinaus und von rechts hinein","2"))
            it.add(ChoiceItem("Gedimmt","Neue Worte erscheinen sanft gedimmt","3"))
            it.add(ChoiceItem("Flackernd","Neue Worte erscheinen flackernd","4"))
        }

        command("Alle Farben", "showcolors", "Zeige alle verfügbaren Farben an")
        command("Zufallsfarbe", "cc", "Wechsel die Farbe des Displays auf eine Zufallsfarbe")
        command("Hintergrund", "bg", "Schaltet den Hintergund An/Aus")
        command("Smoothmode", "sm", "Schaltet den Hintergund An/Aus")
    }
}