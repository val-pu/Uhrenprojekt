package leko.valmx.uhrenprojekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import leko.valmx.uhrenprojekt.appearance.AppearanceActivity
import leko.valmx.uhrenprojekt.birthday.BirthdayActivity
import leko.valmx.uhrenprojekt.calendar.CalendarActivity
import leko.valmx.uhrenprojekt.specials.SpecialsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        btn_appearance.setOnClickListener {
            startActivity(Intent(this, AppearanceActivity::class.java))
        }

        btn_specials.setOnClickListener {
            startActivity(Intent(this, SpecialsActivity::class.java))
        }

        btn_birthday.setOnClickListener{
            startActivity(Intent(this, BirthdayActivity::class.java))
        }

        btn_calendar.setOnClickListener{
            startActivity(Intent(this, CalendarActivity::class.java))
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}