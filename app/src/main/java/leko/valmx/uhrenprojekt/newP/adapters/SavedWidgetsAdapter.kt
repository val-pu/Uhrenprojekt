package leko.valmx.uhrenprojekt.newP.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_birthday_add.view.*
import kotlinx.android.synthetic.main.fragment_b_sheet.view.*
import kotlinx.android.synthetic.main.item_fragment.view.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.newP.widgets.WidgetHelper

class SavedWidgetsAdapter(val childFragmentManager: FragmentManager) :
    RecyclerView.Adapter<SavedWidgetsAdapter.VH>() {

    val data = WidgetHelper.getSavedWidgets()


    class VH(item: View) : RecyclerView.ViewHolder(item) {
        val fragmentHolder = item.findViewById<FragmentContainerView>(R.id.fragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        return VH(inflater.inflate(R.layout.item_fragment, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val supportFragmentManager =
            (holder.itemView.context as AppCompatActivity).supportFragmentManager
        val widget = data[position]
        val containerId: Int = holder.fragmentHolder.id

        val oldFragment: Fragment? = supportFragmentManager.findFragmentById(containerId)
        if (oldFragment != null) {
            supportFragmentManager.beginTransaction().remove(oldFragment).commit()
        }

        val generatedId = View.generateViewId()
        holder.fragmentHolder.id = generatedId


        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(generatedId, widget).commit()


    }

    override fun getItemCount(): Int = data.size

}