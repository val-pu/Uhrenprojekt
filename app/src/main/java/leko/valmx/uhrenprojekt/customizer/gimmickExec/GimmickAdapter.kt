package leko.valmx.uhrenprojekt.customizer.gimmickExec

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_gimmick.view.*
import leko.valmx.uhrenprojekt.R

class GimmickAdapter(private val gimmickArrList: ArrayList<Int>, val context: Context)
    : RecyclerView.Adapter<GimmickAdapter.VH>() {

    class VH(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gimmick, parent, false)
        return VH(view)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: VH, position: Int) {
        val content = gimmickArrList[position]
        //text ist die id
        holder.itemView.text.setImageDrawable(context.resources.getDrawable(content))
    }

    override fun getItemCount(): Int = gimmickArrList.size
}