package leko.valmx.uhrenprojekt.newP.widgets

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.widget.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.newP.adapters.WidgetContentAdapter
import leko.valmx.uhrenprojekt.newP.bundles.CommandBundle
import leko.valmx.uhrenprojekt.newP.bundles.ContentBundle
import leko.valmx.uhrenprojekt.newP.bundles.RecyclerBundle
import leko.valmx.uhrenprojekt.newP.bundles.RedirectBundle
import java.util.*
import kotlin.collections.LinkedHashSet

abstract class Widget() : Fragment(R.layout.widget) {

    private var title = "Titel"
    private var description = ""

    var saveListener: OnSaveListener? = null

    private val content: LinkedList<ContentBundle> = LinkedList()

    fun title(title: String) {
        this.title = title
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
        layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
    ) {
        content.add(RecyclerBundle(name, adapter, description, layoutManager))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        widget_title.text = title
        widget_description.text = description
        recycler.adapter = WidgetContentAdapter(content)

        initCollapseFunction()
        initSaveFunction()
    }

    private fun initCollapseFunction() {


        widget_top_bar.setOnClickListener {

            val collapse = if (btn_collapse.rotation == 0F) 1 else 0

            btn_collapse.animate().apply {
                rotation(90F * collapse)
                start()
            }

            if (collapse == 0) {
                recycler.visibility = GONE
            } else recycler.visibility = VISIBLE
        }
    }

    private fun initSaveFunction() {

        updateSavedButton()

        btn_save.setOnClickListener {
            save()
        }
    }


    fun save() {
        if (isSaved()) markAsUnsaved()
        else markAsSaved()
        updateSavedButton()
    }

    private fun updateSavedButton() {
        btn_save.setImageDrawable(resources.getDrawable(if (isSaved()) R.drawable.ic_bookmark_saved else R.drawable.ic_bookmark))
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
        edit.apply()
    }

    private fun markAsUnsaved() {
        val edit = prefs!!.edit()

        val savedSet = prefs!!.getStringSet(WidgetHelper.SAVED_ID, LinkedHashSet<String>())
        savedSet!!.remove(getWidgetID().toString())

        edit.putStringSet(WidgetHelper.SAVED_ID, savedSet)
        edit.apply()
    }

    private var prefs: SharedPreferences? = null
        get() {

            if (field == null) loadSharedPrefs()

            return field
        }

    private fun loadSharedPrefs() {
        prefs = context!!.getSharedPreferences(WidgetHelper.PREF_ID, MODE_PRIVATE)
    }


    abstract fun getWidgetID(): Int

    abstract fun init()

    interface OnSaveListener {
        fun onSave(save: Boolean)
    }
}
