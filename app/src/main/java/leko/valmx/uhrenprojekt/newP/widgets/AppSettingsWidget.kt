package leko.valmx.uhrenprojekt.newP.widgets

import leko.valmx.uhrenprojekt.newP.bundles.ChoiceItem
import leko.valmx.uhrenprojekt.newP.parents.Widget

class AppSettingsWidget: Widget() {
    override fun init() {
        title("App Einstellungen")
        description("")

        multipleChoice("Design", "") {
            it.add(ChoiceItem("Helles Design","","1"))
            it.add(ChoiceItem("Dunkles Design","","2"))
        }

    }
}