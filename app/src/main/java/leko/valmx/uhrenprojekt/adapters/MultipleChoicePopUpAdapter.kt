package leko.valmx.uhrenprojekt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.widget_subitem_multiple_choice.view.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bundles.ChoiceItem
import java.util.*

class MultipleChoicePopUpAdapter(
    val listener: OnChoiceSelectedListener,
    val dataSet: LinkedList<ChoiceItem>
) :
    RecyclerView.Adapter<MultipleChoicePopUpAdapter.VH>() {

    class VH(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.widget_subitem_multiple_choice, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val itemView = holder.itemView

        val data = dataSet[position]

        itemView.choice_title.text = data.name
        itemView.choice_description.text = data.description

        itemView.choice_item.setOnClickListener {

            listener.onSelected(data)

        }

    }

    override fun getItemCount(): Int = dataSet.size

    interface OnChoiceSelectedListener {
        fun onSelected(item: ChoiceItem)
    }

}