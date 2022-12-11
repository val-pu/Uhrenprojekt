package leko.valmx.uhrenprojekt.widgets

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.widget.view.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.adapters.widgets.WidgetContentAdapter
import leko.valmx.uhrenprojekt.data.DataTBN
import leko.valmx.uhrenprojekt.parents.UhrAppActivity
import leko.valmx.uhrenprojekt.widgets.bundles.*
import java.util.*
import kotlin.collections.LinkedHashSet

abstract class Widget() {

    lateinit var data: DataTBN

    private var title = "Titel"
    private var description = ""

    lateinit var view: View

    lateinit var context: Context

    var saveListener: OnSaveListener? = null

    var content: LinkedList<ContentBundle> = LinkedList()

    fun title(title: String) {
        this.title = title
    }

    fun executeCommand(command: String, desc: String = "No description given.") {
        UhrAppActivity.send(UhrAppActivity.Command(command, desc))
    }


    fun description(description: String) {
        this.description = description
    }

    fun command(
        name: String = "Test",
        command: String,
        description: String = ""
    ) {
        content.add(CommandBundle(name, command, description))
    }

    fun redirect(
        name: String = "TODO",
        description: String = "",
        onClickListener: View.OnClickListener
    ) {
        content.add(RedirectBundle(name, description, onClickListener))
    }

    fun recycler(
        name: String = "Recycler",
        adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>,
        description: String = "",
        layoutManager: RecyclerView.LayoutManager
    ) {
        content.add(RecyclerBundle(name, adapter, description, layoutManager))
    }

    /**
     * @name sollte einzigartig sein, da die gewählte Option unter diesem namen gespeichert wird.
     * @author Val
     * @param choices Eine Funktion wird übergeben in welcher man die Wahlmöglichkeiten definieren kann. Die Parameter werden
     * so eigesetzt @param command + " " + param
     * das zurückgegebene Item wird als default-option gesetzt und vorne hinzugefügt sonst wird das erste item gewählt. Ok iwie ist das unnötig, erlaubt aber mehr (Vielleicht?)
     */

    fun multipleChoice(
        name: String = "Choice",
        command: String,
        choices: (it: LinkedList<ChoiceItem>) -> Unit
    ) {

        val choiceItems = LinkedList<ChoiceItem>().apply { choices(this) }

        content.add(MultipleChoiceBundle(name, command, choiceItems))

    }

    fun input(
        name: String = "Input",
        desc: String = "Geben sie Input ein",
        listener: OnInputSendListener
    ) {
        content.add(InputBundle(title, description, listener))
    }

    fun custom(layout: Int, logic: View.() -> Unit) {
        content.add(CustomBundle(layout, logic))
    }

    fun toggle(
        title: String,
        description: String,
        condition: () -> Boolean,
        onToggle: Boolean.() -> Unit
    ) {
        content.add(ToggleBundle(title, description, condition, onToggle))
    }

    fun init(v: View) {
        content = LinkedList()
        view = v
        context = v.context
        data = DataTBN(context)

        init()
        view.widget_title.text = title
        view.widget_description.text = description
        view.recycler.adapter = WidgetContentAdapter(content)
        initCollapseFunction()
        initSaveFunction()
    }


    private fun initCollapseFunction() {

        view.widget_top_bar.setOnClickListener {

            val collapse = if (view.btn_collapse.rotation == 0F) 1 else 0

            view.btn_collapse.animate().apply {
                rotation(90F * collapse)
                start()
            }

            if (collapse == 0) {
                view.recycler.visibility = GONE
            } else view.recycler.visibility = VISIBLE
        }
    }

    private fun initSaveFunction() {

        updateSavedButton()

        view.btn_save.setOnClickListener {
            save()
        }
    }


    fun save() {
        if (isSaved()) markAsUnsaved()
        else markAsSaved()
        updateSavedButton()
    }

    private fun updateSavedButton() {
        view.btn_save.setImageDrawable(view.resources.getDrawable(if (isSaved()) R.drawable.ic_bookmark_saved else R.drawable.ic_bookmark))
    }

    fun isSaved(): Boolean {
        val savedSet = prefs!!.getStringSet(WidgetHelper.SAVED_ID, LinkedHashSet<String>())

        return savedSet!!.contains(getWidgetID().toString())
    }

    private fun markAsSaved() {
        val edit = prefs!!.edit()

        val savedSet = prefs!!.getStringSet(WidgetHelper.SAVED_ID, LinkedHashSet<String>())
        savedSet!!.add(getWidgetID().toString())

        edit.putStringSet(WidgetHelper.SAVED_ID, savedSet)

        edit.commit()

        //Snackbar.make(view!!, "Test", Snackbar.LENGTH_LONG).show()

    }

    private fun markAsUnsaved() {
        val edit = prefs!!.edit()

        val savedSet = prefs!!.getStringSet(WidgetHelper.SAVED_ID, LinkedHashSet<String>())
        savedSet!!.remove(getWidgetID().toString())

        edit.putStringSet(WidgetHelper.SAVED_ID, savedSet)
        edit.apply()
    }

    private val prefs: SharedPreferences?
        get() {

            return loadSharedPrefs()
        }

    private fun loadSharedPrefs(): SharedPreferences? {
        return view.context!!.getSharedPreferences(WidgetHelper.PREF_ID, MODE_PRIVATE)
    }


    fun getWidgetID(): String = javaClass.name

    abstract fun init()

    interface OnSaveListener {
        fun onSave(save: Boolean)
    }
}
