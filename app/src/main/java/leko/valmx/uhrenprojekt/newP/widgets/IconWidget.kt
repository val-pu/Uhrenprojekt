package leko.valmx.uhrenprojekt.newP.widgets

import androidx.recyclerview.widget.GridLayoutManager
import leko.valmx.uhrenprojekt.newP.parents.Widget
import leko.valmx.uhrenprojekt.specials.icons.IconAdapter

class IconWidget(): Widget() {
    override fun init() {
        title("Icons")
        description("Zeige icons an")

        recycler("Verfügbare Icons",IconAdapter(),"JA",GridLayoutManager(context,5))
//        command()
    }
}