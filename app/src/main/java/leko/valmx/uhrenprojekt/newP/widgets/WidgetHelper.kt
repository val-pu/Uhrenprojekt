package leko.valmx.uhrenprojekt.newP.widgets

import java.util.*

object WidgetHelper {

    val widgets = LinkedList<Class<Widget>>()
    val LOTTO_WIDGET = 1
    val COLOR_WIDGET = 2
    val ICON_WIDGET = 2

    val PREF_ID = "UHR_WIDGETS"
    val SAVED_ID = "SAVED"


    fun getWidget(id: Int): Widget {
        return widgets[id].newInstance()
    }

    fun getSavedWidgets(): LinkedList<Widget> {
        return LinkedList<Widget>().apply {
            add(LottoWidget())
            add(ColorWidget())
            add(IconWidget())
        }
    }


}