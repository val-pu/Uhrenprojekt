package leko.valmx.uhrenprojekt.developertools

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.console_line.view.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.birthday.BirthdayAdapter

class ConsoleAdapter(val consoleContent: ArrayList<Array<String>>):
    RecyclerView.Adapter<ConsoleAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.console_line, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var content = consoleContent[position][0]
        val type = consoleContent[position][1]
        if(type == "CONNECTION_INFO"){
            content = "--------" + content + "\n--------"
        }
        holder.itemView.console_line.text = content
        if(type == "SUCCESS"){
            holder.itemView.console_line.setTextColor(Color.WHITE)
        }
        if(type == "INFO"){
            holder.itemView.console_line.setTextColor(Color.YELLOW)
        }
        if(type == "ERROR"){
            holder.itemView.console_line.setTextColor(Color.RED)
        }
        if(type == "CONNECTION_INFO")
            holder.itemView.console_line.setTextColor(Color.argb(255, 255,165,0))
    }

    override fun getItemCount(): Int = consoleContent.size
}