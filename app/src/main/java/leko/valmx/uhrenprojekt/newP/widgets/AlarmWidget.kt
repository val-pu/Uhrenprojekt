package leko.valmx.uhrenprojekt.newP.widgets

import leko.valmx.uhrenprojekt.newP.parents.Widget

class AlarmWidget: Widget() {
    override fun init() {
        title("Alarm")
        description("Setze einen Reminder")
        redirect("Bestimmte Uhrzeit", "Setze einen Alarm auf eine bestimmte Uhrzeit", {})
        redirect("Klingeldauer", "Setzte die länge des Alarms", {})
    }
}