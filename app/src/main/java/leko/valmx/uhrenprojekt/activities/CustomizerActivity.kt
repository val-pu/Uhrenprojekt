package leko.valmx.uhrenprojekt.activities

import android.R.attr.data
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_customizer.*
import leko.valmx.uhrenprojekt.R


class CustomizerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_customizer)
        initViews()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initViews() {


        btn_erscheinung.setOnClickListener {
            startActivity(Intent(this, AppearanceActivity::class.java))
        }

        btn_gimmicks.setOnClickListener {
            startActivity(Intent(this, GimmickActivity::class.java))
        }

        btn_calendar.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }
    }

    /**
     * activated when power_btn is clicked
     */
    fun turnOffClock(view: View){
        //TODO turn off clock
    }
}