package leko.valmx.uhrenprojekt.adapters.widgets

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_color.view.*
import leko.valmx.uhrenprojekt.R
import java.util.*

class ColorAdapter() :
    RecyclerView.Adapter<ColorAdapter.VH>() {

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

        data.add(
            ColorItem(
                colorValueOf(0, 255, 0),
                colorValueOf(0, 20, 0),
                "Rot"
            )
        )
        data.add(
            ColorItem(
                colorValueOf(0, 255, 20),
                colorValueOf(100, 20, 100),
                "Rot"
            )
        )

        data.add(
            ColorItem(
                colorValueOf(0, 255, 50),
                colorValueOf(150, 100, 0),
                "Rot"
            )
        )
        data.add(
            ColorItem(
                colorValueOf(0, 255, 130),
                colorValueOf(100, 20, 20),
                "Rot"
            )
        )
        data.add(
            ColorItem(
                colorValueOf(0, 255, 255),
                colorValueOf(0, 20, 20),
                "Rot"
            )
        )

        data.add(
            ColorItem(
                colorValueOf(0, 0, 255),
                colorValueOf(0, 0, 255),
                "Rot"
            )
        )

        data.add(
            ColorItem(
                colorValueOf(50, 0, 255),
                colorValueOf(0,150, 255),
                "Rot"
            )
        )

        data.add(
            ColorItem(
                colorValueOf(130, 0, 255),
                colorValueOf(20,100, 20),
                "Rot"
            )
        )

        data.add(
            ColorItem(
                colorValueOf(255, 0, 255),
                colorValueOf(20,0, 20),
                "Rot"
            )
        )

        data.add(
            ColorItem(
                colorValueOf(210, 255, 255),
                colorValueOf(20,20, 20),
                "Rot"
            )
        )
    }


    class VH(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false))

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