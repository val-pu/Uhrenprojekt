package leko.valmx.uhrenprojekt.newP.widgets

import androidx.recyclerview.widget.GridLayoutManager
import leko.valmx.uhrenprojekt.appearance.colors.ColorPickerAdapter

class ColorWidget : Widget() {
    override fun getWidgetID(): Int = WidgetHelper.COLOR_WIDGET

    override fun init() {
        title("Farberscheinung")
        description("Ändere das Thema der Uhr")
        recycler(
            "Voreinstellungen",
            ColorPickerAdapter(),
            "Voreingestellte Farbthema",
            GridLayoutManager(context, 4)
        )
        redirect("Hintergrundfarbe","Setze eine eigene Hintergrundfarbe") {}
        redirect("Vordergrundfarbe","Setze eine eigene Vordergrundfarbe") {}
        command("Alle Farben","showcolors","Zeigt alle verfügbaren Farben an")
        command("Zufallsfarbe","cc","Wechselt die Farbe des Displays auf eine Zufallsfarbe")
    }
}