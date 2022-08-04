package leko.valmx.uhrenprojekt.newP.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.widget_item_command.view.description
import kotlinx.android.synthetic.main.widget_item_command.view.widget_title
import kotlinx.android.synthetic.main.widget_item_recycler.view.*
import kotlinx.android.synthetic.main.widget_item_redirect.view.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.newP.bundles.CommandBundle
import leko.valmx.uhrenprojekt.newP.bundles.ContentBundle
import leko.valmx.uhrenprojekt.newP.bundles.RecyclerBundle
import leko.valmx.uhrenprojekt.newP.bundles.RedirectBundle
import java.util.*

class WidgetContentAdapter(val content: LinkedList<ContentBundle>) :
    RecyclerView.Adapter<WidgetContentAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layout = when (viewType) {
            0 ->
                R.layout.widget_item_command
            1 -> R.layout.widget_item_redirect
            2 -> R.layout.widget_item_recycler
            else -> R.layout.widget
        }

        val inflater = LayoutInflater.from(parent.context)

        return VH(inflater.inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val data = content[position]

        when (data) {
            is CommandBundle -> holder.bind(data)
            is RedirectBundle -> holder.bind(data)
            is RecyclerBundle -> holder.bind(data)
        }

    }

    override fun getItemCount(): Int = content.size

    override fun getItemViewType(position: Int): Int {
        val data = content[position]
        return when (data) {
            is RecyclerBundle -> 2
            is RedirectBundle -> 1
            is CommandBundle -> 0
            else -> 0
        }

    }

    class VH(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(commandBundle: CommandBundle) {
            itemView.widget_title.text = commandBundle.name
            itemView.description.text = commandBundle.desc
        }

        fun bind(redirectBundle: RedirectBundle) {
            itemView.widget_title.text = redirectBundle.name
            itemView.description_redirect.text = redirectBundle.description
            itemView.btn_redirect.setOnClickListener(redirectBundle.listener)
        }

        fun bind(redirectBundle: RecyclerBundle) {
            itemView.recycler_title.text = redirectBundle.title
            itemView.recycler_description.text = redirectBundle.description
            itemView.recycler.adapter = redirectBundle.adapter
            itemView.recycler.layoutManager = redirectBundle.layoutManager
        }

    }
}
