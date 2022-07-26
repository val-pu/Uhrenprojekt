package leko.valmx.uhrenprojekt.birthday

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.birthday_row_content.view.*
import leko.valmx.uhrenprojekt.R

class BirthdayAdapter(val content: ArrayList<Array<String>>,
                      val recyclerInterface: BirthdayRecyclerInterface):
                            RecyclerView.Adapter<BirthdayAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init{
            itemView.birthday_recycler_content.birthday_content_remove.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            if(adapterPosition != RecyclerView.NO_POSITION)
                recyclerInterface.onClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.birthday_row_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rowContent = content[position]
        holder.itemView.birthday_recycler_content.birthday_recycler_content_person.text = rowContent[0];
        holder.itemView.birthday_recycler_content.birthday_recycler_content_date.text = rowContent[1];
    }

    override fun getItemCount(): Int = content.size
}
