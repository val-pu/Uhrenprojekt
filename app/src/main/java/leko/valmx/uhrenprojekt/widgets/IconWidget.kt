package leko.valmx.uhrenprojekt.widgets

import androidx.recyclerview.widget.GridLayoutManager
import leko.valmx.uhrenprojekt.adapters.widgets.IconAdapter

class IconWidget(): Widget() {
    override fun init() {
        title("Icons")
        description("Zeige icons an")

        recycler("Verfügbare Icons", IconAdapter(),"JA",GridLayoutManager(context,5))
//        command()
    }
}