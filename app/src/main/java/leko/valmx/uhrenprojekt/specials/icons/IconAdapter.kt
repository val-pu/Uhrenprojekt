package leko.valmx.uhrenprojekt.specials.icons

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_icon.view.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.bluetooth.Blue
import leko.valmx.uhrenprojekt.specials.SpecialsActivity
import java.util.*

class IconAdapter() : RecyclerView.Adapter<IconAdapter.VH>() {

    val data = LinkedList<SpecialsActivity.IconInfo>()

    init {
        val contentList: LinkedList<SpecialsActivity.IconInfo> = LinkedList()
        contentList.add(SpecialsActivity.IconInfo("Herz", R.drawable.ic_heart, "heart"))
        contentList.add(SpecialsActivity.IconInfo("Smiley", R.drawable.ic_smile, "smiley"))
        contentList.add(SpecialsActivity.IconInfo("Haken", R.drawable.ic_check, "check"))
        contentList.add(SpecialsActivity.IconInfo("Kreuz", R.drawable.ic_smile, "cross"))
        contentList.add(SpecialsActivity.IconInfo("Plus", R.drawable.ic_plus, "plus"))
        contentList.add(SpecialsActivity.IconInfo("Mond", R.drawable.ic_moon, "moon"))
        contentList.add(SpecialsActivity.IconInfo("Doppelpfeil", R.drawable.ic_chevrons_right, "arrow"))
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
        view.icon_name.text = iconInfo.name

        view.setOnClickListener {
            Blue.sendCommand("${iconInfo.cmd}",view)
        }

    }

    override fun getItemCount(): Int = data.size
}