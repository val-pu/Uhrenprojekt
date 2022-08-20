package leko.valmx.uhrenprojekt.newP.widgets

import androidx.recyclerview.widget.GridLayoutManager
import leko.valmx.uhrenprojekt.appearance.colors.ColorPickerAdapter
import leko.valmx.uhrenprojekt.newP.bundles.ChoiceItem
import leko.valmx.uhrenprojekt.newP.bundles.OnInputSendListener

class TextWidget() : Widget() {
    override fun getWidgetID(): String = javaClass.name

    override fun init() {
        title("Text")
        description("Zeige Text an")


        multipleChoice("Gespeicherte Texte abspielen","") {
            it.add(ChoiceItem("1","Text 1","1"))
            it.add(ChoiceItem("2","Text 2","2"))
            it.add(ChoiceItem("3","Ausgabe um 9:56 Uhr und 10:04 Uhr","3"))
            it.add(ChoiceItem("4","Ausgabe um 11:50 Uhr und 11:57 Uhr","4"))
            it.add(ChoiceItem("5","Ausgabe um 13:46 Uhr und 13:57 Uhr","5"))
        }

        input("Sofortausgabe", "Gibt den nachfolgenden Text sofort aus", object : OnInputSendListener {
            override fun onInput(text: String) {

            }

        })


    }
}