package leko.valmx.uhrenprojekt.newP.widgets

import android.app.PendingIntent
import android.content.Intent
import leko.valmx.uhrenprojekt.developertools.DeveloperActivity
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

        redirect("Entwickleroptionen", "", {
            val intent = Intent(context, DeveloperActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        })
    }
}