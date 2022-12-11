package leko.valmx.uhrenprojekt

import android.animation.Animator
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.LinearInterpolator
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_customizer.*
import leko.valmx.uhrenprojekt.adapters.widgets.SavedWidgetsAdapter
import leko.valmx.uhrenprojekt.parents.UhrAppActivity
import leko.valmx.uhrenprojekt.etc.developertools.DeveloperActivity
import leko.valmx.uhrenprojekt.widgets.WidgetHelper


class SimpleControlActivity : UhrAppActivity(), UhrAppActivity.OnCommandListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customizer)
//        setSupportActionBar(toolbar)
        init()
    }

    var isSaved = false

    private fun init() {
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

        fab.callOnClick()


//        ble.setOnClickListener {
//            if (!Blue.isConnected) {
//                ConnectBottomSheet.getInstance().show(this) {}
//                android.os.Handler().postDelayed(this, 20_000)
//
//            } else {
//                Blue.isConnected = false
//                ConnectBottomSheet.undo()
//                Blue.connection = null
//                ble.setBackgroundTintList(
//                    ColorStateList.valueOf(
//                        Color
//                            .parseColor("#FFFFFF")
//                    )
//                );
//            }
//        }
//
//        btn_restart.setOnClickListener{
//            if(!Blue.isConnected){
//                ConnectBottomSheet.getInstance().show(this){}
//                android.os.Handler().postDelayed(this, 20_000)
//
//            }else{
//                Blue.sendCommand("reset")
//                Blue.isConnected = false
//                ConnectBottomSheet.undo()
//                Blue.connection = null
//                ble.setBackgroundTintList(ColorStateList.valueOf(Color
//                    .parseColor("#FFFFFF")));
//                ConnectBottomSheet.getInstance().show(this){}
//                android.os.Handler().postDelayed(this, 20_000)
//            }
//        }

    }


    override fun onBackPressed() {
        finishAffinity()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.desgn_change -> {
                //TODO handle theme-change
                true
            }
            R.id.developertools_appear_btn -> {
                val i = Intent(this, DeveloperActivity::class.java)
                this.startActivity(i)
                true
            }
            R.id.licenses -> {
                //TODO handle license-appear
                val i = Intent(this, DeveloperActivity::class.java)
                this.startActivity(i)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCommandSizeUpdated(newSize: Int) {
        running_processes_text.text = newSize.toString()
    }

    override fun onExecutionFinish() {
        process_layout.animate().alpha(0F)

    }

    override fun onExecutionStart() {
        process_layout.animate().alpha(1F)
        rotateRunningBtn()
    }

    override fun onDisconnect() {

    }

    private fun rotateRunningBtn() {
        btn_running_processes.animate().apply {
            rotationBy(30F)
            setInterpolator(LinearInterpolator())
            withEndAction {
                if (currentlyExecuted != null)
                    rotateRunningBtn()
            }
            start()
        }
    }

}