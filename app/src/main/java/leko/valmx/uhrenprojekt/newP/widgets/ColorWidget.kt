package leko.valmx.uhrenprojekt.newP.widgets

import androidx.recyclerview.widget.GridLayoutManager
import com.maxkeppeler.sheets.color.ColorSheet
import leko.valmx.uhrenprojekt.appearance.colors.ColorPickerAdapter
import leko.valmx.uhrenprojekt.newP.bundles.ChoiceItem

class ColorWidget : Widget() {
    override fun getWidgetID(): String = javaClass.name

    override fun init() {
        title("Farberscheinung")
        description("Ändere das Thema der Uhr")
        recycler(
            "Voreinstellungen",
            ColorPickerAdapter(),
            "Voreingestellte Farbthema",
            GridLayoutManager(context, 4)
        )

        redirect("Hintergrundfarbe", "Setze eine eigene Hintergrundfarbe") {
            ColorSheet().show(context!!) {
                title("Hintergrundfarbe")
                onPositive {

                }
            }
        }
        redirect("Vordergrundfarbe", "Setze eine eigene Vordergrundfarbe") {

            ColorSheet().show(context!!) {
                title("Vordergrundfarbe")
                onPositive {

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
        command("Zufallsfarbe", "cc", "Wechsle die Farbe des Displays auf eine Zufallsfarbe")
    }
}