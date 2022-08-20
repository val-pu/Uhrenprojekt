package leko.valmx.uhrenprojekt.newP

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.addListener
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING
import kotlinx.android.synthetic.main.activity_customizer.*
import kotlinx.android.synthetic.main.activity_customizer.view.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.newP.adapters.SavedWidgetsAdapter
import leko.valmx.uhrenprojekt.newP.widgets.Widget
import leko.valmx.uhrenprojekt.newP.widgets.WidgetHelper

class CustomizerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customizer)
        initRecycler()




    }

    var baseMargin = 400F

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)

        baseMargin = (recycler.x)

    }

    override fun onStart() {
        super.onStart()
        Log.i("WIDGETS",getSharedPreferences(WidgetHelper.PREF_ID, MODE_PRIVATE).getStringSet(
            WidgetHelper.SAVED_ID, HashSet<String>()
        ).toString())
    }

    var isSaved = false

    private fun initRecycler() {
        val savedWidgetsAdapter = SavedWidgetsAdapter(supportFragmentManager, this)

        recycler.adapter = savedWidgetsAdapter

        val params = recycler.layoutParams



        fab.setOnClickListener {
            if (isSaved) {
                savedWidgetsAdapter.data = WidgetHelper.getSavedWidgets()
            } else savedWidgetsAdapter.data = WidgetHelper.getSavedWidgets(this)

            savedWidgetsAdapter.notifyDataSetChanged()

            isSaved = !isSaved

            recycler.adapter = savedWidgetsAdapter

        }

/*
        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var y = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                super.onScrolled(recyclerView, dx, dy)





                Log.i("SY","${recycler.computeVerticalScrollOffset()}")

                val scrollY = recycler.computeVerticalScrollOffset()

                if (scrollY == 0) {
                    val va = ValueAnimator.ofFloat(0F, baseMargin)

                    va.addUpdateListener {
                        val layoutParams = recycler.layoutParams as ConstraintLayout.LayoutParams



                        layoutParams.topMargin = (it.animatedValue as Float).toInt()

                        recyclerView.layoutParams = layoutParams
                    }
                    va.start()
                } else if (!dragging) {

                    if (scrollY != 0 && recycler.marginTop != 0) {
                        dragging = true

                        val va = ValueAnimator.ofFloat(baseMargin, 0F)

                        va.addUpdateListener {
                            val layoutParams =
                                recyclerView.layoutParams as ConstraintLayout.LayoutParams

                            layoutParams.topMargin = (it.animatedValue as Float).toInt()

                            recyclerView.layoutParams = layoutParams
                        }
                        va.addListener(onEnd = {


                        })

                        va.start()

                    }

                }

                y = dy


            }

            var dragging = false

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == SCROLL_STATE_DRAGGING) {

                    dragging = false


                } else {


                }
            }

        })*/}

}