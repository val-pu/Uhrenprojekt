package leko.valmx.uhrenprojekt.customizer.sheets

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_color.view.*
import leko.valmx.uhrenprojekt.R
import java.util.*

class ColorPickerAdapter(private val inflater: LayoutInflater) :
    RecyclerView.Adapter<ColorPickerAdapter.VH>() {

    private val data = LinkedList<ColorItem>()

    init {
        data.add(
            ColorItem(
                colorValueOf(255, 0, 0),
                colorValueOf(20, 0, 0),
                "Rot"
            )
        )
        data.add(
            ColorItem(
                colorValueOf(255, 20, 0),
                colorValueOf(20, 100, 100),
                "Rot"
            )
        )
        data.add(
            ColorItem(
                colorValueOf(255, 40, 0),
                colorValueOf(100, 0, 150),
                "Rot"
            )
        )
        data.add(
            ColorItem(
                colorValueOf(255, 130, 0),
                colorValueOf(20, 20, 100),
                "Rot"
            )
        )
        data.add(
            ColorItem(
                colorValueOf(255, 255, 0),
                colorValueOf(20, 20, 0),
                "Rot"
            )
        )
    }


    class VH(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(inflater.inflate(R.layout.item_color, parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val dataSet = data[position]
        holder.itemView.preview.setColor(dataSet.textColor, dataSet.bgColor)
    }

    override fun getItemCount(): Int = data.size

    class ColorItem(val textColor: Color, val bgColor: Color, val name: String)
    
    private fun colorValueOf(r: Int, g: Int, b: Int): Color {
        return Color.valueOf(r/255F,g/255F,b/255F,1F)
    }


}