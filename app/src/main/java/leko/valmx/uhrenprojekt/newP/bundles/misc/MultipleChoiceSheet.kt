package leko.valmx.uhrenprojekt.newP.bundles.misc

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxkeppeler.sheets.core.Sheet
import kotlinx.android.synthetic.main.widget_subitem_sheet_multiple_choice.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.newP.adapters.MultipleChoicePopUpAdapter
import leko.valmx.uhrenprojekt.newP.bundles.ChoiceItem
import leko.valmx.uhrenprojekt.newP.bundles.MultipleChoiceBundle
import java.util.*

class MultipleChoiceSheet(val dataSet: LinkedList<ChoiceItem>) : Sheet(),
    MultipleChoicePopUpAdapter.OnChoiceSelectedListener {
    override fun onCreateLayoutView(): View { return LayoutInflater.from(activity).inflate( R.layout.widget_subitem_sheet_multiple_choice, null) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sheet_recycler.layoutManager = LinearLayoutManager(context)
        sheet_recycler.adapter = MultipleChoicePopUpAdapter(this, dataSet)

    }
    fun show(ctx: Context, width: Int? = null, func: MultipleChoiceSheet.() -> Unit): MultipleChoiceSheet {
        this.windowContext = ctx
        this.width = width
        func(this)
        this.show()
        return this
    }

    override fun onSelected() {
        dismiss()
    }


}