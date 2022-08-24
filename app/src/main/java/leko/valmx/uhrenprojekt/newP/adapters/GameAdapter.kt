package leko.valmx.uhrenprojekt.newP.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.games_row.view.*
import kotlinx.android.synthetic.main.item_icon.view.*
import kotlinx.android.synthetic.main.widget_item_command.view.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.specials.SpecialsActivity
import java.util.*
import kotlin.collections.ArrayList

class GameAdapter() : RecyclerView.Adapter<GameAdapter.VH>() {

    val data = LinkedList<String>()

    init {
        val contentList: LinkedList<String> = LinkedList()
        contentList.add("Schiffe versenken")
        contentList.add("tetris")
        contentList.add("test")
        data.addAll(contentList)
    }

    class VH(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.games_row, parent, false)
        return VH(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val rowContent = data[position]
        holder.itemView.game_name.text = rowContent
    }

    override fun getItemCount(): Int = data.size
}