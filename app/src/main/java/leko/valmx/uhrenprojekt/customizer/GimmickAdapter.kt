package leko.valmx.uhrenprojekt.customizer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import leko.valmx.uhrenprojekt.R

class GimmickAdapter(val inflator: LayoutInflater): RecyclerView.Adapter<GimmickAdapter.VH>() {
    class VH(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            inflator.inflate(R.layout.item_gimmick, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

    }

    override fun getItemCount(): Int = 10
}