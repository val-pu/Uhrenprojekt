package leko.valmx.uhrenprojekt.adapters.widgets

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_icon.view.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.parents.UhrAppActivity
import java.util.*

class IconAdapter() : RecyclerView.Adapter<IconAdapter.VH>() {

    val data = LinkedList<IconInfo>()

    init {
        val contentList: LinkedList<IconInfo> = LinkedList()
        contentList.add(IconInfo(R.drawable.ic_heart, "heart"))
        contentList.add(IconInfo(R.drawable.ic_smile, "smiley"))
        contentList.add(IconInfo(R.drawable.ic_check, "check"))
        contentList.add(IconInfo(R.drawable.ic_smile, "cross"))
        contentList.add(IconInfo(R.drawable.ic_plus, "plus"))
        contentList.add(IconInfo(R.drawable.ic_moon, "moon"))
        contentList.add(IconInfo(R.drawable.ic_chevrons_right, "arrow"))
        data.addAll(contentList)


    }

    class VH(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_icon, parent, false)
        return VH(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val iconInfo = data[position]
        val view = holder.itemView
        view.icon.setImageDrawable(view.context.resources.getDrawable(iconInfo.id))

        view.setOnClickListener {
            UhrAppActivity.send(UhrAppActivity.Command("${iconInfo.cmd}"))
        }

    }

    override fun getItemCount(): Int = data.size


}

class IconInfo(val id: Int, val cmd: String)