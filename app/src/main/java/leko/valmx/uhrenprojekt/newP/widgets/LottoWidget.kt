package leko.valmx.uhrenprojekt.newP.widgets

import androidx.recyclerview.widget.GridLayoutManager
import leko.valmx.uhrenprojekt.appearance.colors.ColorPickerAdapter

class LottoWidget() : Widget() {
    override fun getWidgetID(): Int = WidgetHelper.LOTTO_WIDGET

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