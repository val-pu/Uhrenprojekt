package leko.valmx.uhrenprojekt.newP

import android.animation.Animator
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_customizer.*
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.newP.adapters.SavedWidgetsAdapter
import leko.valmx.uhrenprojekt.newP.autoconnect.UhrAppActivity
import leko.valmx.uhrenprojekt.newP.utils.WidgetHelper

class CustomizerActivity : UhrAppActivity() {
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

        })*/
    }

}