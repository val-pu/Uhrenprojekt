package leko.valmx.uhrenprojekt.newP

import android.animation.Animator
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_customizer.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.newP.adapters.SavedWidgetsAdapter
import leko.valmx.uhrenprojekt.newP.autoconnect.UhrAppActivity
import leko.valmx.uhrenprojekt.newP.utils.WidgetHelper

class CustomizerActivity : UhrAppActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customizer)
        setSupportActionBar(toolbar)
        initRecycler()
    }

    var baseMargin = 400F

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)

        baseMargin = (recycler.x)

    }

    override fun onStart() {
        super.onStart()
        Log.i(
            "WIDGETS", getSharedPreferences(WidgetHelper.PREF_ID, MODE_PRIVATE).getStringSet(
                WidgetHelper.SAVED_ID, HashSet<String>()
            ).toString()
        )
    }

    var isSaved = false

    private fun initRecycler() {
        val savedWidgetsAdapter = SavedWidgetsAdapter(supportFragmentManager, this)

        recycler.adapter = savedWidgetsAdapter

        val params = recycler.layoutParams



        fab.setOnClickListener {

            var fromId = R.drawable.ic_bookmark
            var toId = R.drawable.ic_bookmark_saved

            if (isSaved)
                fromId = toId


            fab.animate().apply {

                alpha(0F)


                setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator?) {

                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        fab.animate().alpha(1F).start()


                        fab.setImageDrawable(resources.getDrawable(fromId))


                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                })

                start()


            }

            if (!isSaved) {
                savedWidgetsAdapter.data = WidgetHelper.getSavedWidgets()
            } else savedWidgetsAdapter.data = WidgetHelper.getSavedWidgets(this)




            isSaved = !isSaved

            recycler.adapter = savedWidgetsAdapter

        }

    }

}