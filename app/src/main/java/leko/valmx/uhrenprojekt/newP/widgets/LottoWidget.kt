package leko.valmx.uhrenprojekt.newP.widgets

import leko.valmx.uhrenprojekt.newP.parents.Widget

class LottoWidget() : Widget() {

    override fun init() {
        title("Lotto")
        description("Die Lottozahlen der letzten Ziehung")
        command(
            "EuroJackPot",
            "euro",
            "Keine Garantie für die Aktualität der Daten"
        )
        command(
            "Lotto (6 aus 49)",
            "lotto",
            description = "Keine Garantie für die Aktualität der Daten"
        )



    }
}