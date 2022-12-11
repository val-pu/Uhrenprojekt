package leko.valmx.uhrenprojekt.adapters.widgets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.widget_item_command.view.description
import kotlinx.android.synthetic.main.widget_item_command.view.widget_title
import kotlinx.android.synthetic.main.widget_item_custom.view.*
import kotlinx.android.synthetic.main.widget_item_input.view.*
import kotlinx.android.synthetic.main.widget_item_recycler.view.*
import kotlinx.android.synthetic.main.widget_item_recycler.view.recycler
import kotlinx.android.synthetic.main.widget_item_redirect.view.*
import kotlinx.android.synthetic.main.widget_item_redirect.view.btn_redirect
import kotlinx.android.synthetic.main.widget_item_redirect.view.widget_description_toggle
import kotlinx.android.synthetic.main.widget_item_toggle.view.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.widgets.bundles.misc.MultipleChoiceSheet
import leko.valmx.uhrenprojekt.parents.UhrAppActivity
import leko.valmx.uhrenprojekt.widgets.bundles.*
import java.util.*

class WidgetContentAdapter(
    val content: LinkedList<ContentBundle>,
) :
    RecyclerView.Adapter<WidgetContentAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layout = when (viewType) {
            0 ->
                R.layout.widget_item_command
            1 -> R.layout.widget_item_redirect
            2 -> R.layout.widget_item_recycler
            3 -> R.layout.widget_item_multiple_choice
            4 -> R.layout.widget_item_input
            5 -> R.layout.widget_item_custom
            6 -> R.layout.widget_item_toggle
            else -> R.layout.widget
        }

        val inflater = LayoutInflater.from(parent.context)

        return VH(inflater.inflate(layout, parent, false)).apply { setIsRecyclable(false) }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val data = content[position]
        holder.setIsRecyclable(false)

        when (data) {
            is CommandBundle -> holder.bind(data)
            is RedirectBundle -> holder.bind(data)
            is RecyclerBundle -> holder.bind(data)
            is MultipleChoiceBundle -> holder.bind(data)
            is InputBundle -> holder.bind(data)
            is CustomBundle -> holder.bind(data)
            is ToggleBundle -> holder.bind(data)
        }

    }

    override fun getItemCount(): Int = content.size

    override fun getItemViewType(position: Int): Int {
        val data = content[position]
        return when (data) {
            is ToggleBundle -> 6
            is CustomBundle -> 5
            is InputBundle -> 4
            is MultipleChoiceBundle -> 3
            is RecyclerBundle -> 2
            is RedirectBundle -> 1
            is CommandBundle -> 0
            else -> 0
        }

    }

    inner class VH(item: View) : RecyclerView.ViewHolder(item) {

        fun bind(commandBundle: CommandBundle) {
            itemView.widget_title.text = commandBundle.name
            itemView.description.text = commandBundle.desc
            itemView.setOnClickListener {
                UhrAppActivity.send(UhrAppActivity.Command(commandBundle.command))
            }
        }

        fun bind(redirectBundle: RedirectBundle) {
            itemView.widget_title.text = redirectBundle.name
            itemView.widget_description_toggle.text = redirectBundle.description
            itemView.btn_redirect.setOnClickListener(redirectBundle.listener)
        }

        fun bind(redirectBundle: RecyclerBundle) {
            if (itemView.recycler.adapter != null) return

            itemView.recycler_title.text = redirectBundle.title
            itemView.recycler_description.text = redirectBundle.description
            itemView.recycler.adapter = redirectBundle.adapter


            itemView.recycler.layoutManager = GridLayoutManager(itemView.context, 5)
        }

        fun bind(multipleChoiceBundle: MultipleChoiceBundle) {
            itemView.widget_title.text = multipleChoiceBundle.title
            itemView.setOnClickListener {
                MultipleChoiceSheet(
                    multipleChoiceBundle.choices,
                    multipleChoiceBundle.command
                ).show(itemView.context) {
                    title("Wähle eine Option")
                }
            }
        }

        fun bind(inputBundle: InputBundle) {
            itemView.widget_title.text = inputBundle.title
            itemView.widget_description_input.text = inputBundle.title

            itemView.btn_continue_input.setOnClickListener {
                val input = itemView.input

                if (input.text.toString() == "") {
                    Snackbar.make(input, "Bitte schreibe erstmal wa ok?", Snackbar.LENGTH_LONG)
                    return@setOnClickListener
                }

                Snackbar.make(input, "Sende Befehl", Snackbar.LENGTH_LONG)
            }

        }

        fun bind(customBundle: CustomBundle) {
            val view = LayoutInflater.from(itemView.context)
                .inflate(customBundle.layout, itemView.wrapper, false)
            itemView.wrapper.addView(view)

            customBundle.logic(view)

        }

        fun bind(toggleBundle: ToggleBundle) {
            val res = itemView.resources

            fun update() {
                itemView.toggle_tooltip.text =
                    res.getText(if (toggleBundle.condition()) R.string.on else R.string.off)
            }

            itemView.widget_title.text = toggleBundle.title
            itemView.widget_description_toggle.text = toggleBundle.desc

            itemView.setOnClickListener {
                toggleBundle.onToggle(toggleBundle.condition())
                update()
            }
            update()

        }

    }
}
