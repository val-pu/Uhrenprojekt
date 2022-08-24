package leko.valmx.uhrenprojekt.newP.widgets

import androidx.recyclerview.widget.LinearLayoutManager
import leko.valmx.uhrenprojekt.newP.adapters.GameAdapter
import leko.valmx.uhrenprojekt.newP.bundles.ChoiceItem
import leko.valmx.uhrenprojekt.newP.bundles.OnInputSendListener
import leko.valmx.uhrenprojekt.newP.parents.Widget

class GameWidget : Widget(){
    override fun init() {
        title("Games")
        description("")

        recycler("mögliche Spiele", GameAdapter(), "", LinearLayoutManager(context))

    }
}