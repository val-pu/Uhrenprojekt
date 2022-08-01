package leko.valmx.uhrenprojekt.calendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.activity_main.*
import leko.valmx.uhrenprojekt.MainActivity
import leko.valmx.uhrenprojekt.R
import leko.valmx.uhrenprojekt.alarm.AlarmActivity
import leko.valmx.uhrenprojekt.appearance.AppearanceActivity
import leko.valmx.uhrenprojekt.nightmode.NightmodeActivity

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        init()
    }

    fun init(){
        btn_to_alarm.setOnClickListener {
            startActivity(Intent(this, AlarmActivity::class.java))
        }

        btn_to_nightmode.setOnClickListener {
            startActivity(Intent(this, NightmodeActivity::class.java))
        }
    }

    fun backToMainActivityfromCalendar(view: View){
        startActivity(Intent(this, MainActivity::class.java))
    }
}