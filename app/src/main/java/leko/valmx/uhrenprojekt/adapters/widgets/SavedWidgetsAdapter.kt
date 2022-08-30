package leko.valmx.uhrenprojekt.adapters.widgets

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.utils.WidgetHelper

class SavedWidgetsAdapter(val fragmentManager: FragmentManager, val context: Context) :
    RecyclerView.Adapter<SavedWidgetsAdapter.VH>() {

    var data = WidgetHelper.getSavedWidgets(context)

    class VH(item: View) : RecyclerView.ViewHolder(item) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        return VH(inflater.inflate(R.layout.widget, parent, false)).apply {
            setIsRecyclable(false)
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val widget = data[position]

        widget.init(holder.itemView)

        animate(holder.itemView, position)


    }

    var lastPosition = -1

    fun animate(v: View, position: Int) {

        if (position > lastPosition) {

            val animation = AnimationUtils.loadAnimation(v.context, android.R.anim.slide_in_left)

            v.startAnimation(animation)

        }
        lastPosition = position

    }

    override fun getItemCount(): Int {
        lastPosition = -1
        return data.size
    }
}