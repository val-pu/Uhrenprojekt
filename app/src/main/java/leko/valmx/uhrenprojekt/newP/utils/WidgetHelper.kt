package leko.valmx.uhrenprojekt.newP.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import leko.valmx.uhrenprojekt.newP.parents.Widget
import leko.valmx.uhrenprojekt.newP.widgets.*
import java.util.*


object WidgetHelper {

    val widgets = LinkedList<Class<Widget>>()

    val PREF_ID = "WORT_UHR_WIDGETS"
    val SAVED_ID = "SAVED"


    fun getWidget(id: Int): Widget {
        return widgets[id].newInstance()
    }

    fun getSavedWidgets(): LinkedList<Widget> {
        return LinkedList<Widget>().apply {

            add(AlarmWidget())
            add(ColorWidget())
            add(IconWidget())
            add(LottoWidget())
            add(NightModeWidget())
            add(GameWidget())
            add(TextWidget())

        }
    }

    fun getSavedWidgets(ctx: Context): LinkedList<Widget> {

        val widgetNames = ctx.getSharedPreferences(PREF_ID, MODE_PRIVATE).getStringSet(
            SAVED_ID, HashSet<String>()
        )

        val ret = LinkedList<Widget>()

        widgetNames!!.forEach { name ->
            try {
                Toast.makeText(ctx, name, Toast.LENGTH_SHORT).show()
                ret.add(

                    Class.forName("$name").newInstance() as Widget
                )
            } catch (e: Exception) {
            }

        }

        return ret
    }


}